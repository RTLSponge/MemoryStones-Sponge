package au.id.rleach.efficientmultiblocks.interfaces;

import com.google.common.base.Predicate;
import org.spongepowered.api.block.BlockState;

public interface IBlockMatcher extends Predicate<BlockState> {
    abstract BlockState getDebugBlockState();
}
