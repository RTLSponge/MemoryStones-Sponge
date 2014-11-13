package au.id.rleach.efficientmultiblocks;

import org.spongepowered.api.util.Axis;
import org.spongepowered.api.util.Direction;

import java.util.EnumMap;
import java.util.Set;
import java.util.TreeSet;

public class RuleRotate {
    private final EnumMap<Axis,Boolean> map = new EnumMap<Axis,Boolean>(Axis.class);

    private RuleRotate() {
        for(Axis x:Axis.values()){
            map.put(x, false);
        }
    }

    private RuleRotate(Axis... axises){
        this();
        for(Axis x : axises){
            map.put(x,true);
        }
    }

    public static RuleRotate ROTATE_Y   = new RuleRotate(Axis.Y);
    public static RuleRotate ROTATE_ALL = new RuleRotate(Axis.X, Axis.Y, Axis.Z);
    public static RuleRotate NO_ROTATE  = new RuleRotate(Axis.X, Axis.Y, Axis.Z);
}

