package au.id.rleach.memorystones;

/**
 * Created by Ryan on 14/01/2015.
 */
public interface ArrivalStrategy {
    boolean canArrive(MemoryStone from, MemoryStone to);
}
