package au.id.rleach.efficientmultiblocks;

import org.spongepowered.api.util.Axis;

import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Reflexion {
    private final EnumMap<Axis, Boolean> map = new EnumMap<Axis,Boolean>(Axis.class);

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

    public Reflexion(Reflexion r, boolean x, boolean y, boolean z){
        //^ is xor
        map.put(Axis.X, x ^ r.map.get(Axis.X));
        map.put(Axis.Y, y ^ r.map.get(Axis.Y));
        map.put(Axis.Z, z ^ r.map.get(Axis.Z));
    }

    int getFlip(Axis x){
        return map.get(x) ? -1 : 1;
    }


    static Set<Reflexion> all(){
        return all(new Reflexion());
    }

    static Set<Reflexion> all(Reflexion r){
        Set<Reflexion> out = new LinkedHashSet<Reflexion>();
        //Loop over all values for each axis.
        boolean[] tf = {true, false};
        for(boolean x : tf){
            for(boolean y : tf){
                for(boolean z : tf){
                    out.add(new Reflexion(r, x, y, z));
                }
            }
        }
        return out;
    }
}
