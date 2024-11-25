package domain;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Brainstein extends Generate{

    /**
     * Constructor for objects of class Brainstein
     */
    public Brainstein(){
        super(300,1,1,50,25);

    }

    public Brainstein(int posY) {
        super(300,posY,0,50,1);
    }

    /**
     *
     */
    @Override
    public void generateBrain() {

    }
}
