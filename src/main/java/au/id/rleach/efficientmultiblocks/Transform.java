package au.id.rleach.efficientmultiblocks;

import au.id.rleach.efficientmultiblocks.interfaces.IBlockPattern;
import org.spongepowered.api.util.Axis;
import org.spongepowered.api.util.Direction;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Ryan on 14/11/2014.
 */
public class Transform {
    final Reflexion reflexion;
    final Orientation orientation;

    Transform(Reflexion reflexion, Orientation orientation){
        this.reflexion = reflexion;
        this.orientation = orientation;
    }

    public static Collection<Transform> getAll(IBlockPattern pattern) {
        Set<Transform> out = new LinkedHashSet<Transform>();

        //TODO: Add some logic to sort these smartly with the patterns defaults.
        //TODO: Make this obey the Rules.
        for(Orientation o : getOrientationsFromDefault(pattern.getOrientation())){
            for(Reflexion r : Reflexion.all(pattern.getReflexion())) {
                out.add(new Transform(r,o));
            }
        }
        return out;
    }

    public static Set<Orientation> getOrientationsFromDefault(Orientation o){

        Set<Orientation> all = new LinkedHashSet<Orientation>();
        for(Direction u:o.up()){
            for(Direction f:o.forwards()){
                if(u.equals(f) || u.isOpposite(f)){
                    continue;
                }
                all.add(new Orientation(u,f));
            }
        }
        return all;
    }
}
