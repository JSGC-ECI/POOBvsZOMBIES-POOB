package domain;


/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Plant extends Character {

    private int sunCost;

    /**
     * Constructor for objects of class Plant
     */
    public Plant(int hitPoints, int costSun) {
        super(hitPoints);
        this.sunCost = costSun;
    }

    /**
     * Retrieves the sun cost required for deploying the plant.
     *
     * @return the sun cost of the plant.
     */
    public int getSunCost() {
        return sunCost;
    }
}