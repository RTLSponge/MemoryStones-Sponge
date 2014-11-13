package au.id.rleach.efficientmultiblocks.interfaces;

import org.spongepowered.api.block.Block;

import java.util.Optional;

public interface PatternDetector {
    /**
     *
     * @param block The last block placed.
     * @return the first matching IMBObject the detector finds.
     */
    public Optional<IMBObject> match(Block block);
}
