package domain;


/**
 * Represents a plant in a game, such as a strategy or tower defense game, that inherits from
 * the Character class. This abstract class serves as a blueprint for specific types of plants
 * with additional attributes and behaviors. Each plant has a sun cost, which is the amount of
 * sun resource needed to deploy or create it in the game.
 */
public abstract class Plant extends Character {

    private int sunCost;

    /**
     * Constructs a Plant with the specified hit points and sun cost.
     *
     * @param hitPoints the initial amount of hit points for the plant.
     * @param costSun the sun cost required to deploy the plant.
     */
    public Plant(int hitPoints, int costSun) {
        super(hitPoints);
        this.sunCost = costSun;
    }

    /**
     * Retrieves the sun cost required to deploy or create this plant in the game.
     *
     * @return the sun cost of this plant.
     */
    public int getSunCost() {
        return sunCost;
    }
}