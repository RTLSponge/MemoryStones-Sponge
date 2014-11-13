package au.id.rleach.efficientmultiblocks.interfaces;

import com.google.common.base.Predicate;
import org.spongepowered.api.block.BlockState;

import java.util.TreeMap;

/**
 * Represents a template to apply to the world to check for existing IMBObject
 */
public abstract class IBlockPattern {
    Predicate<BlockMatcher>[][][] pattern;

}
