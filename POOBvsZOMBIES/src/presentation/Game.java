package presentation;

import domain.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Game extends JFrame {

    private JPanel boardG;
    private JPanel game;
    private JPanel plants;
    private JPanel zombies;
    private Plant selectedPlant;
    private Clip backgroundMusic;
    private Board board;

    /**
     * Constructor for objects of class Game
     */
    public Game() {
        setTitle("POOBvsZOMBIES (Game)");
        prepareElements();
        prepareActions();
        board = new Board();
    }

    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);

        playBackgroundMusic("presentation/resources/audio/game.wav");
        prepareElementsBoard();
    }

    public void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }

    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private void playBackgroundMusic(String musicPath) {
        try {
            File musicFile = new File(musicPath);
            if (musicFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioStream);
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Archivo de música no encontrado: " + musicPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareElementsBoard() {
        game = new JPanel();
        game.setLayout(new BorderLayout());
        add(new Button("East"), BorderLayout.EAST);
        add(new Button("West"), BorderLayout.WEST);

        boardG = new JPanel();
        boardG.setLayout(new GridLayout(5, 10));
        for (int i = 0; i < 50; i++) {
            JButton tile = new JButton();
            int row = i / 10;
            int col = i % 10;
            tile.addActionListener(e -> placePlant(row, col, tile));
            boardG.add(tile);
        }
        add(boardG, BorderLayout.CENTER);


        plants = new JPanel();
        plants.setLayout(new GridLayout(1, 5));

        JButton button1 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Sunflower.png"));
        button1.addActionListener(e -> selectPlant(new Sunflower(0, 0)));
        plants.add(button1);

        JButton button2 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Peashooter.png"));
        button2.addActionListener(e -> selectPlant(new Peashooter(0, 0)));
        plants.add(button2);

        JButton button3 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Wall-nut.png"));
        button3.addActionListener(e -> selectPlant(new WallNut(0, 0)));
        plants.add(button3);

        JButton button4 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/PotatoMine.png"));
        button4.addActionListener(e -> selectPlant(new PotatoMine(0, 0)));
        plants.add(button4);

        JButton button5 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/ECIPlant.png"));
        button5.addActionListener(e -> selectPlant(new ECIPlant(0, 0)));
        plants.add(button5);

        add(plants, BorderLayout.NORTH);

        // Panel para los zombis
        zombies = new JPanel();
        zombies.setLayout(new GridLayout(1, 5));
        add(zombies, BorderLayout.SOUTH);
    }

    private void selectPlant(Plant plant) {
        selectedPlant = plant;
    }

    private void placePlant(int row, int col, JButton cellButton) {
        if (selectedPlant != null && board.placePlant(selectedPlant, row, col)) {
            String plantName = getPlantName(selectedPlant);
            cellButton.setText(plantName);
            //String gifPath = getGifForPlant(selectedPlant);
            //cellButton.setIcon(new ImageIcon(gifPath));
            cellButton.setDisabledIcon(cellButton.getIcon());
            cellButton.setEnabled(false);
            selectedPlant = null;
        } else {
            JOptionPane.showMessageDialog(this, "No se puede colocar la planta aquí.");
        }
    }

    private String getGifForPlant(Plant plant) {
        if (plant instanceof Sunflower) {
            return "presentation/resources/images/Plantas/Targetas/Sunflower.gif";
        } else if (plant instanceof Peashooter) {
            return "presentation/resources/images/Plantas/Targetas/Peashooter.gif";
        } else if (plant instanceof WallNut) {
            return "presentation/resources/images/Plantas/Targetas/Wall-nut.gif";
        } else if (plant instanceof PotatoMine) {
            return "presentation/resources/images/Plantas/Targetas/PotatoMine.gif";
        } else if (plant instanceof ECIPlant) {
            return "presentation/resources/images/Plantas/Targetas/ECIPlant.gif";
        } else {
            return "";
        }
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
            return "Planta desconocida";
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}
