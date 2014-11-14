package au.id.rleach.efficientmultiblocks.interfaces;

import com.google.common.base.Optional;
import org.spongepowered.api.block.Block;


public interface PatternDetector {
    /**
     *
     * @param block The last block placed.
     * @return the first matching IMBObject the detector finds.
     */
    public Optional<AbstractMBObject> match(Block block);
}
