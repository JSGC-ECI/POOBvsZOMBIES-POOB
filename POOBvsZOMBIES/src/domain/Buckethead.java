package domain;


/**
 * Represents a Buckethead zombie in the game.
 * Buckethead is a type of zombie with enhanced durability due to the metal bucket
 * it wears as head protection. It inherits the movement and attack capabilities
 * from the Movement class.
 */
public class Buckethead extends Movement{


    /**
     * Constructs a new Buckethead zombie instance with pre-defined attributes.
     * This constructor initializes the Buckethead zombie with enhanced durability and specific damage output,
     * reflecting the additional protection offered by the bucket it wears.
     */
    public Buckethead() {
        super(800, 100, 200);
    }


    @Override
    public void atack(Plant plant) {

    }
}
