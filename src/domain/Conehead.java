package domain;


/**
 * Represents a Conehead zombie, a specific type of zombie known for its
 * increased durability due to wearing a traffic cone on its head. Inherits
 * from the Movement class, allowing for movement and attack capabilities
 * with additional health points and damage.
 */
public class Conehead extends Movement{


    /**
     * Constructs a new instance of the Conehead class, which represents a
     * specific type of zombie with predefined attributes such as hit points,
     * damage, and cost in brains. This constructor initializes the Conehead with
     * specific values and passes them to the superclass constructor.
     */
    public Conehead() {
        super(380, 100, 150);
    }


    /**
     * Performs an attack on the specified plant object. This method defines the behavior
     * of the attacking entity when interacting with a plant. The precise effect of the
     * attack is determined by the implementation provided in the subclass.
     *
     * @param plant the plant object to be attacked, which may involve reducing the plant's
     *              hit points or applying other effects as defined by the subclass implementation.
     */
    @Override
    public void atack(Plant plant) {

    }
}
