package au.id.rleach.efficientmultiblocks.interfaces;

import au.id.rleach.efficientmultiblocks.Orientation;
import au.id.rleach.efficientmultiblocks.Reflexion;
import com.google.common.cache.LoadingCache;
import org.spongepowered.api.block.Block;
import org.spongepowered.api.world.World;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents an existing multiblock structure in the world.
 */
public abstract class AbstractMBObject {

    final World world;
    final IWorldCoordinate origin;
    final Orientation orientation;
    final Reflexion reflexion;
    final Collection<IEgoCoordinate> blocks;

    AbstractMBObject(World world, IWorldCoordinate origin, Orientation orientation, Reflexion reflexion, Collection<IEgoCoordinate> blocks) {
        this.world = world;
        this.origin = origin;
        this.orientation = orientation;
        this.reflexion = reflexion;
        this.blocks = new HashSet<IEgoCoordinate>(blocks);
    }

    abstract Block           getBlock (IEgoCoordinate offset, LoadingCache cache);
    abstract IEgoCoordinate translate(IWorldCoordinate coordinate);
    abstract IWorldCoordinate translate(IEgoCoordinate ego);
    abstract boolean         contains (IWorldCoordinate coordinate);


}
