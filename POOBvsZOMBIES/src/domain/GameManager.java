package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class GameManager {
    private Board board;
    private int sunPoints;
    private List<Zombies> zombies;
    private List<Plant> plants;


    /**
     * Constructor for objects of class GameManager
     */
    public GameManager(int rows, int cols) {
        board = new Board();
        sunPoints = 50;
        zombies = new ArrayList<>();
    }

    /**
     * Añade un zombi al juego
     */
    public void addZombie(Zombies zombie) {
        int row = (int) (Math.random() * 5);
        board.placeZombie(zombie, row);
        zombies.add(zombie);
    }

    /**
     * Recoge puntos soles generados por las plantas
     */
    public void collectSun(int amount) {
        sunPoints += amount;
    }

    /**
     * Recoge puntos soles generados por las plantas
     */
    public void updateGame() {
        board.moveZombies();

        if (zombies.isEmpty()) {
            //¡Ganaste! No quedan zombis
        }
    }

    /**
     *
     * @return
     */
    public int getSunPoints() {
        return sunPoints;
    }
}