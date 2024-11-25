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
    public Zombies(int hitPointes, int posY, int costBrains) {
        super(hitPointes,costBrains , 9, posY);

    }
}
