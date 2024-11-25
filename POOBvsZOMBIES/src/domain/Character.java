package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Character {
    private int hitPoints;
    private int posX;
    private int posy;
    private int cost;

    /**
     * Constructor for objects of class Character
     */
    public Character(int hitPoints, int cost, int posX, int posY) {
        this.hitPoints = hitPoints;
        this.posX = posX;
        this.posy = posY;
        this.cost = cost;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int damage) {
        this.hitPoints = hitPoints - damage;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posy;
    }

    public int getCost() {
        return cost;
    }

//    public void disappear(){
//        if(){
//
//        }
//    }
}
