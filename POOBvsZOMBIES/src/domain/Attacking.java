package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Attacking extends Plant {
    private int attackPower;

    /**
     * Constructor for objects of class Attacking
     */
    public Attacking(int hitPoints, int cost, int attackPower, double time, int posX, int posY) {
        super(hitPoints, cost, 0, posY);
        this.attackPower = attackPower;
    }
    public abstract void atack();
}
