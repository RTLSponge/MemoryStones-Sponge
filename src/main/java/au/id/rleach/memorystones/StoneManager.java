package au.id.rleach.memorystones;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.world.Location;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Ryan on 13/01/2015.
 */
public interface StoneManager {
    /**
     * Gets the stone at the specified position if it exists.
     * @param loc the block location.
     * @return the stone if found.
     */
    Optional<MemoryStone> getStone(Location loc);

    Stream<MemoryStone> getListeningStones(Location location);

    Stream<MemoryStone> stream();
}
