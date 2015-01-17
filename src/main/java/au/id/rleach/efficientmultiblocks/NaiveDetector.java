package au.id.rleach.efficientmultiblocks;

import au.id.rleach.efficientmultiblocks.interfaces.AbstractBlockPattern;
import au.id.rleach.efficientmultiblocks.interfaces.AbstractMBObject;
import au.id.rleach.efficientmultiblocks.interfaces.PatternDetector;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.block.BlockState;
import java.util.Collection;

/**
 * Detects blockpatterns by using brute force.
 */
public class NaiveDetector implements PatternDetector {
    AbstractBlockPattern pattern;

    NaiveDetector(AbstractBlockPattern pattern){
        this.pattern = pattern;
    }

    @Override
    public Optional<AbstractMBObject> match(Block placed) {
        LoadingCache loadingCache = CacheBuilder.newBuilder().build(new NaiveDetector.CacheLoader(placed));
        Optional<AbstractMBObject> out;
        for(Transform t : Transform.getAll(pattern)){
            out = pattern.isAtLocation(placed, t, loadingCache);
            if(out.isPresent()) return out;
        }
        return Optional.absent();
    }

    static class CacheLoader extends com.google.common.cache.CacheLoader {
        private final Extent extent;
        private static final String __OBFID = "CL_00002023";

        public CacheLoader(Block block){
            this.extent = block.getExtent();
        }

        public Block loadState(Vector3d pos) {
            return extent.getBlock(pos);
        }

        public Object load(Object p_load_1_) {
            return this.loadState((Vector3d) p_load_1_);
        }
    }

}
