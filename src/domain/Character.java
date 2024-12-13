package domain;

/**
 * An abstract class representing a game character with hit points. This class provides common
 * functionalities for character objects, such as managing hit points and checking if the
 * character is still alive. Subclasses are expected to extend this class to create specific
 * types of characters with additional attributes and behaviors.
 */
public abstract class Character {
    private int hitPoints;

    /**
     * Constructs a Character with the specified initial hit points.
     *
     * @param hitPoints the initial amount of hit points for the character.
     */
    public Character(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Retrieves the current hit points of the character.
     *
     * @return the current hit points of the character.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Reduces the character's hit points by the specified damage value.
     *
     * @param damage the amount of damage to subtract from the character's hit points.
     */
    public void setHitPoints(int damage) {
        this.hitPoints = hitPoints - damage;
    }

    /**
     * Checks if the character is still alive based on its hit points.
     *
     * @return true if the character's hit points are greater than zero, indicating that the character is alive; false otherwise.
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