package au.id.rleach.memorystones;

import au.id.rleach.guice.InjectLogger;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.block.BlockLoc;
import org.spongepowered.api.event.block.BlockEvent;
import org.spongepowered.api.event.block.BulkBlockEvent;
import org.spongepowered.api.util.event.Subscribe;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class StoneEventHandler {

    @InjectLogger
    Logger logger;
    @Inject
    StoneManager stones;

    @Subscribe
    public void interact(org.spongepowered.api.event.block.BlockInteractEvent event){
        lookupMemoryStones(Stream.of(event.getBlock())).forEach(s -> s.interact(event));
        return;
    }

    @Subscribe
    public void change(org.spongepowered.api.event.block.BlockChangeEvent event){
        test(event, stone -> stone.interact(event));
    }

    private void test(BlockEvent e, Consumer<? super MemoryStone> consumer){
        lookupMemoryStones(Stream.of(e.getBlock())).forEach(consumer);
    }

    private Stream<MemoryStone> lookupMemoryStones(Stream<BlockLoc> blocks) {
        return blocks.flatMap(b -> stones.getListeningStones(b.getLocation())).distinct();
    }

    public void bulk(BulkBlockEvent event){
        //get all listening stones, if stone any should protect given location, return false.
        event.filter(
                blockLoc -> stones.getListeningStones(blockLoc.getLocation()).noneMatch(s -> s.bulk(event.getCause().orNull(), blockLoc)));
    }
}