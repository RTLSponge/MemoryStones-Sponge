package au.id.rleach.efficientmultiblocks.interfaces;

import au.id.rleach.efficientmultiblocks.BlockPattern;
import au.id.rleach.efficientmultiblocks.MBObject;
import au.id.rleach.efficientmultiblocks.Orientation;
import au.id.rleach.efficientmultiblocks.Reflexion;
import com.google.common.cache.LoadingCache;
import org.spongepowered.api.block.Block;
import org.spongepowered.api.math.Vector3i;
import org.spongepowered.api.world.World;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents an existing multiblock structure in the world.
 */
public abstract class IMBObject {

    final World world;
    final WorldCoordinate origin;
    final Orientation orientation;
    final Reflexion reflexion;
    final Collection<EgoCoordinate> blocks;

    IMBObject(World world, WorldCoordinate origin, Orientation orientation, Reflexion reflexion, Collection<EgoCoordinate> blocks) {
        this.world = world;
        this.origin = origin;
        this.orientation = orientation;
        this.reflexion = reflexion;
        this.blocks = new HashSet<EgoCoordinate>(blocks);
    }

    abstract Block           getBlock (EgoCoordinate offset, LoadingCache cache);
    abstract EgoCoordinate   translate(WorldCoordinate coordinate);
    abstract WorldCoordinate translate(EgoCoordinate ego);
    abstract boolean         contains (WorldCoordinate coordinate);


}
