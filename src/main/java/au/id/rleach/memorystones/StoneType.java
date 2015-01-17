package au.id.rleach.memorystones;

import au.id.rleach.efficientmultiblocks.BlockPattern;
import com.google.common.base.Optional;

import javax.annotation.Nullable;


/**
 * A Stone Type is a configurable template for MemoryStones.
 */
public class StoneType {
    private final String name;
    private final BlockPattern pattern;
    private Optional<EconomyStrategy> arrivalEcon;
    private Optional<EconomyStrategy> departingEcon;
    private Optional<ArrivalStrategy> arrivalStrategy;
    private Optional<DepartingStrategy> departingStrategy;
    private Optional<TravelAgent> travelAgent;

    /**
     *
     * @param name is the id used to keep track of type of memorystone, this should not be renamed.
     * @param pattern the block pattern to create new MemoryStones from.
     * @param arrivalEcon
     * @param departingEcon
     * @param arrivalStrategy
     * @param departingStrategy
     * @param travelAgent
     */
    public StoneType(String name, BlockPattern pattern, @Nullable EconomyStrategy arrivalEcon, @Nullable EconomyStrategy departingEcon, @Nullable ArrivalStrategy arrivalStrategy, @Nullable DepartingStrategy departingStrategy, @Nullable TravelAgent travelAgent) {
        this.name = name;
        this.pattern = pattern;
        this.arrivalEcon = Optional.fromNullable(arrivalEcon);
        this.departingEcon = Optional.fromNullable(departingEcon);
        this.arrivalStrategy = Optional.fromNullable(arrivalStrategy);
        this.departingStrategy = Optional.fromNullable(departingStrategy);
        this.travelAgent = Optional.fromNullable(travelAgent);
    }

    public EconomyStrategy getArrivalEcon() {
        return arrivalEcon.or(new FreeEcon());
    }

    public EconomyStrategy getDepartingEcon() {
        return departingEcon.or(new FreeEcon());
    }

    public ArrivalStrategy getArrivalStrategy() {
        return arrivalStrategy.or(new PermissiveArrivalStrategy());
    }

    public DepartingStrategy getDepartingStrategy() {
        return departingStrategy.or(new PermissiveDepartingStrategy());
    }

    public TravelAgent getTravelAgent() {
        return travelAgent.or(new SimpleTravelAgent());
    }
}
