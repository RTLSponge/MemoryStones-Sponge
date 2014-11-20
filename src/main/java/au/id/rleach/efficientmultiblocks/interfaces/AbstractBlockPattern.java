package au.id.rleach.efficientmultiblocks.interfaces;

import au.id.rleach.efficientmultiblocks.*;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.cache.LoadingCache;
import org.spongepowered.api.block.Block;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.math.Vector3d;
import org.spongepowered.api.math.Vector3i;

import java.util.Collection;

/**
 * Represents a template to apply to the world to check for existing IMBObject
 */
public abstract class AbstractBlockPattern {

    public abstract RuleRotate getRotate();

    public abstract RuleFlip getFlip();

    public Orientation getOrientation(){
        return new Orientation();
    }

    public Reflexion getReflexion() {
        return new Reflexion();
    }

    /**
     *
     * @return returns an array of IEgoCoordinate representing the position of predicates that are not @see{Predicates.alwaysTrue()}.
     */
    public abstract Collection<IEgoCoordinate> getPositions();
    public abstract Collection<IEgoCoordinate> getPositions(BlockState bs);

    /**
     * Checks whether the pattern can be found at the given position.
     * @param placed the block to check
     * @param t a transform representing the possible position of the pattern.
     * @param cache a block cache.
     * @return an optional AbstractMBObject,
     */
    public abstract Optional<AbstractMBObject> isAtLocation(Block placed, Transform t, LoadingCache<Vector3d,Block> cache);

    /**
     * returns true if the location is inside the pattern, but fails the predicate.
     * @param bs the blockstate to test
     * @param pos the location to test at
     * @return true if the pattern can not have that block at this position.
     */
    public abstract boolean conflictsWithPattern(BlockState bs, IEgoCoordinate pos);
}
