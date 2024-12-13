package domain;

public abstract class Zombies extends Character {

    private int costBrains;

    public Zombies(int hitPointes, int costBrains) {
        super(hitPointes);
        this.costBrains = costBrains;

    }

    public int getCostBrains() {
        return costBrains;
    }


}
