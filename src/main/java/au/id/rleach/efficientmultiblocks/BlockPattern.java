package au.id.rleach.efficientmultiblocks;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import org.spongepowered.api.block.BlockState;

import java.util.Optional;

/**
 * Represents a 3d pattern of blocks.
 */
public class BlockPattern {
    //used for matching the blocks.
    private Predicate<BlockState>[][][] pattern;
    //used for drawing a given BlockPattern for debugging purposes.
    private Optional<BlockState[][][]> blocks;
    private RuleFlip flip;
    private RuleRotate rotate;
    private Orientation initialOrientation;
    private Reflexion reflexion;

    BlockPattern(Predicate<BlockState>[][][] pattern, RuleFlip flip, RuleRotate rotate, Orientation orientation) {
        Preconditions.checkNotNull(pattern);
        Preconditions.checkNotNull(flip);
        Preconditions.checkNotNull(rotate);
        Preconditions.checkNotNull(orientation);

        this.blocks = Optional.empty();
        this.pattern = pattern;
        this.flip = flip;
        this.rotate = rotate;
        this.initialOrientation = orientation;
        this.reflexion = new Reflexion();
    }

    //Package Local, lets use a factory to make these.
    BlockPattern(Predicate<BlockState>[][][] pattern, BlockState[][][] blocks, RuleFlip flip, RuleRotate rotate, Orientation orientation) {
        this(pattern, flip, rotate, orientation);
        this.blocks = Optional.of(blocks);
    }

    public RuleFlip getFlip() {
        return flip;
    }

    public RuleRotate getRotate() {
        return rotate;
    }
}
