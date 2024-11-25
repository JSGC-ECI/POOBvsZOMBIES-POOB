package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Generate extends Zombies{
    public int BrainValue;

    /**
     * Constructor for objects of class Generate
     */
    public Generate(int hitPointes, int posY, int damage, int costBrains, int brainValue) {
        super(hitPointes, posY, costBrains);
        this.BrainValue = BrainValue;
    }

    public abstract void generateBrain();
}
