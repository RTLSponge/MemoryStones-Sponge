package au.id.rleach.memorystones;

import com.google.common.base.Optional;

import java.util.stream.Stream;

public interface DestinationSelector {
    public Optional<? extends MemoryStone> selectDestination(Stream<? extends MemoryStone> destinations);
}