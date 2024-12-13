package domain;

/**
 * The Zombies class is an abstract representation of a zombie character in a game.
 * Zombies are defined by attributes such as hit points and the cost in brains
 * required to use them in gameplay. The class extends the Character class, inheriting
 * basic character traits and behaviors. Zombies are integral to gameplay and must
 * implement additional specifications when subclassed.
 */
public abstract class Zombies extends Character {

    private int costBrains;

    /**
     * Constructs a Zombies object with specified hit points and brain cost,
     * representing a zombie in the game. This constructor initializes the zombie
     * with certain attributes required for its functionality.
     *
     * @param hitPointes the initial amount of hit points for the zombie, determining its endurance.
     * @param costBrains the amount of brain resources required to deploy or utilize the zombie in gameplay.
     */
    public Zombies(int hitPointes, int costBrains) {
        super(hitPointes);
        this.costBrains = costBrains;

    }

    /**
     * Returns the cost in brains required to use this zombie in gameplay.
     *
     * @return the number of brain resources required to deploy or utilize the zombie.
     */
    public int getCostBrains() {
        return costBrains;
    }
}
