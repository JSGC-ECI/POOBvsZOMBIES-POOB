package domain;

/**
 * Represents an abstract Zombie entity within the game. The Zombies class extends
 * the Character class, inheriting basic attributes such as health points while adding
 * unique properties such as cost in brains. This class serves as a base for different
 * types of zombies with additional functionality to be implemented in subclasses.
 */
public abstract class Zombies extends Character {

    private int costBrains;

    /**
     * Constructs a new Zombies object with specified hit points and cost in brains.
     * This constructor initializes the zombie's health points and its associated cost.
     *
     * @param hitPointes the initial health points of the zombie. This determines the starting
     *                   health of the zombie and should be a positive integer.
     * @param costBrains the cost in brains required to create this zombie. This value
     *                   represents the resource cost associated with this zombie.
     */
    public Zombies(int hitPointes, int costBrains) {
        super(hitPointes);
        this.costBrains = costBrains;

    }

    /**
     * Retrieves the cost of the zombie in terms of brains, which represents the resource
     * expenditure required to deploy this zombie in the game.
     *
     * @return the cost in brains for this zombie.
     */
    public int getCostBrains() {
        return costBrains;
    }


    /**
     * Creates a Zombies object from a comma-separated string representation.
     * The string should contain the zombie's name, health points, and cost in brains
     * separated by commas. The method parses these values to create and return a new instance
     * of the CustomZombie class.
     *
     * @param line a string containing the information to construct a Zombies object,
     *             formatted as "name,health,costBrains", where:
     *             - name is the name of the zombie (String)
     *             - health is the number of health points (int)
     *             - costBrains is the cost in brains (int*/
    public static Zombies fromString(String line) {
        String[] parts = line.split(",");
        String name = parts[0].trim();
        int health = Integer.parseInt(parts[1].trim());
        int attackPower = Integer.parseInt(parts[2].trim());
        return new CustomZombie(name, health, attackPower);
    }

    /**
     * Returns a string representation of the zombie object.
     * The representation includes the class name, the current hit points,
     * and the cost in brains required for the zombie, separated by commas.
     *
     * @return a string in the format "ClassName,hitPoints,costBrains".
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "," + super.getHitPoints() + "," + getCostBrains();
    }

}

/**
 * Represents a custom zombie entity in the game. This class extends the abstract
 * Zombies class, enabling the creation of specific zombie types with configurable
 * attributes such as name, hit points, and cost in brains.
 */
class CustomZombie extends Zombies {
    /**
     * Constructs a new CustomZombie object with specified attributes.
     *
     * @param name       the name of the zombie. This provides a unique identifier or description
     *                   for the specific zombie type.
     * @param hitPoints  the initial health points of the zombie. Determines how much damage
     *                   the zombie can withstand before being eliminated.
     * @param costBrains the cost in brains required to create this zombie. Represents the
     *                   resource cost associated with spawning the zombie.
     */
    public CustomZombie(String name, int hitPoints, int costBrains) {
        super(hitPoints, costBrains);
    }
}
