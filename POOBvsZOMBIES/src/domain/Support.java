package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public abstract class Support extends Plant{
    public int sunValue;
    public double time;

    /**
     * Constructor for objects of class Support
     */
    public Support(int hitPoints, int cost, int sunValue, double time, int posX, int posY) {
        super(hitPoints, cost, posX, posY);
        this.sunValue = sunValue;
        this.time = time;
    }

    public int getSunValue() {
        return sunValue;
    }

    public double getTime() {
        return time;
    }

    public abstract void generateSun();
}
