package au.id.rleach.efficientmultiblocks;

import com.google.common.base.Preconditions;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.math.Vector3i;
import org.spongepowered.api.util.Axis;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Collection;
import java.util.HashSet;

/**
 * A BlockPattern with a world, co-ordinate and orientation.
 * e.g. a result, or known location of a pattern, or a potential result of a pattern.
 */
public class MBObject {
    private final World world;
    //
    private final HashSet<Vector3i> locations = new HashSet<Vector3i>();
    private final Orientation orientation;
    private final Reflexion reflexion;

    //World Co-ordinate.
    private final Vector3i origin;

    MBObject(World world, Collection<Vector3i> locations, Direction forwards, Direction up, Axis...x) {
        this.locations.addAll(locations);
        this.orientation = new Orientation(forwards, up);

        if (x == null) {
            this.reflexion = new Reflexion();
        }else {
            this.reflexion = new Reflexion(x);
        }
        if(!isValidDirection(forwards)){
            throw new IllegalArgumentException("Invalid fowards direction : "+forwards);
        }
        if(!isValidDirection(up)){
            throw new IllegalArgumentException("Invalid up direction : "+up);
        }
        Preconditions.checkNotNull(locations);
        this.world = world;
    }

    private boolean isValidDirection(Direction dir){
        return dir != null && (dir.isUpright() || dir.isCardinal());
    }

    public boolean eliminate(BlockType blockType, Vector3i offset) {
        Preconditions.checkNotNull(blockType);
        Preconditions.checkNotNull(offset);
        //If location found, and the blockstate does not match, return true.
        //if location is not found, block is not a part of this MBObject, therefore the block should have no effect
        //on this MBOjects elimination.
        return locations.contains(offset)
                && !checkBlock(offset, blockType);
    }

    private boolean checkBlock(Location loc, Vector3i offset, BlockType blockType) {
        world.getBlock(offset.toDouble());
        return world.getBlock(offset.add(pos)).getType().equals(blockType);
    }

    //takes a world co-ordinate and converts it to an offset.
    private Vector3i translateWorldCoordinate(Vector3i position){

    }

    //takes an offset and outputs a real world co-ordinate.
    private Vector3i translateEgoCoordinate(Vector3i offset){

    }
}