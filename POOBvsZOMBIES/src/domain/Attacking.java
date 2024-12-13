package domain;

/**
 * The Attacking class serves as an abstract base class for all plant types that have
 * the capability to perform an attack. It extends the Plant class and introduces
 * the concept of attack power.
 *
 * This class is intended to be subclassed by specific plants that implement the
 * abstract method `atack`, which defines the behavior of the attack.
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Attacking extends Plant {
    private int attackPower;

    /**
     * Constructor for objects of class Attacking
     */
    public Attacking(int hitPoints, int cost, int attackPower, double time) {
        super(hitPoints, cost);
        this.attackPower = attackPower;
    }
    /**
     * Defines the behavior of an attack for classes that extend the Attacking class.
     * Subclasses must implement this method to specify how the attack is executed.
     */
    public abstract void atack();
}
