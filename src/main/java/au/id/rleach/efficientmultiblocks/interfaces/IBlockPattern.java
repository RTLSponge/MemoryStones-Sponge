package au.id.rleach.efficientmultiblocks.interfaces;

import au.id.rleach.efficientmultiblocks.Orientation;
import au.id.rleach.efficientmultiblocks.Reflexion;
import au.id.rleach.efficientmultiblocks.RuleFlip;
import au.id.rleach.efficientmultiblocks.RuleRotate;
import com.google.common.base.Predicate;
import org.spongepowered.api.block.BlockState;

import java.util.Collection;
import java.util.TreeMap;

/**
 * Represents a template to apply to the world to check for existing IMBObject
 */
public abstract class IBlockPattern {
    Predicate<BlockMatcher>[][][] pattern;
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

    public abstract Collection<EgoCoordinate> getTranslations();
}
