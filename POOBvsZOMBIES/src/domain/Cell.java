
package domain;

/**
 * Represents a single cell in a game grid. The cell can hold either a plant or a zombie
 * and provides various methods to manage its contents and retrieve information about its state.
 */
public class Cell {
    private int row;
    private int col;
    private Plant plant;
    private Zombies zombie;

    /**
     * Initializes a new Cell object with the specified row and column indices.
     * The cell starts as empty, with no plant or zombie placed in it.
     *
     * @param row the row index of the cell in the game grid.
     * @param col the column index of the cell in the game grid.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.plant = null;
        this.zombie = null;
    }

    /**
     * Checks whether the cell is empty. A cell is considered empty if it contains neither
     * a plant nor a zombie.
     *
     * @return true if the cell has no plant and no zombie, otherwise false.
     */
    public boolean isEmpty() {
        return plant == null && zombie == null;
    }

    /**
     * Sets a plant in the cell. This method assigns a specified plant to the cell,
     * replacing any existing plant that may already be present.
     *
     * @param plant the plant to be placed in the cell. It can be null to remove any
     *              existing plant from the cell.
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     * Retrieves the plant currently placed in the cell.
     *
     * @return the plant present in the cell, or null if no plant is present.
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * Removes the plant currently placed in the cell.
     * If a plant is present, it is set to null, effectively making the cell empty with respect to plants.
     */
    public void removePlant() {
        if (this.plant != null) {
            this.plant = null;
        }
    }

    /**
     * Sets a zombie in the cell. This method assigns a specified zombie to the cell,
     * replacing any existing zombie that may already be present.
     *
     * @param zombie the zombie to be placed in the cell. It can be null to remove any
     *               existing zombie from the cell.
     */
    public void setZombie(Zombies zombie) {
        this.zombie = zombie;
    }

    /**
     * Retrieves the zombie currently placed in the cell.
     *
     * @return the zombie present in the cell, or null if no zombie is present.
     */
    public Zombies getZombie() {
        return zombie;
    }

    /**
     * Removes the zombie from the cell if it is no longer alive.
     * This method checks the zombie's status using the `isAlive` method. If the zombie's
     * hit points are depleted (i.e., it is not alive), it sets the zombie reference in
     * the cell to null, effectively making the cell empty with respect to zombies.
     */
    public void removeZombie() {
        if (!this.zombie.isAlive()) {
            this.zombie = null;
        }
    }

    /**
     * Checks whether the cell contains a zombie.
     * A cell is considered to have a zombie if the `zombie` field is not null.
     *
     * @return true if the cell contains a zombie, false otherwise.
     */
    public boolean hasZombie(){
        return this.zombie != null;
    }

    /**
     * Retrieves the row index of the cell in the game grid.
     *
     * @return the row index of this cell.
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column index of the cell in the game grid.
     *
     * @return the column index of this cell.
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks whether the cell contains a plant.
     * A cell is considered to have a plant if the `plant` field is not null.
     *
     * @return true if the cell contains a plant, false otherwise.
     */
    public boolean hasPlant() {
        return this.plant != null;
    }

    /**
     * Determines if a zombie can attack in the current cell.
     * This method checks whether the cell contains both a plant and a zombie,
     * as an attack can only occur under these conditions.
     *
     * @return true if both a plant and a zombie are present in the cell, false otherwise.
     */
    public boolean zombieAtack(){
        if (plant != null && zombie != null){
            return true;
        }
        return false;
    }
}