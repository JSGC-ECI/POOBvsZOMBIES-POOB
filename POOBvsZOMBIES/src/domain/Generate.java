package domain;

public abstract class Generate extends Zombies{
    public int BrainValue;

    public Generate(int hitPointes, int damage, int costBrains, int brainValue) {
        super(hitPointes, costBrains);
        this.BrainValue = BrainValue;
    }

    public abstract void generateBrain();
}
