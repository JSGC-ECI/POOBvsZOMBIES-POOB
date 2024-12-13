package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Board {
    private final int ROWS = 5;
    private final int COLUMNS = 10;
    private Cell[][] cells;
    private boolean[] lawnMower;

    /**
     * Constructor for objects of class Board
     */
    public Board() {
        cells = new Cell[ROWS][COLUMNS];
        lawnMower = new boolean[ROWS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                cells[i][j] = new Cell(i, j);
            }
            lawnMower[i] = true;
        }
    }

    /**
     * Places a plant on the board at the specified row and column position if the position is valid and empty.
     *
     * @param plant the plant to be placed on the board
     * @param row the row index where the plant should be placed
     * @param col the column index where the plant should be placed
     * @return true if the plant was successfully placed, false otherwise
     */
    public boolean placePlant(Plant plant, int row, int col) {
        if (isValidPosition(row, col) && cells[row][col].isEmpty() ) {
            cells[row][col].setPlant(plant);
            return true;
        }
        return false;
    }

    /**
     * Attempts to place a zombie at the last column of the specified row on the board.
     * The zombie can only be placed if the position is valid and empty.
     *
     * @param zombie the zombie to be placed on the board
     * @param row the row index where the zombie should be placed
     * @return true if the zombie was successfully placed, false otherwise
     */
    public boolean placeZombie(Zombies zombie, int row) {
        if (isValidPosition(row, COLUMNS - 1) && cells[row][COLUMNS - 1].isEmpty()) {
            cells[row][COLUMNS - 1].setZombie(zombie);
            return true;
        }
        return false;
    }

    /**
     * Moves the zombies on the board one step to the left. If a zombie is at the leftmost column,
     * it will trigger the lawn mower for that row.
     * This method checks each cell in the board for zombies. If a zombie is present and
     * it's not in the leftmost column, the zombie is moved to the adjacent cell to the left
     * if the position is valid. If it's on the leftmost column, the lawn mower for that row
     * is activated, removing all zombies in that row.
     */
    public void moveZombies() {
        for (int i = 0; i < ROWS - 1; i++) {
            for (int j = 0; j < COLUMNS - 1; j++) {
                if (cells[i][j].hasZombie()) {
                    Zombies zombie = cells[i][j].getZombie();
                    if (j == 0) {
                        handlePodadora(i, zombie);
                    } else if (isValidPosition(i, j - 1)) {
                        cells[i][j].removeZombie();
                        cells[i][j - 1].setZombie(zombie);
                    }
                }
            }
        }
    }

    /**
     * Handles the activation of a lawn mower in the specified row when a zombie
     * reaches the leftmost column. If the lawn mower is active, it removes all
     * zombies in that row and deactivates the lawn mower.
     *
     * @param row the row index where the action is to be performed
     * @param zombie the zombie that triggered the lawn mower
     */
    private void handlePodadora(int row, Zombies zombie) {
        if (lawnMower[row]) {
            for (int j = 0; j < COLUMNS; j++) {
                if (cells[row][j].hasZombie()) {
                    cells[row][j].removeZombie();
                }
            }
            lawnMower[row] = false;
        } else {
            // pierde
        }
    }

    /**
     * Checks whether the specified position on the board is valid.
     *
     * @param row the row index to check
     * @param col the column index to check
     * @return true if the position is within the valid range of rows and columns, false otherwise
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS - 1 && col >= 1 && col < COLUMNS - 1;
    }

    /**
     * Retrieves the name of the specified plant type.
     *
     * @param plant the plant whose name is to be retrieved
     * @return the name of the plant if it is an instance of a recognized type
     * @throws POOBvsZOMBIESException if the plant type is not recognized
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
     * Retrieves a specific plant instance based on the given plant name.
     *
     * @param plantName the name of the plant to retrieve
     * @return the Plant object corresponding to the specified name
     * @throws POOBvsZOMBIESException if the plant name is not recognized
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
            default:
                throw new POOBvsZOMBIESException(POOBvsZOMBIESException.NO_PLANT);
        }
    }

    /**
     * Retrieves a specific zombie instance based on the given zombie name.
     *
     * @param zombieName the name of the zombie to retrieve
     * @return the Zombies object corresponding to the specified name
     * @throws POOBvsZOMBIESException if the zombie name is not recognized
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

}