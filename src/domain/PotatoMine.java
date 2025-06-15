package domain;

/**
 * PotatoMine is a type of defensive plant with explosive capabilities. It
 * temporarily hides underground and bursts to damage enemies when triggered.
 * It is a subclass of the Defensive class, inheriting its defensive properties
 * and characteristics.
 */
public class PotatoMine extends Defensive {
    private final double TIME = 14;

    /**
     * Constructs a new PotatoMine instance with predefined hit points and sun cost.
     * This constructor initializes the PotatoMine with specific defensive attributes,
     * inherited from the Defensive superclass, enabling it to act as a defensive plant
     * in the gameplay. The PotatoMine initially remains underground and becomes active
     * after a period, exploding upon interaction with an enemy.
     */
    public PotatoMine() {
        super(100, 25);
    }

    /**
     * Retrieves the time before the PotatoMine becomes active and ready to explode.
     *
     * @return the time in seconds it takes for the PotatoMine to become fully active.
     */
    public double getTime() {
        return this.TIME;
    }

    /**
     * Causes the PotatoMine to explode, dealing damage to nearby enemies.
     * This method is triggered when the PotatoMine is activated after being
     * placed underground and an enemy steps onto it. The explosion impacts
     * the area around the PotatoMine, potentially affecting multiple targets.
     */
    public void burst(){

    }

}
