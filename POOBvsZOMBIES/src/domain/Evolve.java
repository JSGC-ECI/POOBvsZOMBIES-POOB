package domain;

/**
 * Represents an advanced attacking plant in the game that evolves from the basic attributes
 * provided by the Attacking class. This class specifies unique hit points, cost, attack
 * power, and time characteristics of the evolved plant.
 */
public class Evolve extends Attacking{
    /**
     * Constructs an instance of the Evolve class, an extension of the Attacking plant,
     * with predefined attributes for hit points, cost, attack power, and time.
     *
     * This constructor calls the superclass constructor with the following parameters:
     * - hit points: 500
     * - cost: 200
     * - attack power: 20
     * - time: 20
     *
     * The Evolve class represents an advanced attacking plant with specific characteristics,
     * designed to deal significant damage and sustain a notable level of durability in-game.
     */
    public Evolve() {
        super(500, 200, 20, 20);
    }

    /**
     * Executes an attack specific to the evolved plant behavior.
     * This method overrides the base `atack` method, introducing the
     * unique attack mechanics associated with plants of type Evolve.
     *
     * The implementation leverages special traits of the Evolve class,
     * including its attack power, hit points, or other custom actions,
     * to interact with other game entities such as zombies or cells.
     */
    @Override
    public void atack() {

    }
}
