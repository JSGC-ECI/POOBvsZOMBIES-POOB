package domain;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Represents a zombie with movement capabilities in the game. This class extends the
 * Zombies class and adds the ability to move across the game board, as well as attack plants.
 * Movement is an abstract class, allowing subclasses to implement specific zombie behaviors.
 *
 * Movement includes functionality for:
 * - Moving across the game board if the zombie is in a movable state.
 * - Attacking plants when encountering them on the game board.
 * - Managing attack timing with a scheduled executor.
 * - Stopping the attack timer when the attack ends.
 *
 * The Movement class is intended to be a base class for specific types of zombies
 * that require mobility and attack behavior.
 */
public abstract class Movement extends Zombies {
    private int damage;
    private boolean isMoving;
    private int timeattack;
    private int timemove;
    private ScheduledExecutorService scheduler;


    /**
     * Constructs a new Movement instance representing a zombie with the ability to move and attack.
     * Inherits basic zombie attributes from the Zombies class and adds specific properties
     * for movement and attack behavior.
     *
     * @param hitPoints the total health points of the zombie.
     * @param damage the amount of damage the zombie can inflict on plants.
     * @param costbrains the number of brains required to deploy this zombie.
     * @param timeattack the time interval, in seconds, between each attack on a plant.
     * @param timemove the time interval, in seconds, for the zombie to move across cells.
     */
    public Movement(int hitPoints, int damage, int costbrains, int timeattack, int timemove) {
        super(hitPoints, costbrains);
        this.damage = damage;
        this.isMoving = true;
        this.timeattack = timeattack;
        this.timemove = timemove;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }


    /**
     * Determines if the zombie is currently in a moving state.
     *
     * @return true if the zombie is moving, false otherwise.
     */
    public boolean isMoving() {
        return isMoving;
    }


    /**
     * Sets the moving state of the zombie.
     *
     * @param moving a boolean value indicating whether the zombie is in motion.
     *               If true, the zombie is marked as moving; if false, it is not moving.
     */
    public void setMoving(boolean moving) {
        isMoving = moving;
    }


    /**
     * Moves the zombie to a different cell on the game board, if possible. The method handles movement logic,
     * including removing the zombie from its current position, setting it in a new position if the adjacent cell
     * is empty, or initiating an attack if the adjacent cell contains a plant.
     *
     * @param board the game board on which the zombie is located.
     * @param row   the row index of the zombie's current position on the board.
     * @param col   the column index of the zombie's current position on the board.
     */
    public void move(Board board, int row, int col) {
        if (!isMoving) {
            return;
        }
        if (col > 0) {
            Cell currentCell = board.getCell(row, col);
            Cell leftCell = board.getCell(row, col - 1);
            if (leftCell.isEmpty()) {
                currentCell.removeZombie();
                leftCell.setZombie(this);
            } else if (leftCell.hasPlant()) {
                startAttack(leftCell);
            }
        }
    }


    /**
     * Initiates an attack cycle on the plant within the specified cell. This method stops the
     * movement of the calling entity and begins a scheduled attack on the plant present in
     * the provided cell. If the plant is no longer alive or missing, the attack cycle is
     * stopped, and movement is resumed.
     *
     * @param leftCell the cell containing the plant to attack. If the cell does not contain a plant
     *                 or the plant is not alive, the attack cycle will terminate.
     */
    private void startAttack(Cell leftCell) {
        if (!isMoving) {
            return;
        }
        isMoving = false;
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            Plant plant = leftCell.getPlant();
            if (plant != null && plant.isAlive()) {
                attack(plant);
            } else {
                isMoving = true;
                stopAttackTimer();
            }
        }, 0, timeattack, TimeUnit.SECONDS);
    }



    /**
     * Performs an attack on the specified plant. If the plant is alive, its hit points are reduced
     * by the damage value of the attacking entity.
     *
     * @param plant the Plant object being attacked. The plant's hit points will be decreased if it is alive.
     */
    public void attack(Plant plant) {
        if (plant.isAlive()) {
            plant.setHitPoints(plant.getHitPoints() - this.damage);
        }
    }

    /**
     * Retrieves the damage value of the zombie.
     *
     * @return the amount of damage the zombie can inflict on plants.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Stops the attack timer associated with the zombie's scheduled attack cycle.
     * If a scheduler exists and is not already shut down, this method will forcibly
     * terminate all active tasks and shut down the scheduler.
     *
     * This is typically used to halt any ongoing attack behavior, such as when the
     * zombie is no longer attacking a plant or the zombie is removed from the game.
     */
    public void stopAttackTimer() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

}

