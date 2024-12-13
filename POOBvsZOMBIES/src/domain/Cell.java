
package domain;

public class Cell {
    private int row;
    private int col;
    private Plant plant;
    private Zombies zombie;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.plant = null;
        this.zombie = null;
    }

    public boolean isEmpty() {
        return plant == null && zombie == null;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Plant getPlant() {
        return plant;
    }

    public void removePlant() {
        this.plant = null;
    }

    public void setZombie(Zombies zombie) {
        this.zombie = zombie;
    }

    public Zombies getZombie() {
        return zombie;
    }

    public void removeZombie() {
        this.zombie = null;
    }

    public boolean hasZombie(){
        return this.zombie != null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasPlant() {
        return this.plant != null;
    }
}