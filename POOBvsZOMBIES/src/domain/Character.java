package domain;

/**
 * Represents a character in a game with basic functionality such as health management.
 * This abstract class serves as a base class for different types of characters
 * such as plants or zombies in the game.
 */
public abstract class Character {
    private int hitPoints;

    /**
     * Constructs a new character with the specified initial hit points.
     * This constructor initializes the `hitPoints` field, representing
     * the health points of the character.
     *
     * @param hitPoints the initial health points of the character. This value
     *                  determines the starting health of the character and
     *                  should be a positive integer.
     */
    public Character(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Retrieves the current hit points of the character.
     * Hit points represent the health of the character, and this method
     * provides the value of the character's current health.
     *
     * @return the current hit points of the character as an integer.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Updates the current hit points of the character by subtracting the specified damage amount.
     * This method reduces the character's health based on the given damage value.
     *
     * @param damage the amount of damage to subtract from the character's current hit points.
     *               It should be a non-negative integer.
     */
    public void setHitPoints(int damage) {
        this.hitPoints = hitPoints - damage;
    }

    /**
     * Checks whether the character is alive. A character is considered alive if their
     * current hit points are greater than zero.
     *
     * @return true if the character's hit points are greater than zero, otherwise false.
     */
    public boolean isAlive() {
        return hitPoints > 0;
    }


    /**
     *
     */
//    public void disappear(){
//        if(){
//
//        }
//    }
}