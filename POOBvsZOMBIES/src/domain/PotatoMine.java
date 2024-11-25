package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class PotatoMine extends Defensive {
    private final double TIME = 14;

    /**
     * Constructor for objects of class PotatoMine
     */
    public PotatoMine(int posX, int posY) {
        super(100, 25, posX,posY);
    }

    public double getTime() {
        return this.TIME;
    }

    public void burst(){

    }

}
