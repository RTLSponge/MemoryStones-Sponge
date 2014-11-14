package au.id.rleach.efficientmultiblocks.interfaces;

import au.id.rleach.efficientmultiblocks.*;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.cache.LoadingCache;
import org.spongepowered.api.block.Block;
import org.spongepowered.api.math.Vector3d;
import org.spongepowered.api.math.Vector3i;

/**
 * Represents a template to apply to the world to check for existing IMBObject
 */
public abstract class AbstractBlockPattern {
    Vector3i size;
    Predicate<AbstractBlockMatcher>[][][] pattern;
    RuleRotate rotate;
    Orientation orientation;

    public abstract RuleRotate getRotate();

    public abstract RuleFlip getFlip();

    public Orientation getOrientation(){
        return new Orientation();
    }

    public Reflexion getReflexion() {
        return new Reflexion();
    }

    public abstract Vector3i[] getBlocks(Block block);

    public abstract IEgoCoordinate[] getPositions();
    
    public abstract Optional<AbstractMBObject> isAtLocation(Block origin, Transform t, LoadingCache<Vector3d,Block> cache);
}
