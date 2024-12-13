package domain;

/**
 * Represents a Basic zombie character in the game. The Basic class extends the Movement class,
 * inheriting its properties and behavior. It provides a basic implementation of a zombie's attack action.
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Basic extends Movement{

    /**
     * Constructor for objects of class Basic
     */
    public Basic() {
        super(100, 100, 100);
    }

    @Override
    public void atack() {

    }
}
