
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
     *
     */
    public boolean isEmpty() {
        return plant == null && zombie == null;
    }

    /**
     *
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     *
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     *
     */
    public void removePlant() {
        this.plant = null;
    }

    /**
     *
     */
    public void setZombie(Zombies zombie) {
        this.zombie = zombie;
    }

    /**
     * Obtiene el zombi de la celda
     */
    public Zombies getZombie() {
        return zombie;
    }

    /**
     * Elimina el zombi de la celda
     */
    public void removeZombie() {
        this.zombie = null;
    }

    /**
     *
     */
    public boolean hasZombie(){
        return this.zombie != null;
    }

    /**
     * Devuelve la posición de la celda
     */
    public int getRow() {
        return row;
    }

    /**
     * Devuelve la posición de la celda
     */
    public int getCol() {
        return col;
    }
}