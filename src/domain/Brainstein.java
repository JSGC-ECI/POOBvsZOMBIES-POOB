package domain;

/**
 * The Brainstein class represents a specific type of entity in the game that extends
 * functionality from the Generate class. This class is responsible for handling Brainstein-specific
 * behavior while leveraging the inherited properties and behaviors of the Generate class.
 */
public class Brainstein extends Generate{

    /**
     * Constructs a new Brainstein object with predefined values for hit points, damage,
     * brain cost, and brain value. This constructor initializes the Brainstein entity by
     * invoking the parent class constructor with fixed parameters. The Brainstein class
     * represents a specialized type of entity with specific behavior and attributes
     * inherited from the Generate class.
     */
    public Brainstein(){
        super(300,50,25,1);

    }


    @Override
    public void generateBrain() {

    }
}
