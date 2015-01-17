package au.id.rleach.memorystones;

import java.util.Collection;
import java.util.Collections;

public class ConfigReaderImpl implements ConfigReader{

    @Override
    public void loadConfigs() {

    }

    @Override
    public Collection<StoneTypes> getTypes() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Collection<TravelAgent> getTeleports() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Collection<EconomyStrategy> getEcons() {
        return Collections.EMPTY_LIST;
    }
}
