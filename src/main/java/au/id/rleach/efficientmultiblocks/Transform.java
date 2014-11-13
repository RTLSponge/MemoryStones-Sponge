package au.id.rleach.efficientmultiblocks;

import au.id.rleach.efficientmultiblocks.interfaces.EgoCoordinate;
import au.id.rleach.efficientmultiblocks.interfaces.IBlockPattern;
import org.spongepowered.api.util.Axis;
import org.spongepowered.api.util.Direction;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Transform {
    final Reflexion reflexion;
    final Orientation orientation;

    Transform(Reflexion reflexion, Orientation orientation, EgoCoordinate offset){
        this.reflexion = reflexion;
        this.orientation = orientation;
    }

    public static Collection<Transform> getAll(IBlockPattern pattern) {
        Set<Transform> out = new LinkedHashSet<Transform>();

        //TODO: Add some logic to sort these smartly with the patterns defaults.
        //TODO: Make this obey the Rules.
        for(Orientation o : getOrientationsFromDefault(pattern.getOrientation())){
            for(Reflexion r : Reflexion.all(pattern.getReflexion())) {
                for(EgoCoordinate offset : pattern.getTranslations()) {
                    out.add(new Transform(r, o, offset));
                }
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
