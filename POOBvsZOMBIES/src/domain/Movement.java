package domain;

/**
 * The Movement class is an abstract representation of zombies that can move and cause damage.
 * It extends the Zombies class and provides additional functionality specific to movement and attacking.
 * Classes that extend Movement need to implement the abstract attack method.
 */
public abstract class Movement extends Zombies {
    private int damage;

    private boolean isMoving = true;

    /**
     * Constructs a new Movement instance with specified hit points, damage, and brain cost.
     *
     * @param hitPoints the number of hit points the movement entity has, indicating its durability or health
     * @param damage the amount of damage the movement entity can inflict on other entities
     * @param costbrains the resource cost in brains required to utilize the movement entity within the game
     */
    public Movement(int hitPoints, int damage, int costbrains ) {
        super(hitPoints, costbrains);
        this.damage = damage;
        this.isMoving = isMoving;
    }

    /**
     * Returns the movement status of the entity.
     *
     * @return true if the entity is currently moving, false otherwise
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Sets the movement status of the entity.
     *
     * @param moving a boolean indicating whether the entity is moving (true) or not (false)
     */
    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    /**
     * Moves the entity within the specified board to a new position defined by the given row and column.
     *
     * @param board the game board on which the entity is moving
     * @param row the row index where the entity will be moved
     * @param col the column index where the entity will be moved
     */
    public void move(Board board, int row, int col) {

    }

    /**
     * Executes an attack on a specified plant, inflicting damage based on the attacking zombie's capabilities.
     *
     * @param plant the plant that is the target of the attack
     */
    private void attack(Plant plant) {
    }

    /**
     * Retrieves the damage value that the zombie can inflict upon attacking.
     *
     * @return the amount of damage this entity can deal to other entities
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Initiates an attack on a specified plant, inflicting damage as defined by the implementing class.
     *
     * @param plant the plant that serves as the target of the attack
     */
    public abstract void atack(Plant plant);
}
