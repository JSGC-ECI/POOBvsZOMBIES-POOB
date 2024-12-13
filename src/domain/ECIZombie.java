package domain;


/**
 * The ECIZombie class represents a specific type of zombie character in the game.
 * This class is responsible for handling attack behavior and shooting projectiles
 * on the game board.
 */
public class ECIZombie extends Movement {


    /**
     * Constructs an instance of the ECIZombie class with specified health points, damage, and cost.
     * This constructor initializes the zombie with attributes that define its strength and
     * the resources it utilizes within the game. It calls the superclass constructor with
     * predetermined values that specify the hit points, damage, and brain cost for this specific type of zombie.
     */
    public ECIZombie() {
        super(200, 50, 250);
    }

    /**
     * Executes an attack on the specified plant, reducing its hit points by the zombie's damage value.
     * If the plant is no longer alive after the attack, the zombie is set to a moving state.
     *
     * @param plant the plant that is being attacked
     */
    @Override
    public void atack(Plant plant) {
        if (plant != null && plant.isAlive()) {
            plant.setHitPoints(this.getDamage());
            if (!plant.isAlive()) {
                this.setMoving(true);
            }
        }
    }
    /**
     * Shoots a projectile from the ECIZombie's current position on the game board.
     * This method calculates and executes the trajectory of the projectile aimed
     * towards a specific location on the board.
     *
     * @param board the game board on which the projectile is to be shot
     * @param row the row coordinate on the board where the projectile is aimed
     * @param col the column coordinate on the board where the projectile is aimed
     */
    public void shootProjectile(Board board, int row, int col) {

    }
}
