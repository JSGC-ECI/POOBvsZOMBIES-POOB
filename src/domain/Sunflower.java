package domain;

/**
 * Represents a Sunflower plant in the game, which is a type of support plant.
 * The Sunflower generates sunlight at specific intervals which can be used to
 * grow additional plants on the board.
 */
public class Sunflower extends Support{
    /**
     * Constructs a new Sunflower instance with predefined attributes.
     * The Sunflower plant generates sun over time at specific intervals.
     * It extends the Support class and is initialized with hit points, sun cost,
     * sun value generation, and time interval for sun production.
     */
    public Sunflower(){
        super(300,50,25, 20);

    }

}
