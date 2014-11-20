package au.id.rleach.efficientmultiblocks;

import au.id.rleach.efficientmultiblocks.interfaces.AbstractBlockMatcher;
import au.id.rleach.efficientmultiblocks.interfaces.AbstractBlockPattern;
import au.id.rleach.efficientmultiblocks.interfaces.AbstractMBObject;
import au.id.rleach.efficientmultiblocks.interfaces.IEgoCoordinate;
import com.google.common.base.*;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import org.spongepowered.api.block.Block;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.math.Vector3d;
import org.spongepowered.api.math.Vector3i;

import java.util.Collection;
import java.util.Map;


/**
 * An Immutable class, representing a pattern to match blocks.
 */
public class BlockPattern extends AbstractBlockPattern{

    //used for matching the blocks.
    private ImmutableMap<IEgoCoordinate,AbstractBlockMatcher> pattern;
    private ImmutableMultimap<AbstractBlockMatcher,IEgoCoordinate> patternLookup;

    //used for drawing a given BlockPattern for debugging purposes.
    private ImmutableMap<IEgoCoordinate,BlockState> blocks;

    private RuleFlip flip;
    private RuleRotate rotate;
    private Orientation initialOrientation;
    private Reflexion reflexion;

    BlockPattern(Map<IEgoCoordinate,AbstractBlockMatcher> pattern, RuleFlip flip, RuleRotate rotate, Orientation orientation) {
        Preconditions.checkNotNull(pattern);
        Preconditions.checkNotNull(flip);
        Preconditions.checkNotNull(rotate);
        Preconditions.checkNotNull(orientation);

        this.blocks = ImmutableMap.of();
        ImmutableMap.Builder<IEgoCoordinate, AbstractBlockMatcher> builder = ImmutableMap.builder();
        for(Map.Entry<IEgoCoordinate,AbstractBlockMatcher> e: pattern.entrySet()){
            builder.put(e.getKey(),e.getValue());
        }
        this.pattern = builder.build();
        this.patternLookup = this.pattern.asMultimap().inverse();
        this.flip = flip;
        this.rotate = rotate;
        this.initialOrientation = orientation;
        this.reflexion = new Reflexion();
    }

    //Package Local, lets use a factory to make these.
    BlockPattern(Map<IEgoCoordinate,AbstractBlockMatcher> pattern, Map<IEgoCoordinate,BlockState> blocks, RuleFlip flip, RuleRotate rotate, Orientation orientation) {
        this(pattern, flip, rotate, orientation);
        this.blocks = ImmutableMap.copyOf(blocks);
    }

    public RuleFlip getFlip() {
        return flip;
    }

    @Override
    public Collection<IEgoCoordinate> getPositions() {
        return patternLookup.values();
    }

    public Collection<IEgoCoordinate> getPositions(AbstractBlockMatcher key) {
        return patternLookup.get(key);
    }

    @Override
    public Collection<IEgoCoordinate> getPositions(BlockState bs) {
        ImmutableSet.Builder<IEgoCoordinate> out = ImmutableSet.builder();
        for (Map.Entry<AbstractBlockMatcher, IEgoCoordinate> e : patternLookup.entries()) {
            if(e.getKey().apply(bs)){
                out.add(e.getValue());
            }
        }
        return out.build();
    }

    /**
     * returns true if the location is inside the pattern, but fails the predicate.
     * @param bs the blockstate to test
     * @param pos the location to test at
     * @return true if the pattern can not have that block at this position.
     */
    @Override
    public boolean conflictsWithPattern(BlockState bs, IEgoCoordinate pos){
        //Note, this for loop should only ever run once, as the multimap should be only 1 way.
        AbstractBlockMatcher m = pattern.get(pos);
        //If pos isn't in pattern then it doesn't conflict.
        if(m == null) {
            return false;
        }
        return !m.apply(bs);
    }

    @Override
    public Optional<AbstractMBObject> isAtLocation(Block placed, Transform t, LoadingCache<Vector3d, Block> cache) {

        for( Map.Entry<IEgoCoordinate, AbstractBlockMatcher> e: pattern.entrySet()){
            IEgoCoordinate ego = e.getKey();
            AbstractBlockMatcher match = e.getValue();
            Vector3d worldCoord = t.transform(placed, ego);
            if(!match.apply(cache.getUnchecked(worldCoord).getBlockState())) {
                return Optional.absent();
            }
        }

        return Optional.absent();
    }

    public RuleRotate getRotate() {
        return rotate;
    }
}
