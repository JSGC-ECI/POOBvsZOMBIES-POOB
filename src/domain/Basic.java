package domain;

public class Basic extends Movement{

    public Basic() {
        super(100, 100, 100,10,10);
    }

    /**
     * Executes an attack on the specified plant. The attack decreases the plant's hit points
     * by the amount of damage associated with this attacker. If the plant's hit points reach
     * zero or below and the plant is no longer alive, the attacker becomes movable.
     *
     * @param plant the plant to be attacked. If the plant is null or not alive, the attack
     *              does not proceed.
     */
    @Override
    public void attack(Plant plant) {
        if (plant != null && plant.isAlive()) {
            plant.setHitPoints(this.getDamage());
            if (!plant.isAlive()) {
                this.setMoving(true);
            }
        }
    }
}
