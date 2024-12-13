package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Zombies extends Character {

    private int costBrains;

    /**
     * Constructor for objects of class Zombies
     */
    public Zombies(int hitPointes, int costBrains) {
        super(hitPointes);
        this.costBrains = costBrains;

    }

    public int getCostBrains() {
        return costBrains;
    }
}
