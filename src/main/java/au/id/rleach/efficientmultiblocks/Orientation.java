package au.id.rleach.efficientmultiblocks;

import org.spongepowered.api.util.Direction;

import java.util.Set;
import java.util.TreeSet;

public class Orientation {
    private final Direction up;
    private final Direction forwards;

    public Orientation(Direction up, Direction forwards) {
        this.up = up;
        this.forwards = forwards;
    }

    //By default face up.
    public Orientation(Direction forwards) {
        this(Direction.UP, forwards);
    }

    public Orientation(){
        this(Direction.NORTH);
    }

    public static Set<Direction> up(){
        //Ordered set.
        Set <Direction> out = new TreeSet<Direction> ();
        //This order is most efficient for up, as multiblocks are more commonly rotated around the y axis then any other
        out.add(Direction.UP);
        out.add(Direction.DOWN);
        out.add(Direction.NORTH);
        out.add(Direction.EAST);
        out.add(Direction.SOUTH);
        out.add(Direction.WEST);
        return out;
    }

    public static Set<Direction> forwards(){
        //Ordered set.
        Set <Direction> out = new TreeSet<Direction> ();
        //This order is most efficient for forwards, as multiblocks are more commonly rotated around the y axis then any other
        out.add(Direction.NORTH);
        out.add(Direction.EAST);
        out.add(Direction.SOUTH);
        out.add(Direction.WEST);
        out.add(Direction.UP);
        out.add(Direction.DOWN);
        return out;
    }
}
