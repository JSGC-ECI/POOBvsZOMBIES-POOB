package domain;

/**
 * Represents the Buckethead zombie character, which is a type of zombie with
 * high hit points, capable of moving across the game board and attacking plants.
 * Buckethead is a specialization of the {@code Movement} class and inherits
 * its movement and attack behavior.
 *
 * The Buckethead zombie has the following characteristics:
 * - High hit points: 800.
 * - Attack damage: 100.
 * - Movability and attack timing parameters, inherited from {@code Movement}.
 *
 * This class provides the implementation for the zombie's specific attack
 * behavior against plants through the overridden {@code attack} method.
 */
public class Buckethead extends Movement{

    /**
     * Constructs a new Buckethead zombie with the following characteristics:
     * - Hit Points: 800.
     * - Damage: 100.
     * - Cost in brains: 200.
     * - Time between attacks: 10.
     * - Time between moves: 10.
     *
     * This constructor initializes the Buckethead zombie by invoking the
     * constructor of its superclass {@code Movement}. The specified parameters
     * define the unique attributes of the Buckethead character, making it a
     * highly durable and effective attacker within the game.
     */
    public Buckethead() {
        super(800, 100, 200,10,10);
    }


    /**
     * Executes an attack on the specified plant. This method decreases the plant's
     * hit points by the amount of damage dealt by the Buckethead zombie. If the
     * plant's hit points are reduced to zero or below, the plant is considered
     * defeated, and further gameplay actions can proceed accordingly.
     *
     * @param plant the plant to be attacked. If the plant is null or not alive,
     *              the attack will not be executed.
     */
    @Override
    public void attack(Plant plant) {

    }
}
