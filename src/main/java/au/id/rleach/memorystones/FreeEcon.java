package au.id.rleach.memorystones;

import com.google.common.base.Optional;

public class FreeEcon implements EconomyStrategy {
    @Override
    public Optional<Transaction> getCost(TeleportRequest teleportRequest) {
        return Optional.absent();
    }
}
