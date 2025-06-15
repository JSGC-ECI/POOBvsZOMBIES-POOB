package domain;

/**
 * The Defensive class is an abstract subclass of Plant that serves as a blueprint for creating defensive plant types
 * in a game. These plants typically have protective or defensive capabilities to shield against threats.
 * The class extends Plant, inheriting properties related to hit points and sun cost.
 */
public abstract class Defensive extends Plant {

    /**
     * Constructs a Defensive plant with specified hit points and sun cost.
     *
     * @param hitPoints the initial amount of hit points for the defensive plant.
     * @param cost the sun cost required to deploy the defensive plant.
     */
    public Defensive(int hitPoints, int cost) {
        super(hitPoints, cost);;
    }
}
