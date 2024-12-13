
package domain;

/**
 * Represents a cell on a game board which can contain a plant or a zombie.
 * Each cell is defined by its position on the board in terms of row and column indices.
 */
public class Cell {
    private int row;
    private int col;
    private Plant plant;
    private Zombies zombie;

    /**
     * Constructs a new Cell instance with specified row and column indices.
     * Initializes the cell with no plant or zombie.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.plant = null;
        this.zombie = null;
    }

    /**
     * Checks if the cell is empty, i.e., contains no plant or zombie.
     *
     * @return true if the cell contains neither a plant nor a zombie, false otherwise
     */
    public boolean isEmpty() {
        return plant == null && zombie == null;
    }

    /**
     * Sets the plant for this cell.
     *
     * @param plant the plant to be placed in the cell
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     * Retrieves the plant present in this cell.
     *
     * @return the plant currently set in this cell, or null if no plant is present
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * Removes the plant from the cell by setting the plant property to null.
     * After invoking this method, the cell will not contain any plant.
     */
    public void removePlant() {
        this.plant = null;
    }

    /**
     * Sets the zombie for this cell.
     *
     * @param zombie the zombie to be placed in the cell
     */
    public void setZombie(Zombies zombie) {
        this.zombie = zombie;
    }

    /**
     * Retrieves the zombie currently placed in this cell.
     *
     * @return the zombie present in this cell, or null if no zombie is present
     */
    public Zombies getZombie() {
        return zombie;
    }

    /**
     * Removes the zombie from the cell by setting the zombie property to null.
     * After invoking this method, the cell will not contain any zombie.
     */
    public void removeZombie() {
        this.zombie = null;
    }

    /**
     * Determines whether this cell contains a zombie.
     *
     * @return true if there is a zombie present in the cell, false otherwise
     */
    public boolean hasZombie(){
        return this.zombie != null;
    }

    /**
     * Retrieves the row index of this cell.
     *
     * @return the row index as an integer
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column index of this cell.
     *
     * @return the column index as an integer
     */
    public int getCol() {
        return col;
    }

    public boolean hasPlant() {
        return this.plant != null;
    }
}