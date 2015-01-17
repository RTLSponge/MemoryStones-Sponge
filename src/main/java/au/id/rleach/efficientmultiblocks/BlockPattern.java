package au.id.rleach.efficientmultiblocks;

import au.id.rleach.efficientmultiblocks.interfaces.IBlockMatcher;
import au.id.rleach.efficientmultiblocks.interfaces.AbstractBlockPattern;
import au.id.rleach.efficientmultiblocks.interfaces.AbstractMBObject;
import au.id.rleach.efficientmultiblocks.interfaces.IEgoCoordinate;
import com.google.common.base.*;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.sun.istack.internal.NotNull;
import org.spongepowered.api.block.BlockState;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;


/**
 * An Immutable class, representing a pattern to match blocks.
 */
public class BlockPattern extends AbstractBlockPattern{

    //used for matching the blocks.
    private final ImmutableMap<IEgoCoordinate,IBlockMatcher> pattern;
    private final ImmutableMultimap<IBlockMatcher,IEgoCoordinate> patternLookup;

    //used for drawing a given BlockPattern for debugging purposes.
    private ImmutableMap<IEgoCoordinate, BlockState> blocks;

    static final Function<IBlockMatcher, BlockState> TO_BLOCKSTATE = new Function<IBlockMatcher, BlockState>() {
        @Nullable
        @Override
        public BlockState apply(final IBlockMatcher input) {
            return input.getDebugBlockState();
        }
    };

    private final RuleFlip flip;
    private final RuleRotate rotate;
    private final Orientation initialOrientation;
    private final Reflexion reflexion;

    BlockPattern(final Map<IEgoCoordinate, IBlockMatcher> patternIn, final RuleFlip flipIn, final RuleRotate rotateIn, final Orientation orientationIn) {
        super();
        Preconditions.checkNotNull(patternIn);
        Preconditions.checkNotNull(flipIn);
        Preconditions.checkNotNull(rotateIn);
        Preconditions.checkNotNull(orientationIn);

        this.pattern = ImmutableMap.copyOf(patternIn);
        final Map<IEgoCoordinate, BlockState> transformed = Maps.transformValues(pattern, TO_BLOCKSTATE);
        //Make Immutable, too bad we can't have a immutable copy of an immutable map.
        this.blocks = ImmutableMap.copyOf(transformed);
        //Have a reverse lookup map for performance reasons, has to be a multimap due to possible duplicate values.
        this.patternLookup = this.pattern.asMultimap().inverse();
        this.flip = flipIn;
        this.rotate = rotateIn;
        this.initialOrientation = orientationIn;
        this.reflexion = new Reflexion();
    }

    //Package Local, lets use a factory to make these.
    BlockPattern(Map<IEgoCoordinate,IBlockMatcher> pattern, Map<IEgoCoordinate,BlockState> blocks, RuleFlip flip, RuleRotate rotate, Orientation orientation) {
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

    public Collection<IEgoCoordinate> getPositions(IBlockMatcher key) {
        return patternLookup.get(key);
    }

    @Override
    public Collection<IEgoCoordinate> getPositions(BlockState bs) {
        ImmutableSet.Builder<IEgoCoordinate> out = ImmutableSet.builder();
        for (Map.Entry<IBlockMatcher, IEgoCoordinate> e : patternLookup.entries()) {
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
        IBlockMatcher m = pattern.get(pos);
        //If pos isn't in pattern then it doesn't conflict.
        if(m == null) {
            return false;
        }
        return !m.apply(bs);
    }

    @Override
    public Optional<AbstractMBObject> isAtLocation(Block placed, Transform t, LoadingCache<Vector3d, Block> cache) {

        for( Map.Entry<IEgoCoordinate, IBlockMatcher> e: pattern.entrySet()){
            IEgoCoordinate ego = e.getKey();
            IBlockMatcher match = e.getValue();
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
