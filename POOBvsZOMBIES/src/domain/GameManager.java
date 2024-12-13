package domain;

import presentation.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the game state, including the board, points, and game elements.
 * Implements a singleton pattern to ensure only one instance of GameManager.
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class GameManager {
    private Board board;
    private int sunPoints;
    private int brainPoints;
    private List<Zombies> zombies;
    private static GameManager instance;
    private Game game;

    /**
     * Constructor for objects of class GameManager
     */
    public GameManager(int rows, int cols) {
        board = new Board();
        sunPoints = 50;
    }

    /**
     * Sets the current game instance for the GameManager.
     *
     * @param game the Game instance to be set as the current game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Provides access to the singleton instance of the GameManager class.
     * If the instance is not already created, it initializes it with default
     * parameters.
     *
     * @return the singleton instance of GameManager
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager(5, 10);
        }
        return instance;
    }

    /**
     * Places a plant on the game board at the specified row and column location, deducting the sun points required
     * for the plant and updating the game's sun points display.
     *
     * @param plant the Plant object to be placed on the board
     * @param row the row index on the board where the plant should be placed
     * @param col the column index on the board where the plant should be placed
     * @return true if the plant is successfully placed and sun points are deducted, false otherwise
     */
    public boolean placePlant(Plant plant, int row, int col) {
        if (sunPoints >= plant.getSunCost()) {
            if (board.placePlant(plant, row, col)) {
                sunPoints -= plant.getSunCost();
                if (game != null) {
                    game.updateSunPointsLabel(sunPoints);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Attempts to place a zombie on the board at a specified row, deducting the required brain points
     * if the placement is successful.
     *
     * @param zombie the Zombies object to be placed on the board
     * @param row the row index on the board where the zombie should be placed
     * @param col the column index on the board where the zombie should be placed (currently ignored)
     * @return true if the zombie is successfully placed and brain points are deducted, false otherwise
     */
    public boolean placeZommbie(Zombies zombie, int row, int col) {
        if (brainPoints >= zombie.getCostBrains()) {
            if (board.placeZombie(zombie, row)) {
                brainPoints -= zombie.getCostBrains();
                if (game != null) {
                    //game.updateBrainPointsLabel(brainPoints);
                }
                return true;
            }
            return false;
        }
        return false;
    }


    /**
     * Updates the state of the game by moving all zombies on the board.
     * This method triggers the board's `moveZombies` function to shift all
     * zombies one step to the left. If any zombies are at the leftmost column,
     * they will activate a lawn mower for that row.
     *
     * After moving the zombies, the method checks if the list of zombies is empty,
     * indicating that the player has won the game since there are no remaining zombies.
     */
    public void updateGame() {
        board.moveZombies();
        if (zombies.isEmpty()) {
            //¡Ganaste! No quedan zombis
        }
    }

    /**
     * Retrieves the current number of sun points available in the game.
     *
     * @return the number of sun points currently available
     */
    public int getSunPoints() {
        return sunPoints;
    }


    /**
     * Collects a specified amount of sun points and updates the game's sun points label.
     *
     * @param amount the amount of sun points to be collected
     */
    public void collectSun(int amount) {
        sunPoints += amount;
        if (game != null) {
            game.updateSunPointsLabel(sunPoints);
        }
    }

    /**
     * Retrieves the current number of brain points available in the game.
     *
     * @return the number of brain points currently available
     */
    public int getBrainPointsPoints() {
        return brainPoints;
    }

    /**
     * Collects a specified amount of brain points and updates the game's sun points label if a game instance is set.
     *
     * @param amount the amount of brain points to be collected
     */
    public void collectBrain(int amount) {
        brainPoints += amount;
        if (game != null) {
            game.updateSunPointsLabel(sunPoints);
        }
    }

    /**
     * Attempts to place a zombie on the board at a specified position.
     *
     * @param zombieByName the Zombies object to be placed
     * @param row the row index on the board where the zombie should be placed
     * @param col the column index on the board where the zombie should be placed
     * @return true if the zombie is successfully placed, false otherwise
     */
    public boolean placeZombie(Zombies zombieByName, int row, int col) {

        return false;
    }
}