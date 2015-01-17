package au.id.rleach.efficientmultiblocks;

import org.spongepowered.api.util.Axis;

import javax.annotation.Nullable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Reflexion {

    //null represents the non reflection.
    @Nullable
    private final Axis axis;

    private Reflexion() {
        super();
        this.axis = null;
    }

    private Reflexion(final Axis axisIn) {
        super();
        this.axis = axisIn;
    }

    int getFlip(Axis x){
        return x == axis ? -1 : 1;
    }

    static Set<Reflexion> all(){
        return all(new Reflexion());
    }

    static Set<Reflexion> all(final Reflexion r){
        final Set<Reflexion> out = new LinkedHashSet<Reflexion>();
        //Loop over all values for each axis.
        //make r first.
        out.add(r);
        out.add(new Reflexion());
        //as out is a set, r can not be added twice.
        for(Axis x : Axis.values()){
            out.add(new Reflexion(x));
        }
        assert(out.size() == 4);
        return out;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Reflexion reflexion = (Reflexion) obj;

        if (axis != reflexion.axis) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        return axis != null ? axis.hashCode() : 0;
    }
}
