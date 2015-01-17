package au.id.rleach.memorystones;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.world.Location;

import java.util.Collection;

/**
 * A TravelAgent is responsible for teleporting the player, scheduling location changes, animations, cooldowns etc
 * it should also make sure that teleportations are completed before a server stops, or a player logs out, else refund
 * a player the costs.
 * It is also responsible for finding a safe location, or may be strict and take the player to the location no matter what.
 */
public interface TravelAgent {
    /**
     *
     * @param e a collection of entitys that should be transported.
     * @param from the destination from which the entities are teleporting from (can be used for relative position teleports)
     * @param to the (approximate) destination that the entities should be teleported to.
     * @param t the payment for this teleport (should be refunded if teleport is unable to be completed.
     */
    void teleport(Collection<? extends Entity> e, MemoryStone from, MemoryStone to, Transaction t);
}
