package domain;

import presentation.Game;

import java.io.*;
import java.util.List;

/**
 * The GameManager class is responsible for managing the game's state, resources, and interactions
 * in the Player vs Player mode.
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
    public boolean placeZombie(Zombies zombie, int row, int col) {
        if (brainPoints >= zombie.getCostBrains()) {
            if (board.placeZombie(zombie, row, col)) {
                brainPoints -= zombie.getCostBrains();
                if (game != null) {
                    game.updateBrainPointsLabel(brainPoints);
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
     * Returns the current game time in seconds.
     *
     * @return the current game time
     */
    public int getGameTime() {
        return gameTime;
    }

    /**
     * Saves the current game state to the specified file. The game state is serialized
     * and written to the file.
     *
     * @param file the destination file to which the game state will be saved
     * @throws POOBvsZOMBIESException if an error occurs during the file writing or serialization process
     */
    public void save(File file) throws POOBvsZOMBIESException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new POOBvsZOMBIESException("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Opens a saved game state from the specified file and loads it into the game manager.
     * The method also updates the singleton instance of the game manager.
     *
     * @param file the file from which the game state will be loaded
     * @return the GameManager instance loaded from the specified file
     * @throws POOBvsZOMBIESException if an error occurs while reading the file or deserializing the object
     */
    public static GameManager open(File file) throws POOBvsZOMBIESException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            GameManager gameManager = (GameManager) ois.readObject();
            instance = gameManager; // Actualizar la instancia singleton
            return gameManager;
        } catch (IOException | ClassNotFoundException e) {
            throw new POOBvsZOMBIESException("Error al abrir el archivo: " + e.getMessage());
        }
    }

    /**
     * Imports the game state from a specified file. The file contains details such as
     * initial sun points, brain points, game time, and a list of zombies to initialize
     * the game. This method sets up the game state based on the imported data and creates
     * a new instance of the GameManager class.
     *
     * @param file The file containing the game state to be imported.
     * @throws POOBvsZOMBIESException If an error occurs during file reading or data parsing,
     *         such as invalid format or input/output issues.
     */
    public static void aimport(File file) throws POOBvsZOMBIESException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int sunPoints = Integer.parseInt(br.readLine());
            int brainPoints = Integer.parseInt(br.readLine());
            int gameTime = Integer.parseInt(br.readLine());

            GameManager gameManager = new GameManager(sunPoints, brainPoints, gameTime);

            String zombieLine;
            while ((zombieLine = br.readLine()) != null) {
                Zombies zombie = Zombies.fromString(zombieLine);
                gameManager.zombies.add(zombie);
            }

            instance = gameManager;
        } catch (IOException | NumberFormatException e) {
            throw new POOBvsZOMBIESException("Error al importar el archivo: " + e.getMessage());
        }
    }


    /**
     * Exports the current game state to a specified file. The game state includes
     * details such as sun points, brain points, game time, and the list of zombies with their information.
     *
     * @param file The file to which the game state will be exported.
     * @throws POOBvsZOMBIESException If an error occurs during file writing, such as an I/O exception.
     */
    public static void export(File file) throws POOBvsZOMBIESException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(instance.sunPoints + "\n");
            bw.write(instance.brainPoints + "\n");
            bw.write(instance.gameTime + "\n");

            for (Zombies zombie : instance.zombies) {
                bw.write(zombie.toString() + "\n");
            }
        } catch (IOException e) {
            throw new POOBvsZOMBIESException("Error al exportar el archivo: " + e.getMessage());
        }
    }
    public boolean removePlant(int row, int col) {
        Plant plant = board.getPlantAt(row, col);
        if (plant != null) {
            board.removePlantWithShovel(row, col);
            return true;
        }
        return false;
    }
}
