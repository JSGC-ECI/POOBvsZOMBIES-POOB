package domain;


/**
 * Represents an abstract class for attacking plants in a game.
 * This class extends the Plant class and introduces an attack power attribute.
 */
public abstract class Attacking extends Plant {
    private int attackPower;


    /**
     * Constructs an Attacking object with specified hit points, cost, attack power, and time.
     *
     * @param hitPoints the hit points of the attacking plant.
     * @param cost the cost of deploying the attacking plant.
     * @param attackPower the attack power of the attacking plant.
     * @param time the time associated with the attacking action.
     */
    public Attacking(int hitPoints, int cost, int attackPower, double time) {
        super(hitPoints, cost);
        this.attackPower = attackPower;
    }

    public abstract void atack();
}
