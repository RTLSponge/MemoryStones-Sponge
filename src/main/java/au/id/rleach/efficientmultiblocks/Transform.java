package au.id.rleach.efficientmultiblocks;

import au.id.rleach.efficientmultiblocks.interfaces.AbstractBlockPattern;
import au.id.rleach.efficientmultiblocks.interfaces.IEgoCoordinate;
import org.spongepowered.api.block.Block;
import org.spongepowered.api.math.Vector3d;
import org.spongepowered.api.util.Direction;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class Transform {
    final Reflexion reflexion;
    final Orientation orientation;
    final IEgoCoordinate offset;

    /**
     * assumes the origin is 0,0,0 egocentric.
     * **/
    Transform(Reflexion reflexion, Orientation orientation, IEgoCoordinate offset){
        this.reflexion = reflexion;
        this.orientation = orientation;
        this.offset = offset;

    }

    public static Collection<Transform> getAll(AbstractBlockPattern pattern) {
        Set<Transform> out = new LinkedHashSet<Transform>();

        //TODO: Add some logic to sort these smartly with the patterns defaults.
        //TODO: Make this obey the Rules.
        for(Orientation o : getOrientationsFromDefault(pattern.getOrientation())){
            for(Reflexion r : Reflexion.all(pattern.getReflexion())) {
                for(IEgoCoordinate offset : pattern.getPositions()) {
                    out.add(new Transform(r, o, offset.invert()));
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

    public Vector3d transform(Block block) {
        return block.getPosition().add(result).toDouble();
    }
}
