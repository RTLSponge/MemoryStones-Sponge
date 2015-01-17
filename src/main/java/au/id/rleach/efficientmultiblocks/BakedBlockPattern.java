package au.id.rleach.efficientmultiblocks;

import com.google.common.base.Optional;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.math.Vector3i;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class BakedBlockPattern {
    //All possible MBObjects
    private final Collection<MBObject> possibilities;
    //MBObjects that have not yet been eliminated.
    private Collection<MBObject> remaining;


    public BakedBlockPattern(TreeSet<MBObject> possibilities) {
        this.possibilities = possibilities;
        this.resetRemaining();
    }

    public void resetRemaining(){
        this.remaining = new TreeSet<MBObject>();
        this.remaining.addAll(possibilities);
    }

    //return Optional.absent if the result is still inconclusive? TODO: WORK OUT WHEN THIS ENDS
    public Optional<MBObject> eliminate(BlockType blockType,Vector3i offset){
        for (Iterator<MBObject> iterator = possibilities.iterator(); iterator.hasNext(); ) {
            MBObject next = iterator.next();
            next.eliminate(blockType, offset);
        }

        return Optional.absent();
    }
}
