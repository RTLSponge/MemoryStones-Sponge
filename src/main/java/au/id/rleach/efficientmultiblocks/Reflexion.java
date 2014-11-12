package au.id.rleach.efficientmultiblocks;

import org.spongepowered.api.util.Axis;

import java.util.EnumMap;

public class Reflexion {
    private final EnumMap<Axis,Boolean> map = new EnumMap<Axis,Boolean>(Axis.class);

    public Reflexion() {
        for(Axis x:Axis.values()){
            map.put(x, false);
        }
    }

    public Reflexion(Axis...axises){
        this();
        for(Axis x : axises){
            map.put(x,true);
        }
    }

    int getFlip(Axis x){
        return map.get(x) ? -1 : 1;
    }
}
