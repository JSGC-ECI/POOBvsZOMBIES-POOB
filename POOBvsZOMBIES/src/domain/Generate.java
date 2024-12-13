package domain;

/**
 * The Generate class is an abstract extension of the Zombies class, intended to serve as a
 * blueprint for zombie characters that have the functionality to generate brains in the
 * POOBvsZombies game. The class introduces a new concept, BrainValue, representing the value
 * associated with the brain generation ability.
 */
public abstract class Generate extends Zombies{
    public int BrainValue;

    /**
     * Constructs a new Generate instance with specified attributes for hit points,
     * damage, cost in brains, and brain value. This constructor initializes a
     * zombie character with the inherited functionality for brain generation.
     *
     * @param hitPointes   the initial amount of hit points for the zombie.
     * @param damage       the damage value associated with the zombie.
     * @param costBrains   the cost in brains required for the zombie.
     * @param brainValue   the value associated with the brain generation ability.
     */
    public Generate(int hitPointes, int damage, int costBrains, int brainValue) {
        super(hitPointes, costBrains);
        this.BrainValue = BrainValue;
    }

    /**
     * Initiates the brain generation process specific to a zombie character.
     * This abstract method must be implemented by subclasses to define the
     * logic for creating or managing brain resources in the context of the
     * POOBvsZombies game. The brain generation process might involve increasing
     * available brain resources or interacting with game elements to fulfill
     * the game's requirements.
     */
    public abstract void generateBrain();
}
