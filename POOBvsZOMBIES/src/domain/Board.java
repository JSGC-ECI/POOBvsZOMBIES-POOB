package domain;

import java.util.ArrayList;
import java.util.List;
import domain.Zombies;
import presentation.Game;

import javax.swing.*;

public class Board {
    private final int ROWS = 5;
    private static final int COLUMNS = 10;
    private static Cell[][] cells;
    private static boolean[] lawnMower;
    private int sunPoints;
    private int brainPoints;
    private List<Zombies> zombies;

    public Board(int initialSunPoints, int initialBrainPoints) {
        this.sunPoints = initialSunPoints;
        this.brainPoints = initialBrainPoints;
        cells = new Cell[ROWS][COLUMNS];
        lawnMower = new boolean[ROWS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                cells[i][j] = new Cell(i, j);
            }
            lawnMower[i] = true;
        }
        zombies = new ArrayList<>();
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }

    public int getSunPoints() {
        return sunPoints;
    }

    public int getBrainPoints() {
        return brainPoints;
    }


    public boolean placePlant(Plant plant, int row, int col) {
        if (isValidPosition(row, col) && cells[row][col].isEmpty() ) {
            cells[row][col].setPlant(plant);
            return true;
        }
        return false;
    }

    public boolean placeZombie(Zombies zombie, int row) {
        if (isValidPosition(row, COLUMNS - 1) && cells[row][COLUMNS - 1].isEmpty()) {
            cells[row][COLUMNS - 1].setZombie(zombie);
            return true;
        }
        return false;
    }


    public void moveZombies() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = COLUMNS - 1; j > 0; j--) {
                if (cells[i][j].hasZombie()) {
                    Zombies zombie = cells[i][j].getZombie();
                    if (zombie instanceof Movement) {
                        ((Movement) zombie).move(this, i, j);
                    }
                }
            }
        }
    }

    public static boolean handlePodadora(int row) {
        if (lawnMower[row]) {
            for (int j = 0; j < COLUMNS; j++) {
                if (cells[row][j].hasZombie()) {
                    cells[row][j].removeZombie();
                }
            }
            lawnMower[row] = false;
            return false;
        } else {
            return true;
        }
    }
    public static boolean winner(){
        for (int h = 0; h < 5; h++){
            if (handlePodadora(h)) {
                return true;
            }
        }
        return false;
    }



    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    private String getPlantName(Plant plant) {
        if (plant instanceof Sunflower) {
            return "Sunflower";
        } else if (plant instanceof Peashooter) {
            return "Peashooter";
        } else if (plant instanceof WallNut) {
            return "Wall-nut";
        } else if (plant instanceof PotatoMine) {
            return "Potato Mine";
        } else if (plant instanceof ECIPlant) {
            return "ECI Plant";
        } else {
            throw new POOBvsZOMBIESException(POOBvsZOMBIESException.NO_PLANT);
        }
    }

    public Plant getPlantByName(String plantName) {
        switch (plantName) {
            case "Sunflower":
                return new Sunflower();
            case "Peashooter":
                return new Peashooter();
            case "WallNut":
                return new WallNut();
            case "PotatoMine":
                return new PotatoMine();
            case "ECIPlant":
                return new ECIPlant();
            case "Evolve":
                return new Evolve();
            default:
                throw new POOBvsZOMBIESException(POOBvsZOMBIESException.NO_PLANT);
        }
    }

    public Zombies getZombieByName(String zombieName) {
        switch (zombieName) {
            case "Basic":
                return new Basic();
            case "Conehead":
                return new Conehead();
            case "Buckethead":
                return new Buckethead();
            case "Brainstein":
                return new Brainstein();
            case "ECIZombie":
                return new ECIZombie();
            default:
                throw new POOBvsZOMBIESException(POOBvsZOMBIESException.NO_ZOMBIE);
        }
    }


    public Cell getCell(int row, int i) {
        return cells[row][i];
    }

    public void removeZombie(int row, int col) {
        if (cells[row][col].hasZombie()) {
            cells[row][col].removeZombie();
        }
    }


    public boolean addZombie(Zombies zombie, int row, int col) {
        if (isValidPosition(row, col) && isEmpty(row, col)) {
            cells[row][col].setZombie(zombie);
            zombies.add(zombie);
            return true;
        }
        return false;
    }

    public boolean isEmpty(int row, int col) {
        if (!isValidPosition(row, col)) {
            return false;
        }
        return cells[row][col].isEmpty();
    }

}