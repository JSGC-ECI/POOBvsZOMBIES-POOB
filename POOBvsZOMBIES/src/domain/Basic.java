package domain;


/**
 * Represents a basic zombie type that can attack plants.
 * Basic zombies have a default attack damage and are initially
 * capable of moving.
 */
public class Basic extends Movement{


    /**
     * Constructs a new Basic zombie instance with predefined attributes.
     * The Basic zombie is initialized with 100 hit points, 100 attack
     * damage, and requires 100 brain points for its deployment.
     */
    public Basic() {
        super(100, 100, 100);
    }


    /**
     * Attacks the specified plant by inflicting damage to its hit points.
     * If the plant's hit points are reduced to zero or below, the zombie
     * is set to moving.
     *
     * @param plant the plant to be attacked. The plant must not be null,
     *              and it must be alive for the attack to proceed.
     */
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
