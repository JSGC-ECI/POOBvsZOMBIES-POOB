
package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Cell {
    private int row;
    private int col;
    private Plant plant;
    private Zombies zombie;

    /**
     * Constructor for objects of class Cell
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
}