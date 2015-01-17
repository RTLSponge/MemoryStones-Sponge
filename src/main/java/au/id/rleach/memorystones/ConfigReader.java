package au.id.rleach.memorystones;

import java.util.Collection;

/**
 * Created by Ryan on 13/01/2015.
 */
public interface ConfigReader {
    void loadConfigs();
    Collection<StoneTypes> getTypes();
    Collection<TravelAgent> getTeleports();
    Collection<EconomyStrategy> getEcons();
}
