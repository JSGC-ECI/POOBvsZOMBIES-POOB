package domain;

/**
 * The Generate class is an abstract subclass of Zombies, designed for entities
 * that possess the ability to generate "brains" as resources in the game.
 * It extends the core functionality provided by the Zombies class while introducing
 * a new attribute and method specific to brain generation.
 */
public abstract class Generate extends Zombies{
    public int BrainValue;

    /**
     * Constructs a new Generate object with the specified attributes.
     *
     * @param hitPointes   the hit points of the generate entity, representing its health.
     * @param damage       the amount of damage the generate entity can inflict.
     * @param costBrains   the number of brains required to deploy the entity in the game.
     * @param brainValue   the value of brains the generate entity can produce.
     */
    public Generate(int hitPointes, int damage, int costBrains, int brainValue) {
        super(hitPointes, costBrains);
        this.BrainValue = BrainValue;
    }

    /**
     * Defines the behavior for generating brains in entities that extend the Generate class.
     * This abstract method must be implemented by subclasses to specify how brains are
     * produced or acquired during gameplay.
     */
    public abstract void generateBrain();
}
