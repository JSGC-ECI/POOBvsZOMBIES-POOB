package domain;

import presentation.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class GameManager {
    private Board board;
    private static int sunPoints;
    private int brainPoints;
    private List<Zombies> zombies;
    private static GameManager instance;
    private static Game game;
    private int gameTime;

    public GameManager(int initialSunPoints, int initialBrainPoints,int initialTime ) {
        board = new Board(initialSunPoints, initialBrainPoints);
        this.sunPoints = initialSunPoints;
        this.brainPoints = initialBrainPoints;
        this.gameTime = initialTime;
        this.zombies = new ArrayList<>();    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            int defaultSunPoints = 100;
            int defaultBrainPoints = 50;
            int defaultGameTime = 300;

            instance = new GameManager(defaultSunPoints, defaultBrainPoints, defaultGameTime);
        }
        return instance;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public boolean placePlant(Plant plant, int row, int col) {
        if (sunPoints >= plant.getSunCost()) {
            if (board.placePlant(plant, row, col)) {
                sunPoints -= plant.getSunCost();
                if (game != null) {
                    game.updateSunPointsLabel(sunPoints);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public void placeZombie(Zombies zombie) {
        int row = board.getRows() - 1;
        int maxAttempts = 10;
        int attempts = 0;
        int col;
        do {
            col = (int) (Math.random() * board.getColumns());
            attempts++;
        } while (!board.isEmpty(row, col) && attempts < maxAttempts);

        if (board.isEmpty(row, col)) {
            board.addZombie(zombie, row, col);
            zombies.add(zombie);
        } else {
            System.out.println("No se pudo colocar el zombi después de " + maxAttempts + " intentos.");
        }
    }


    public void updateGame() {
        board.moveZombies();
        if (zombies.isEmpty()) {
            //No quedan zombis
        }
    }

    public int getSunPoints() {
        return sunPoints;
    }

    public void collectSun(int amount) {
        sunPoints += amount;
        if (game != null) {
            game.updateSunPointsLabel(sunPoints);
        }
    }

    public void moveZombies() {
        board.moveZombies();
    }

    public int getGameTime() {
        return gameTime;
    }

}