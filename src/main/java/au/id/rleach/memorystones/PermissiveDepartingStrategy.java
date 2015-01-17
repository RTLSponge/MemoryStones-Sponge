package au.id.rleach.memorystones;

/**
 * Created by Ryan on 15/01/2015.
 */
public class PermissiveDepartingStrategy implements DepartingStrategy{
    @Override
    public boolean canDepart(MemoryStone s) {
        return true;
    }
}
