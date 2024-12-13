package domain;

/**
 * The Brainstein class extends the Generate class and represents a specific type of zombie
 * character with predefined attributes and behaviors in the POOBvsZombies game. It is
 * responsible for generating brains in the game, although the specific implementation details
 * of the brain generation are not provided in this class.
 */
public class Brainstein extends Generate{

    /**
     * Constructs a Brainstein instance with specified attributes for hit points, damage,
     * cost in brains, and brain value. This constructor initializes a Brainstein zombie
     * with pre-defined values that determine its behavior and interactions within the
     * game environment. The Brainstein is a type of zombie character that has unique
     * abilities related to brain generation.
     */
    public Brainstein(){
        super(300,50,25,1);

    }


    @Override
    public void generateBrain() {

    }
}
