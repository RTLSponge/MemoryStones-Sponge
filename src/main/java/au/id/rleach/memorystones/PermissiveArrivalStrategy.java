package au.id.rleach.memorystones;

/**
 * Created by Ryan on 15/01/2015.
 */
public class PermissiveArrivalStrategy implements ArrivalStrategy {
    @Override
    public boolean canArrive(MemoryStone memoryStone) {
        return true;
    }
}
