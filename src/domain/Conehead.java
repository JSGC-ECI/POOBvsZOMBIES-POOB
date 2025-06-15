package domain;

/**
 * Represents a Conehead zombie in the game. Conehead is a specific type of zombie with
 * higher hit points compared to basic zombies, wearing a cone as extra protection.
 * It inherits movement and attack characteristics from the Movement class.
 */
public class Conehead extends Movement{

    /**
     * Constructs a new Conehead zombie with predefined attributes.
     * The Conehead zombie has higher hit points and damage than basic zombies,
     * utilizing a cone for added defense. This constructor initializes the
     * Conehead object with specific values for its hit points, damage, attack
     * frequency, and movement speed, and sets its brain cost.
     */
    public Conehead() {
        super(380, 100, 150,10,10);
    }

    /**
     * Executes an attack on the specified plant. The attack decreases the plant's hit points
     * based on the damage value of this Conehead zombie. If the plant's hit points reach zero or below,
     * the plant is defeated. The attack may also affect the Conehead's movement logic depending on
     * the state of the plant.
     *
     * @param plant the plant to be attacked. If the plant is null or not alive, the attack
     *              will not be executed.
     */
    @Override
    public void attack(Plant plant) {

    }
}
