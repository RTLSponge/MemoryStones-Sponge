package au.id.rleach.memorystones;

import au.id.rleach.efficientmultiblocks.MBObject;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import org.spongepowered.api.block.BlockLoc;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.block.BlockChangeEvent;
import org.spongepowered.api.event.block.BlockInteractEvent;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Represents a specific memorystone in a specific location.
 */
public class MemoryStone {

    private UUID ID;

    //A location marking the origin of the multiblock stucture that made up this memory stone.
    private Location origin;
    //A location marking the approximate position that arriving players should be teleported to.
    private Location destination;
    //A multiblock object representing the remaining blocks of the MemoryStone.
    //Cached due to possible StoneType changes invaliding existing MemoryStones, maybe validate a memorystone whenever it is loaded?
    private MBObject object;
    //StoneType - Template that this stone initially inherited from.
    private StoneType type;
    //Optionals, if any of the following are absent, inherit from the StoneType.
    //EconomyOptions - Price to leave or arrive from here. Can be seperate.
    private Optional<EconomyStrategy> arrivalEcon;
    private Optional<EconomyStrategy> departingEcon;
    private Optional<ArrivalStrategy> arrivalStrategy;
    private Optional<DepartingStrategy> departingStrategy;
    private Optional<TravelAgent> travelAgent;
    //AABB - used to ensure the entire stone is loaded before validating that it exists
    private Vector3i min;
    private Vector3i max;

    private final StoneManager stones;

    private int listenRadius;

    public MemoryStone(Location origin, Location destination, MBObject object, StoneType type, int listenRadius) {
        super();
        new MemoryStone(origin, destination, object, type, null, null, null, null, null, listenRadius);
    }
    public MemoryStone(Location origin, Location destination, MBObject object, StoneType type, @Nullable EconomyStrategy arrivalEcon, @Nullable EconomyStrategy departingEcon, @Nullable ArrivalStrategy arrivalStrategy, @Nullable DepartingStrategy departingStrategy, @Nullable TravelAgent travelAgent, int listenRadius) {
        super();
        this.listenRadius = listenRadius;
        this.origin = origin;
        this.destination = destination;
        this.object = object;
        this.min = object.getLowerBounds();
        this.max = object.getUpperBounds();
        this.type = type;
        this.arrivalEcon = Optional.fromNullable(arrivalEcon);
        this.departingEcon = Optional.fromNullable(departingEcon);
        this.arrivalStrategy = Optional.fromNullable(arrivalStrategy);
        this.departingStrategy = Optional.fromNullable(departingStrategy);
        this.travelAgent = Optional.fromNullable(travelAgent);
    }


    /**
     * The listen radius is the distance that a stone will receive events happening near it.
     *
     * @return
     */
    int getListenRadius() {
        return this.listenRadius;
    }

    /**
     * get a stream of valid destinations that this stone can teleport to.
     * @return the stream of stones
     */
    public Stream<MemoryStone> getValidDestinations(){
        final Stream<MemoryStone> destinations =
                stones  .stream()
                        .filter(s -> this.getDepartingStrategy().canDepart(s))
                        .filter(s -> s.getArrivalStrategy().canArrive(this));
        return destinations;
    }

    public Location getDestination(){
        return destination;
    }

    public World getWorld(){
        return object.getWorld();
    }

    public EconomyStrategy getArrivalEcon() {
        return this.arrivalEcon.or(this.type.getArrivalEcon());
    }

    public ArrivalStrategy getArrivalStrategy() {
        return this.arrivalStrategy.or(this.type.getArrivalStrategy());
    }

    public DepartingStrategy getDepartingStrategy() {
        return this.departingStrategy.or(this.type.getDepartingStrategy());
    }

    public EconomyStrategy getDepartingEcon() {
        return this.departingEcon.or(this.type.getDepartingEcon());
    }

    public TravelAgent getTravelAgent() {
        return this.travelAgent.or(this.type.getTravelAgent());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("ID", this.ID)
                .add("origin", this.origin)
                .add("destination", this.destination)
                .add("object", this.object)
                .add("type", this.type)
                .add("arrivalEcon", this.arrivalEcon)
                .add("departingEcon", this.departingEcon)
                .add("arrivalStrategy", this.arrivalStrategy)
                .add("departingStrategy", this.departingStrategy)
                .add("travelAgent", this.travelAgent)
                .add("min", this.min)
                .add("max", this.max)
                .add("listenRadius", this.listenRadius)
                .toString();
    }
}