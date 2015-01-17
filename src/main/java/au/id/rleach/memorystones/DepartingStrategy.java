package au.id.rleach.memorystones;

import java.util.function.Predicate;

/**
 * Defines the strategy used for departing from this memorystone, permissions, cross-world etc.
 */
public interface DepartingStrategy {
    boolean canDepart(MemoryStone from, MemoryStone to);
}
