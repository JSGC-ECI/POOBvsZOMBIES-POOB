package domain;

import presentation.Game;

import java.io.*;
import java.util.List;

/**
 * The GameManager class is responsible for managing the game's state, resources, and interactions.
 * It is implemented as a singleton, allowing only one instance to be used throughout the game.
 */
public class GameManager {
    private Board board;
    private int sunPoints;
    private int brainPoints;
    private List<Zombies> zombies;
    private static GameManager instance;
    private Game game;
    private int gameTime;


    /**
     * Constructs a GameManager instance with specified initial resources and time.
     *
     * @param initialSunPoints the initial amount of sun points available for the game
     * @param initialBrainPoints the initial amount of brain points available for the game
     * @param initialTime the initial game time in seconds
     */
    public GameManager(int initialSunPoints, int initialBrainPoints,int initialTime ) {
        board = new Board(initialSunPoints, initialBrainPoints);
        this.sunPoints = initialSunPoints;
        this.brainPoints = initialBrainPoints;
        this.gameTime = initialTime;
    }

    /**
     * Sets the current game instance for the GameManager.
     *
     * @param game the Game object to be set as the current game
     */
    public void setGame(Game game) {
        this.game = game;
    }


    /**
     * Returns the singleton instance of the GameManager. If the instance does not already
     * exist, it initializes a new GameManager with default values for sun points, brain points,
     * and game time.
     *
     * @return the single instance of GameManager
     */
    public static GameManager getInstance() {
        if (instance == null) {
            int defaultSunPoints = 100;
            int defaultBrainPoints = 50;
            int defaultGameTime = 300;

            instance = new GameManager(defaultSunPoints, defaultBrainPoints, defaultGameTime);
        }
        return instance;
    }


    /**
     * Sets the game time to the specified value.
     *
     * @param gameTime the new game time in seconds
     */
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }


    /**
     * Attempts to place a plant on the game board at the specified position.
     * It first checks if there are enough sun points available to pay for the plant's cost.
     * If successful, it deducts the plant's sun cost from the total sun points,
     * updates the sun points display, and places the plant on the board.
     *
     * @param plant the plant to be placed
     * @param row the row index where the plant should be placed
     * @param col the column index where the plant should be placed
     * @return true if the plant was successfully placed, false otherwise
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
     * Attempts to place a zombie on the game board at the specified row.
     * This method checks if the player has enough brain points to cover the cost of the zombie.
     * If there are sufficient brain points and the zombie can be successfully placed on the board,
     * the cost is deducted from the player's brain points.
     *
     * @param zombie the zombie to be placed
     * @param row the row index where the zombie should be placed
     * @param col the column index, which is considered in this method
     * @return true if the zombie was successfully placed; false if there are not enough brain points
     *         or if the placement on the board was unsuccessful
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
     * Updates the game state by moving zombies on the board and checking for game victory conditions.
     * <ul>
     * <li>Calls the {@code moveZombies} method on the game board to advance the position of zombies.</li>
     * <li>If there are no zombies remaining on the board, the player wins the game.</li>
     * </ul>
     */
    public void updateGame() {
        board.moveZombies();
        if (zombies.isEmpty()) {
            //¡Ganaste! No quedan zombis
        }
    }

    /**
     * Returns the current amount of sun points available in the game.
     *
     * @return the number of sun points
     */
    public int getSunPoints() {
        return sunPoints;
    }


    /**
     * Increases the total amount of sun points by the specified amount.
     * This method also updates the sun points label in the current game interface.
     *
     * @param amount the number of sun points to be added
     */
    public void collectSun(int amount) {
        sunPoints += amount;
        if (game != null) {
            game.updateSunPointsLabel(sunPoints);
        }
    }

    /**
     * Returns the current amount of brain points available in the game.
     *
     * @return the number of brain points
     */
    public int getBrainPointsPoints() {
        return brainPoints;
    }


    /**
     * Increases the total amount of brain points by the specified amount.
     * This method also updates the sun points label in the current game interface, if a game is present.
     *
     * @param amount the number of brain points to be added
     */
    public void collectBrain(int amount) {
        brainPoints += amount;
        if (game != null) {
            game.updateSunPointsLabel(sunPoints);
        }
    }

    /**
     * Attempts to place a zombie on the game board at the specified location.
     * This method checks if the player has enough brain points to cover the cost of the zombie.
     * If successful, it deducts the zombie's brain cost from the player's total brain points
     * and places the zombie on the board at the specified location.
     *
     * @param zombieByName the zombie to be placed on the board
     * @param row the row index where the zombie should be placed
     * @param col the column index where the zombie should be placed
     * @return true if the zombie was successfully placed, false otherwise
     */
    public boolean placeZombie(Zombies zombieByName, int row, int col) {

        return false;
    }

    /**
     * Returns the current game time in seconds.
     *
     * @return the current game time
     */
    public int getGameTime() {
        return gameTime;
    }

}