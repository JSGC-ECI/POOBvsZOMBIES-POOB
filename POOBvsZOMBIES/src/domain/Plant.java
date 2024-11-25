package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Plant extends Character {

    /**
     * Constructor for objects of class Plant
     */
    public Plant(int hitPoints, int costSun, int posX, int posY) {
        super(hitPoints, costSun, posX, posY);
    }
}
