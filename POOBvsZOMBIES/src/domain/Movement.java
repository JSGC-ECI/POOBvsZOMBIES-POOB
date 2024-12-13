package domain;

public abstract class Movement extends Zombies {
    private int damage;

    private boolean isMoving = true;

    public Movement(int hitPoints, int damage, int costbrains ) {
        super(hitPoints, costbrains);
        this.damage = damage;
        this.isMoving = isMoving;
    }


    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void move(Board board, int row, int col) {
        if (this.isMoving()) {
            if (col > 0) {
                Cell currentCell = board.getCell(row, col);
                Cell leftCell = board.getCell(row, col - 1);
                if (leftCell.isEmpty()) {
                    currentCell.removeZombie();
                    leftCell.setZombie(this);
                    System.out.println("Zombie se movió a la izquierda: " + row + ", " + (col - 1));
                }
            }
        }
    }

    public void attack(Plant plant) {
        if (plant != null && plant.isAlive()) {
            plant.setHitPoints(this.damage);
            System.out.println("Zombie atacó a la planta! Daño: " + this.damage);
        }
    }

    public int getDamage() {
        return damage;
    }

    public abstract void atack(Plant plant);
}
