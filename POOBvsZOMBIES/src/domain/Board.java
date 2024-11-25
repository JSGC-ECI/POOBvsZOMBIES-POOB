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

    /**
     * Constructor for objects of class Board
     */
    public Board() {
        cells = new Cell[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                cells[i][j] = new Cell(i, j); // Cada celda conoce su posición
            }
        }
    }

    /**
     *
     */
    public boolean placePlant(Plant plant, int row, int col) {
        if (isValidPosition(row, col) && cells[row][col].isEmpty()) {
            cells[row][col].setPlant(plant);
            return true;
        }
        return false;
    }

    public boolean placeZombie(Zombies zombie, int row) {
        if (isValidPosition(row, 9) && cells[row][9].isEmpty()) {
            cells[row][9].setZombie(zombie);
            return true;
        }
        return false;
    }

    /**
     *
     */
    public void moveZombies() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (cells[i][j].hasZombie()) {
                    Zombies zombie = cells[i][j].getZombie();
                    if (isValidPosition(i, j - 1)) {
                        cells[i][j].removeZombie();
                        cells[i][j - 1].setZombie(zombie);
                    } else {
                        // Llegaron al final del tablero (el jugador pierde)
                    }
                }
            }
        }
    }

    /**
     * Verifica si una posición es válida en el tablero
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

}

