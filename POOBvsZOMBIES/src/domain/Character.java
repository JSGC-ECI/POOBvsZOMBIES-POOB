package domain;

public abstract class Character {
    private int hitPoints;

    public Character(int hitPoints) {
        this.hitPoints = hitPoints;
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


    /**
     *
     */
//    public void disappear(){
//        if(){
//
//        }
//    }
}