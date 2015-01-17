package au.id.rleach.memorystones;

import com.google.common.base.Optional;

/**
 * Determines the prices of a memorystone.
 * Creation costs,
 * Teleport costs,
 * may be non-fixed depending on feature requests.
 */
public interface EconomyStrategy {
    Optional<Transaction> getCost(TeleportRequest teleportRequest);
}
