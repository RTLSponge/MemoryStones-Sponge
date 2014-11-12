package au.id.rleach.efficientmultiblocks;

import org.spongepowered.api.util.Axis;
import org.spongepowered.api.util.Direction;

/**
 * A BlockPattern with a world, co-ordinate and orientation.
 * e.g. a result, or known location of a pattern, or a potential pattern.
 */
public class MBObject {
    private final BlockPattern pattern;
    private final Orientation orientation;
    private final Reflexion reflexion;

    MBObject(BlockPattern pattern, Direction forwards, Direction up) {
        this(pattern,forwards,up,null);
        if(!isValidDirection(forwards)){
            throw new IllegalArgumentException("Invalid fowards direction : "+forwards);
        }
        if(!isValidDirection(up)){
            throw new IllegalArgumentException("Invalid up direction : "+up);
        }
        if (pattern == null){
            throw new IllegalArgumentException("pattern was null");
        }
    }

    private MBObject(BlockPattern pattern, Direction forwards, Direction up, Axis...x) {
        this.pattern = pattern;
        this.orientation = new Orientation(forwards, up);
        this.reflexion = new Reflexion(x);

    }

    private boolean isValidDirection(Direction dir){
        return (dir.isUpright() || dir.isCardinal());
    }
}