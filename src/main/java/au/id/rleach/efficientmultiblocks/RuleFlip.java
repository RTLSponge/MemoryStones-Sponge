package au.id.rleach.efficientmultiblocks;

import org.spongepowered.api.util.Axis;

import java.util.EnumMap;

public class RuleFlip {
    private final EnumMap<Axis,Boolean> map = new EnumMap<Axis,Boolean>(Axis.class);

    public RuleFlip() {
        for(Axis x:Axis.values()){
            map.put(x, false);
        }
    }

    public RuleFlip(Axis...axises){
        this();
        for(Axis x : axises){
            map.put(x,true);
        }
    }

    boolean getFlip(Axis x){
        return map.get(x);
    }
}
