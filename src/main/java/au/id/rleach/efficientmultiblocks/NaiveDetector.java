package au.id.rleach.efficientmultiblocks;

import au.id.rleach.efficientmultiblocks.interfaces.BlockMatcher;
import au.id.rleach.efficientmultiblocks.interfaces.IBlockPattern;
import au.id.rleach.efficientmultiblocks.interfaces.IMBObject;
import au.id.rleach.efficientmultiblocks.interfaces.PatternDetector;
import com.google.common.base.Optional;
import javafx.util.Pair;
import org.spongepowered.api.block.Block;

import java.util.Collection;
import java.util.Iterator;

/**
 * Detects blockpatterns by using brute force.
 */
public class NaiveDetector implements PatternDetector {
    IBlockPattern pattern;

    NaiveDetector(IBlockPattern pattern){
        this.pattern = pattern;
    }



    @Override
    public Optional<IMBObject> match(Block block) {
        Collection<Transform> transforms = Transform.getAll(pattern);

        for(Transform t : transforms){

        }

        return Optional.absent();

    }


}
