package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Movement extends Zombies {
    private int damage;

    private boolean isMoving = true;

    /**
     * Constructor for objects of class Movement
     */
    public Movement(int hitPoints, int posY, int damage, int costbrains ) {
        super(hitPoints,posY, costbrains);
        this.damage = damage;
        this.isMoving = isMoving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getDamage() {
        return damage;
    }
    public abstract void atack();
}
