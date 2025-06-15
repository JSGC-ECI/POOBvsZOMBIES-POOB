package domain;


import presentation.GamePvsMO;

import java.util.ArrayList;
import java.util.List;

/**
 * GameManagerPvsMO is responsible for managing the game's core functionality
 * in the Player vs Machine (ZombiesOriginal) mode. It handles the game's
 * state, including board management, sun and brain points tracking, game time,
 * and the placement and movement of plants and zombies.
 */
public class GameManagerPvsMO {

    private Board board;
    private static int sunPoints;
    private int brainPoints;
    private List<Zombies> zombies;
    private static GameManagerPvsMO instance;
    private static GamePvsMO game;
    private int gameTime;

    /**
     * Creates a new instance of the GameManagerPvsMO class to manage game functionality
     * in Player versus Multiplayer Online (PvMO) mode, initializing game parameters.
     *
     * @param initialSunPoints the initial amount of sun points available to the player
     * @param initialBrainPoints the initial amount of brain points for zombie-related tasks
     * @param initialTime the initial game time in seconds
     */
    public GameManagerPvsMO(int initialSunPoints, int initialBrainPoints, int initialTime ) {
        board = new Board(initialSunPoints, initialBrainPoints);
        this.sunPoints = initialSunPoints;
        this.brainPoints = initialBrainPoints;
        this.gameTime = initialTime;
        this.zombies = new ArrayList<>();    }

    /**
     * Sets the game instance for the Player versus Multiplayer Online (PvMO) mode.
     *
     * @param game the GamePvsMO instance to be associated with this GameManagerPvsMO
     */
    public void setGame(GamePvsMO game) {
        this.game = game;
    }

    /**
     * Retrieves the singleton instance of the GameManagerPvsMO class. If the instance does not
     * already exist, it creates a new instance with default values for sun points, brain points,
     * and game time.
     *
     * @return the singleton instance of the GameManagerPvsMO class.
     */
    public static GameManagerPvsMO getInstance() {
        if (instance == null) {
            int defaultSunPoints = 100;
            int defaultBrainPoints = 50;
            int defaultGameTime = 300;

            instance = new GameManagerPvsMO(defaultSunPoints, defaultBrainPoints, defaultGameTime);
        }
        return instance;
    }

    /**
     * Sets the current game time for the Player versus Multiplayer Online (PvMO) mode.
     *
     * @param gameTime the game time in seconds to be set. It represents the current
     *                 progression of the game.
     */
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

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
     * Attempts to place a zombie on the bottom row of the board at a random column.
     * If the target position is occupied, the method will make multiple attempts to
     * find an empty position. The placement is limited to a maximum number of attempts.
     * If a valid position is found, the zombie is added to the board and stored in the zombies list.
     *
     * @param zombie the zombie object to be placed on the board.
     */
    public void placeZombie(Zombies zombie) {
        int row = board.getRows() - 1;
        int maxAttempts = 10;
        int attempts = 0;
        int col;
        do {
            col = (int) (Math.random() * board.getColumns());
            attempts++;
        } while (!board.isEmpty(row, col) && attempts < maxAttempts);

        if (board.isEmpty(row, col)) {
            board.placeZombie(zombie, row, col);
            zombies.add(zombie);
        }
    }

    /**
     * Updates the game state by executing zombie movements and checking for the presence
     * of remaining zombies. This method is responsible for one primary operation:
     *
     * - Calls the moveZombies method on the game board, which advances zombies based
     *   on their movement logic and interaction rules.
     *
     * After the zombie movements are resolved, it checks whether the list of zombies
     * in the game is empty:
     *
     * - If no zombies remain, the code indicates such a condition within the method body.
     *   Further actions on this condition would depend on the specific game rules.
     */
    public void updateGame () {
        board.moveZombies();
        if (zombies.isEmpty()) {
            //No quedan zombis
        }
    }

    /**
     * Retrieves the current amount of sun points available in the game.
     *
     * @return the number of sun points currently available.
     */
    public int getSunPoints () {
        return sunPoints;
    }

    /**
     * Collects a specified amount of sun points and updates the game's sun points label if a game instance exists.
     *
     * @param amount the amount of sun points to be added to the current total.
     */
    public void collectSun ( int amount){
        sunPoints += amount;
        if (game != null) {
            game.updateSunPointsLabel(sunPoints);
        }
    }

    /**
     * Executes the zombie movement logic within the game. This method delegates the responsibility
     * to the game board's moveZombies method, which iterates through all cells to identify and
     * advance zombies in accordance with pre-defined movement rules.
     *
     * The operation involves:
     * - Identifying zombies present in each cell of the game board.
     * - Determining their capability to move or attack based on their type and current state.
     * - Moving zombies to adjacent cells or triggering attacks on nearby plants as per the defined logic.
     *
     * This method relies on the board's internal implementation of zombie movement and handles
     * the progression of zombies across the game board.
     */
    public void moveZombies () {
        board.moveZombies();
    }

    /**
     * Retrieves the current game time for the Player versus Multiplayer Online (PvMO) mode.
     *
     * @return the current game time in seconds, representing the progression of the game.
     */
    public int getGameTime () {
        return gameTime;
    }
}
