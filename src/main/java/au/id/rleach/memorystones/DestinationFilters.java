package au.id.rleach.memorystones;

import org.spongepowered.api.entity.player.Player;

/**
 * Created by Ryan on 13/01/2015.
 */
public class DestinationFilters {
    class Distance {

    }

    class Discovered implements ArrivalStrategy{

        @Override
        public boolean canArrive(Player p, MemoryStone from, MemoryStone to) {
            return DiscoveryTracker.hasPlayerDiscovered(Player p, to);
        }
    }

    class crossWorldBanned  implements ArrivalStrategy{

        @Override
        public boolean canArrive(Player p, MemoryStone from, MemoryStone to) {
            return from.getWorld() == to.getWorld();
        }
    }
}
