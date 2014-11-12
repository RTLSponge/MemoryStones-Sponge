package au.id.rleach.efficientmultiblocks;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import org.spongepowered.api.block.BlockState;

import java.util.Optional;

/**
 * Represents a 3d pattern of blocks.
 */
public class BlockPattern {
    private final Predicate<BlockState>[][][] pattern;
    //used for drawing a given BlockPattern for debugging purposes.
    private final Optional<BlockState[][][]> blocks;


    //Package Local, lets use a factory to make these.
    BlockPattern(Predicate<BlockState>[][][] pattern) {
        Preconditions.checkNotNull(pattern);
        this.pattern = pattern;
        this.blocks = Optional.empty();
    }

    //Package Local, lets use a factory to make these.
    BlockPattern(Predicate<BlockState>[][][] pattern, BlockState[][][] blocks) {
        Preconditions.checkNotNull(pattern);
        this.pattern = pattern;
        this.blocks = Optional.of(blocks);
    }
}
