package au.id.rleach.memorystones;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.world.Location;

import java.util.Collection;

/**
 * A dangerous travel agent, doesn't check to see if the landing is safe, or is even in air,
 * teleports entities relative to the location they were teleported from, and to, makes for cool effects.
 * disregards transactions, doesn't care if the transaction was successful, or how much was paid.
 */
public class SimpleTravelAgent implements TravelAgent {
    @Override
    public void teleport(Collection<? extends Entity> entities, MemoryStone from, MemoryStone to, Transaction t) {
        for(Entity e:entities){
            final Location loc = e.getLocation();
            final Vector3d difference = from.getDestination().getPosition().sub(loc.getPosition());
            e.setLocation(to.getDestination().add(difference));
        }
    }
}
