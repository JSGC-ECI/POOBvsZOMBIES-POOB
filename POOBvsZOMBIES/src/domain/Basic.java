package domain;

public class Basic extends Movement{

    public Basic() {
        super(100, 100, 100);
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
}
