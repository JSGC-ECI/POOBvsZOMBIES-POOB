package domain;

/**
 * Represents a specialized type of zombie called ECIZombie, which is a child class
 * of the Movement class. This zombie has predefined characteristics such as
 * hit points, damage, and movement speed inherited from the Movement class.
 * It also has the ability to attack plants and shoot projectiles in the game.
 */
public class ECIZombie extends Movement {

    /**
     * Constructs an ECIZombie instance with predefined attributes.
     * This constructor initializes the ECIZombie with specific values for
     * hit points, damage, cost in brains, attack time, and movement time.
     * These values are passed to the superclass constructor of the Movement class.
     */
    public ECIZombie() {
        super(200, 50, 250,10,10);
    }

    /**
     * Executes an attack on the specified plant. The attack decreases the plant's hit points
     * by the amount of damage associated with this attacker. If the plant's hit points reach
     * zero or below and the plant is no longer alive, the attacker becomes movable.
     *
     * @param plant the plant to be attacked. If the plant is null or not alive, the attack
     *              does not proceed.
     */
    @Override
    public void attack(Plant plant) {
        if (plant != null && plant.isAlive()) {
            plant.setHitPoints(this.getDamage());
            if (!plant.isAlive()) {
                this.setMoving(true);
            }
        }
    }

    /**
     * Shoots a projectile from the ECIZombie on the specified board at the target location.
     * The projectile can impact the gameplay by damaging plants or interacting with other
     * entities present on the specified row and column.
     *
     * @param board the game board on which the projectile is shot. Represents the current game state.
     * @param row the row index on the board where the projectile is targeted.
     * @param col the column index on the board where the projectile is targeted.
     */
    public void shootProjectile(Board board, int row, int col) {

    }
}
