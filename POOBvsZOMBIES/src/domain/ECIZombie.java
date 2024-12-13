package domain;

public class ECIZombie extends Movement {

    public ECIZombie() {
        super(200, 50, 250);
    }

    @Override
    public void atack(Plant plant) {
        if (plant != null && plant.isAlive()) {
            plant.setHitPoints(this.getDamage());
            if (!plant.isAlive()) {
                this.setMoving(true);
            }
        }
    }

    public void shootProjectile(Board board, int row, int col) {

    }
}
