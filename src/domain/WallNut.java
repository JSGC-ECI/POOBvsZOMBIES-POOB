package domain;

/**
 * WallNut is a defensive plant in the game with high durability.
 * It is utilized to absorb damage from incoming threats, protecting other plants.
 * As an extension of the Defensive class, WallNut inherits features to enhance its survivability.
 */
public class WallNut extends Defensive{
    /**
     * Constructs a new WallNut instance, a type of defensive plant with high durability.
     * The WallNut is initialized with specific hit points and sun cost, inheriting
     * these characteristics from its parent class, Defensive.
     */
    public WallNut() {
        super(4000, 50);
    }
}
