package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board in a tower-defense style game such as Plants vs. Zombies.
 * The board consists of a grid of cells where plants and zombies are placed and interact.
 */
public class Board {
    private final int ROWS = 5;
    private static final int COLUMNS = 10;
    private static Cell[][] cells;
    private static boolean[] lawnMower;
    private int sunPoints;
    private int brainPoints;
    private List<Zombies> zombies;

    /**
     * Constructs a new Board with the specified initial sun points and brain points.
     * Initializes the game board with an empty grid of cells and sets up the lawn mowers
     * and zombies list.
     *
     * @param initialSunPoints the initial number of sun points available for the game.
     * @param initialBrainPoints the initial number of brain points available for the game.
     */
    public Board(int initialSunPoints, int initialBrainPoints) {
        this.sunPoints = initialSunPoints;
        this.brainPoints = initialBrainPoints;
        cells = new Cell[ROWS][COLUMNS];
        lawnMower = new boolean[ROWS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                cells[i][j] = new Cell(i, j);
            }
            lawnMower[i] = true;
        }
        zombies = new ArrayList<>();
    }

    /**
     * Retrieves the number of rows in the board.
     *
     * @return the total number of rows available on the board.
     */
    public int getRows() {
        return ROWS;
    }

    /**
     * Retrieves the number of columns in the board.
     *
     * @return the total number of columns available on the board.
     */
    public int getColumns() {
        return COLUMNS;
    }

    /**
     * Retrieves the current number of sun points available on the board.
     *
     * @return the current sun points as an integer.
     */
    public int getSunPoints() {
        return sunPoints;
    }

    /**
     * Retrieves the current number of brain points available in the game.
     *
     * @return the current brain points as an integer.
     */
    public int getBrainPoints() {
        return brainPoints;
    }


    /**
     * Places a plant at the specified position on the board if the position is valid
     * and the cell is empty.
     *
     * @param plant the Plant object to be placed on the board.
     * @param row the row index where the plant will be placed.
     * @param col the column index where the plant will be placed.
     * @return true if the plant was successfully placed, false otherwise.
     */
    public boolean placePlant(Plant plant, int row, int col) {
        if (isValidPosition(row, col) && cells[row][col].isEmpty() ) {
            cells[row][col].setPlant(plant);
            return true;
        }
        return false;
    }

    /**
     * Places the specified zombie at the given row and column on the board if the position
     * is valid and the cell is empty.
     *
     * @param zombie the Zombie object to be placed on the board.
     * @param row the row index where the zombie should be placed.
     * @param col the column index where the zombie should be placed.
     * @return true if the zombie was successfully placed, false otherwise.
     */
    public boolean placeZombie(Zombies zombie, int row, int col) {
        if (isValidPosition(row, col) && cells[row][col].isEmpty()) {
            cells[row][col].setZombie(zombie);
            return true;
        }
        return false;
    }


    /**
     * Moves all zombies on the board that are capable of movement. Iterates through each
     * cell starting from the rightmost column towards the left in each row, checking for
     * the presence of zombies in each cell. If a zombie is found and it is an instance
     * of the Movement class, the zombie's move behavior is invoked.
     *
     * The move behavior allows the zombie to move to an adjacent cell or commence attack
     * on a nearby plant if one is present. Zombies move leftward in their respective rows,
     * following the movement rules defined in the Movement class.
     */
    public void moveZombies() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = COLUMNS - 1; j > 0; j--) {
                if (cells[i][j].hasZombie()) {
                    Zombies zombie = cells[i][j].getZombie();
                    if (zombie instanceof Movement) {
                        ((Movement) zombie).move(this, i, j);
                    }
                }
            }
        }
    }

    /**
     * Handles the activation of the lawn mower for the specified row.
     * If the lawn mower in the given row is active, it clears all zombies in that row
     * and deactivates the lawn mower. Otherwise, it returns the state of the lawn mower.
     *
     * @param row the index of the row in the game board to handle the lawn mower activation.
     * @return false if the lawn mower was activated and zombies were cleared,
     *         true if the lawn mower was already inactive.
     */
    public static boolean handlePodadora(int row) {
        if (lawnMower[row]) {
            for (int j = 0; j < COLUMNS; j++) {
                if (cells[row][j].hasZombie()) {
                    cells[row][j].removeZombie();
                }
            }
            lawnMower[row] = false;
            return false;
        } else {
            return true;
        }
    }
    /**
     * Determines if any lawn mower has been activated during the game.
     * Iterates through a predefined number of rows, invoking the handlePodadora
     * method for each row to check if the lawn mower was triggered.
     *
     * @return true if any lawn mower was activated, false otherwise.
     */
    public static boolean winner(){
        for (int h = 0; h < 5; h++){
            if (handlePodadora(h)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Determines if the specified position on the board is valid.
     * A position is considered valid if it falls within the defined range
     * of rows and columns for the board.
     *
     * @param row the row index of the position to validate.
     * @param col the column index of the position to validate.
     * @return true if the position is within the valid range of the board, false otherwise.
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    /**
     * Determines the name of the given plant based on its type.
     * The method identifies the specific subclass of the provided Plant object
     * and returns the corresponding plant name. If the plant type is not
     * recognized, it throws an exception.
     *
     * @param plant the Plant object for which the name needs to be determined.
     *              Must be an instance of a known plant type.
     * @return the name of the plant as a String, based on its type.
     *         For example, "Sunflower" for Sunflower, "Peashooter" for Peashooter, etc.
     * @throws POOBvsZOMBIESException if the plant type is not recognized.
     */
    private String getPlantName(Plant plant) {
        if (plant instanceof Sunflower) {
            return "Sunflower";
        } else if (plant instanceof Peashooter) {
            return "Peashooter";
        } else if (plant instanceof WallNut) {
            return "Wall-nut";
        } else if (plant instanceof PotatoMine) {
            return "Potato Mine";
        } else if (plant instanceof ECIPlant) {
            return "ECI Plant";
        } else {
            throw new POOBvsZOMBIESException(POOBvsZOMBIESException.NO_PLANT);
        }
    }

    /**
     * Retrieves a specific plant object by its name. The method takes the name
     * of a plant as input and returns a corresponding Plant object instance.
     * If the plant name does not match any of the predefined plant types,
     * an exception is thrown.
     *
     * @param plantName the name of the plant to retrieve. Must be one of the
     *                  predefined plant names: "Sunflower", "Peashooter", "WallNut",
     *                  "PotatoMine", "ECIPlant", or "Evolve".
     * @return a Plant object corresponding to the specified name.
     * @throws POOBvsZOMBIESException if the provided plant name is not recognized.
     */
    public Plant getPlantByName(String plantName) {
        switch (plantName) {
            case "Sunflower":
                return new Sunflower();
            case "Peashooter":
                return new Peashooter();
            case "WallNut":
                return new WallNut();
            case "PotatoMine":
                return new PotatoMine();
            case "ECIPlant":
                return new ECIPlant();
            case "Evolve":
                return new Evolve();
            default:
                throw new POOBvsZOMBIESException(POOBvsZOMBIESException.NO_PLANT);
        }
    }

    /**
     * Retrieves a specific type of zombie based on its name. The method returns a new instance
     * of a zombie class corresponding to the provided name. If the name does not match any
     * predefined zombie type, an exception is thrown.
     *
     * @param zombieName the name of the zombie to retrieve. Valid names are: "Basic", "Conehead",
     *                   "Buckethead", "Brainstein", "ECIZombie".
     * @return a Zombies object corresponding to the specified name.
     * @throws POOBvsZOMBIESException if the specified name does not match any known zombie type.
     */
    public Zombies getZombieByName(String zombieName) {
        switch (zombieName) {
            case "Basic":
                return new Basic();
            case "Conehead":
                return new Conehead();
            case "Buckethead":
                return new Buckethead();
            case "Brainstein":
                return new Brainstein();
            case "ECIZombie":
                return new ECIZombie();
            default:
                throw new POOBvsZOMBIESException(POOBvsZOMBIESException.NO_ZOMBIE);
        }
    }


    /**
     * Retrieves the Cell object located at the specified row and column
     * on the game board.
     *
     * @param row the row index of the desired cell.
     * @param i the column index of the desired cell.
     * @return the Cell object at the specified row and column.
     */
    public Cell getCell(int row, int i) {
        return cells[row][i];
    }

    /**
     * Removes a zombie from the specified cell on the board if a zombie is present.
     *
     * @param row the row index of the cell from which the zombie should be removed.
     * @param col the column index of the cell from which the zombie should be removed.
     */
    public void removeZombie(int row, int col) {
        if (cells[row][col].hasZombie()) {
            cells[row][col].removeZombie();
        }
    }


    /**
     * Adds a zombie to the specified position on the board if the position is valid
     * and the cell is empty. Updates the board state by adding the zombie to the cell
     * and the internal list of zombies.
     *
     * @param zombie the Zombies object to be added to the board.
     * @param row the row index where the zombie will be placed.
     * @param col the column index where the zombie will be placed.
     * @return true if the zombie was successfully added to the board, false otherwise.
     */
    public boolean addZombie(Zombies zombie, int row, int col) {
        if (isValidPosition(row, col) && isEmpty(row, col)) {
            cells[row][col].setZombie(zombie);
            zombies.add(zombie);
            return true;
        }
        return false;
    }

    /**
     * Determines if the specified cell on the board is empty.
     * A cell is considered empty if it is within valid board boundaries
     * and does not contain any plant or zombie.
     *
     * @param row the row index of the cell to check.
     * @param col the column index of the cell to check.
     * @return true if the cell is valid and empty, false otherwise.
     */
    public boolean isEmpty(int row, int col) {
        if (!isValidPosition(row, col)) {
            return false;
        }
        return cells[row][col].isEmpty();
    }

    /**
     * Retrieves the Plant object located at the specified row and column on the game board.
     *
     * @param row the row index of the position from which the plant should be retrieved.
     * @param col the column index of the position from which the plant should be retrieved.
     * @return the Plant object located at the specified row and column, or null if no plant is present.
     */
    public Plant getPlantAt(int row, int col) {
        return cells[row][col].getPlant();
    }

    /**
     * Attempts to remove a plant from the specified position on the board using a shovel.
     * The removal occurs only if the position is valid and a plant exists in the cell.
     *
     * @param row the row index of the cell from which the plant should be removed.
     * @param col the column index of the cell from which the plant should be removed.
     * @return true if the plant was successfully removed, false otherwise.
     */
    public boolean removePlantWithShovel(int row, int col) {
        if (isValidPosition(row, col) && cells[row][col].hasPlant()) {
            cells[row][col].removePlant();
            return true;
        }
        return false;
    }

}