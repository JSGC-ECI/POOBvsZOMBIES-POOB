package domain;

/**
 * The Peashooter class represents a specific type of attacking plant
 * in the game. It extends the Attacking class and is characterized by
 * specific hit points, cost, attack power, and time attributes.
 * The Peashooter is typically used to attack zombies by shooting peas.
 */
public class Peashooter extends Attacking{

    /**
     * Constructs a Peashooter with predefined attributes for hit points,
     * cost, attack power, and attack time. The Peashooter is a type of
     * attacking plant in the game, designed for shooting peas at enemies.
     * This constructor initializes the Peashooter's characteristics
     * by passing specific values to the superclass, Attacking.
     */
    public Peashooter() {
        super(300,100,20,1.5);
    }

    /**
     * Initiates an attack action specific to the Peashooter class, targeting nearby enemies.
     * The method leverages the attack power defined in the Peashooter attributes to deal
     * damage to enemies. This implementation of the attack method provides the functionality
     * for the Peashooter to engage in combat interactions within the game environment.
     */
    @Override
    public void atack() {

    }
}
