package au.id.rleach.memorystones;

import org.spongepowered.api.world.World;

import java.util.Collection;

/**
 * Should be kept in a world folder, but failing that store it in the plugin directory with sub folders.
 */
public interface WorldData {
    Collection<MemoryStone> loadMemoryStones(World world);
}
