package domain;

/**
 * ECIPlant is a specialized type of Support plant.
 * It inherits from the Support class to provide specific behavior
 * and initial configuration compatible with the game's mechanics.
 */
public class ECIPlant extends Support{

    /**
     * Constructs a new instance of ECIPlant with predefined attributes.
     * The ECIPlant is initialized with specified hit points, cost in sun points,
     * sun value generation rate, and generation interval.
     * It uses the superclass Support's constructor to provide these specific values:
     * hit points set to 150, sun cost to 75, a sun value of 50, and a time interval
     * of 20 seconds for sun generation.
     */
    public ECIPlant() {
        super(150,75,50,20);

    }
}
