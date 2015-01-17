package au.id.rleach.memorystones;
import au.id.rleach.guice.LoggingModule;
import com.google.common.base.Objects;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.PostInitializationEvent;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerAboutToStartEvent;
import org.spongepowered.api.event.state.ServerStartingEvent;
import org.spongepowered.api.event.state.ServerStoppedEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.api.service.config.ConfigDir;
import org.spongepowered.api.service.config.DefaultConfig;
import org.spongepowered.api.util.config.ConfigFile;
import org.spongepowered.api.util.event.Subscribe;

import java.io.File;

//Requires public constructor to be constructed by sponge.
@SuppressWarnings({"FieldNotUsedInToString", "PublicConstructor"})
@org.spongepowered.api.plugin.Plugin(id = Plugin.PLUGIN_ID, name = Plugin.PLUGIN_ID, version= Plugin.VERSION)
public class Plugin {

    public static final String PLUGIN_ID = "MemoryStones"; //NON-NLS
    public static final String VERSION   = "0.0.1-SNAPSHOT"; //NON-NLS
    private static final boolean SHARED_ROOT = true;

    private final Logger logger;
    private final Game game;
    private final GameRegistry registry;
    private final PluginContainer container;
    private final PluginManager pluginManager;
    private final File pluginDir;
    private final ConfigFile configFile;

    private final Injector inj;

    @Inject
    public Plugin(
                                                            final Logger          loggerInj        ,
                                                            final Game            gameInj          ,
                                                            final GameRegistry    registryInj      ,
                                                            final PluginContainer containerInj     ,
                                                            final PluginManager   pluginManagerInj ,
            @ConfigDir    (sharedRoot = Plugin.SHARED_ROOT) final File            pluginDirInj     ,
            @DefaultConfig(sharedRoot = Plugin.SHARED_ROOT) final ConfigFile      configFileInj    )
    {
        super();
        this.logger = loggerInj;
        this.game = gameInj;
        this.registry = registryInj;
        this.container = containerInj;
        this.pluginManager = pluginManagerInj;
        this.pluginDir = pluginDirInj;
        this.configFile = configFileInj;
        this.inj = Guice.createInjector(new LoggingModule());
    }

    // =======================================================================================
    //  Event's that run *once* per minecraft lifecycle.
    // =======================================================================================

    @Subscribe
    public void preInit(final PreInitializationEvent event){
        //Fired when your plugin should load resources needed before initialization.
    }

    @Subscribe
    public void init(final InitializationEvent event){
        //Fired when events should be registered, commands registered etc.
    }

    @Subscribe
    public void postInit(final PostInitializationEvent event){
        //Fired when plugins should start asking each other for resources, including querying for services.
        logger.info("Memory Stones is starting up");
        logger.debug(this.toString());
    }

    // ===============================================================================================
    //  The following methods are run whenever a server is restarting, this may happen multiple times.
    // ===============================================================================================
    @Subscribe
    public void preWorldLoad(final ServerAboutToStartEvent event){

    }

    @Subscribe
    public void postWorldLoad(final ServerStartingEvent event){

    }

    @Subscribe
    public void beforeFinalTick(final ServerStoppingEvent event){
        //(re)Schedule tasks that must resolve ingame before server stops.
        //before world save.
    }

    @Subscribe
    public void stopped(final ServerStoppedEvent event){
        //Final cleanup before server starts up again (or is shutdown)
        //May run at *any* stage on error condition runs after no more world changes will be made.
    }

    @Override
    public String toString() {
        final String superString = super.toString();
        //Objects is deprecated in future guava versions, change to MoreObjects if sponge updates guava
        return Objects.toStringHelper(this)
                .add("superString"  , superString        )
                .add("pluginID"     , Plugin.PLUGIN_ID   )
                .add("pluginVersion", Plugin.VERSION     )
                .add("Shared"       , Plugin.SHARED_ROOT )
                .toString();
    }
}
