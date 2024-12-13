package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Character {
    private int hitPoints;

    /**
     * Constructor for objects of class Character
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