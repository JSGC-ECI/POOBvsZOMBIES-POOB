package domain;

/**
 * The Peashooter class represents a specific type of attacking plant
 * in the game. It extends the Attacking class and is characterized by
 * specific hit points, cost, attack power, and time attributes.
 * The Peashooter is typically used to attack zombies by shooting peas.
 */
public class Peashooter extends Attacking {



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

    @Override
    public void atack() {
        // Implementation for the Peashooter's attack logic
        // Example: shooting a pea.
    }
}
