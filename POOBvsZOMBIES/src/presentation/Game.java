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
 * The Game class represents the main game window for the application
 * "POOBvsZOMBIES". It extends from JFrame and contains the game's primary
 * components including the board, panels for choosing plants and zombies, as
 * well as managing the game's state and actions.
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Game extends JFrame {

    private JPanel boardG;
    private JPanel game;
    private JPanel plants;
    private JPanel zombies;
    private String selectedPlant;
    private String selectedZombie;
    private Clip backgroundMusic;
    private Board board;
    private GameManager gameManager;
    private JLabel sunPointsLabel;
    private JPanel pointsPanel;

    /**
     * Constructor for objects of class Game
     */
    public Game() {
        gameManager = GameManager.getInstance();
        gameManager.setGame(this);
        gameManager = new GameManager(5, 10);
        setTitle("POOBvsZOMBIES (Game)");
        prepareElements();
        prepareActions();
        board = new Board();
    }

    /**
     * Updates the sun points label to display the current number of sun points. This method
     * updates the label text on the Event Dispatch Thread to ensure thread safety with Swing components.
     *
     * @param sunPoints the current number of sun points to be displayed on the label
     */
    public void updateSunPointsLabel(int sunPoints) {
        SwingUtilities.invokeLater(() -> sunPointsLabel.setText("Sun Points: " + sunPoints));
    }


    /**
     * Prepares the initial elements and user interface for the game.
     *
     * This method sets up the window size to half the dimensions of the user's screen
     * and centers the window on the screen. It also initializes the background music
     * and invokes the preparation of game board elements.
     *
     * It sets the window size to be half of the screen dimensions by:
     * - Retrieving the screen size using the toolkit.
     * - Setting the window size using the screen dimensions.
     *
     * The method centers the game window on the screen using:
     * - setLocationRelativeTo with a null parameter.
     *
     * The method plays background music by specifying the music file path:
     * - The music file is located in the specified directory and looped continuously.
     *
     * Finally, it calls the prepareElementsBoard method to set up the game board
     * elements required for the game.
     *
     * Assumptions:
     * - The specified music file path is valid and the file exists.
     * - The game board preparation is handled correctly by prepareElementsBoard().
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);

        playBackgroundMusic("src/presentation/resources/audio/game.wav");
        prepareElementsBoard();
    }

    /**
     * Prepares the actions for the game window.
     *
     * This method sets the default close operation of the JFrame to DO_NOTHING_ON_CLOSE,
     * preventing the default close operation. It adds a window listener to handle the
     * window closing event. When the user attempts to close the window, the listener
     * invokes the exit() method, which prompts the user with a confirmation dialog,
     * and closes the application if the user confirms.
     */
    public void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }

    /**
     * Exits the application with a user confirmation dialog.
     *
     * This method prompts the user with a confirmation dialog to verify if they
     * truly want to close the application. If the user selects "Yes," the
     * application resources are disposed of and the program exits.
     *
     * The dialog presented has options for "Yes" and "No," where selecting "Yes"
     * confirms the decision to exit, and "No" will cancel the operation, allowing
     * the application to continue running.
     */
    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Plays background music in a continuous loop from a specified file path.
     * If the music file is found, it will start playing continuously.
     * If the file does not exist, an error message will be printed to the console.
     * Any exceptions during file loading or playback will also be printed to the console.
     *
     * @param musicPath the file path of the music to be played
     */
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

    /**
     * Prepares the main game board by initializing and arranging various UI components
     * such as the game panel, elements board, plants panel, zombies panel, and points panel.
     * This method sets up the layout, backgrounds, buttons, and their associated actions.
     *
     * The method does the following:
     * <ul>
     *   <li>Initializes the main game panel with a BorderLayout and a button on the west side.</li>
     *   <li>Creates a background panel with a custom image and places a grid of buttons on it,
     *       representing the board where plants and zombies can be placed.</li>
     *   <li>Configures each tile button in the grid to be transparent and adds an action listener
     *       to handle plant placement.</li>
     *   <li>Sets up the plants panel with custom background and adds buttons for each plant type,
     *       configuring their actions to select plants when clicked.</li>
     *   <li>Sets up the zombies panel similarly with a custom background and buttons for each zombie type,
     *       configuring their actions to select zombies when clicked.</li>
     *   <li>Creates a points panel on the east side to display the current sun points, showing the
     *       player's resources used for planting.</li>
     * </ul>
     *
     * This method is crucial for setting up the visual and interactive elements necessary for gameplay.
     * Each UI component and button is integrated into specific regions of the main game panel to ensure
     * a structured and intuitive layout for players.
     */
    public void prepareElementsBoard() {
        game = new JPanel();
        game.setLayout(new BorderLayout());
        add(new Button("West"), BorderLayout.WEST);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("presentation/resources/images/Fondos/backyard.jpg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        boardG = new JPanel();
        boardG.setLayout(new GridLayout(5, 10));
        boardG.setOpaque(false);

        for (int i = 0; i < 50; i++) {
            int row = i / 10;
            int col = i % 10;
            JButton tile = new JButton();
            tile.setOpaque(false);
            tile.setContentAreaFilled(false);
            tile.setBorderPainted(true);
            tile.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            tile.addActionListener(e -> placePlant(row, col, tile));
            boardG.add(tile);
        }
        backgroundPanel.add(boardG, BorderLayout.CENTER);
        add(backgroundPanel, BorderLayout.CENTER);

        plants = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("presentation/resources/images/Plantas/Targetas/SeedBank.png");
                int imageWidth = background.getIconWidth();
                int imageHeight = background.getIconHeight();
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int x = (panelWidth - imageWidth) / 2;
                int y = (panelHeight - imageHeight) / 2;
                g.drawImage(background.getImage(), x, y, this);
            }
        };
        plants.setLayout(new FlowLayout(FlowLayout.CENTER,-30, 0));
        JButton button1 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Sunflower.png"));
        button1.addActionListener(e -> selectPlant("Sunflower"));
        setInvisibleButton(button1);
        plants.add(button1);
        JButton button2 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Peashooter.png"));
        button2.addActionListener(e -> selectPlant("Peashooter"));
        setInvisibleButton(button2);
        plants.add(button2);
        JButton button3 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Wall-nut.png"));
        button3.addActionListener(e -> selectPlant("WallNut"));
        setInvisibleButton(button3);
        plants.add(button3);
        JButton button4 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/PotatoMine.png"));
        button4.addActionListener(e -> selectPlant("PotatoMine"));
        setInvisibleButton(button4);
        plants.add(button4);
        JButton button5 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/ECIPlant.png"));
        button5.addActionListener(e -> selectPlant("ECIPlant"));
        setInvisibleButton(button5);
        plants.add(button5);
        add(plants, BorderLayout.NORTH);

        zombies = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("presentation/resources/images/Zombies/Targetas/PackagesDead.png");
                int imageWidth = background.getIconWidth();
                int imageHeight = background.getIconHeight();
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int x = (panelWidth - imageWidth) / 2;
                int y = (panelHeight - imageHeight) / 2;
                g.drawImage(background.getImage(), x, y, this);
            }
        };
        zombies.setLayout(new FlowLayout(FlowLayout.CENTER,-30, 0));
        JButton buttonBasic = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/Basic.png"));
        buttonBasic.addActionListener(e -> selectZombie("Basic"));
        setInvisibleButton(buttonBasic);
        zombies.add(buttonBasic);
        JButton buttonConehead = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/Conehead.png"));
        buttonConehead.addActionListener(e -> selectZombie("Conehead"));
        setInvisibleButton(buttonConehead);
        zombies.add(buttonConehead);
        JButton buttonBuckethead = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/Buckethead.png"));
        buttonBuckethead.addActionListener(e -> selectZombie("Buckethead"));
        setInvisibleButton(buttonBuckethead);
        zombies.add(buttonBuckethead);
        JButton buttonBrainstein = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/Brainstein.png"));
        buttonBrainstein.addActionListener(e -> selectZombie("Brainstein"));
        setInvisibleButton(buttonBrainstein);
        zombies.add(buttonBrainstein);
        JButton buttonECIZombie = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/ECIZombie.png"));
        buttonECIZombie.addActionListener(e -> selectZombie("ECIZombie"));
        setInvisibleButton(buttonECIZombie);
        zombies.add(buttonECIZombie);
        add(zombies, BorderLayout.SOUTH);

        // Panel para los puntos de sol
        pointsPanel = new JPanel();
        pointsPanel.setLayout(new GridLayout(2, 1));
        sunPointsLabel = new JLabel("Sun Points: " + GameManager.getInstance().getSunPoints());
        sunPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunPointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsPanel.add(sunPointsLabel);
        add(pointsPanel, BorderLayout.EAST);
    }

    /**
     * Sets the button's appearance to be invisible by making it transparent.
     * This method alters the JButton so that it does not draw its own opaque
     * background, content area, or border.
     *
     * @param button the JButton that will be made invisible
     */
    private void setInvisibleButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    /**
     * Selects a plant for the game by setting the specified plant as the current selection.
     *
     * @param plant the name of the plant to be selected
     */
    private void selectPlant(String plant) {
        selectedPlant = plant;
    }

    /**
     * Places a plant on the game board at the specified row and column.
     * If no plant is currently selected, a message is shown to the user
     * prompting them to select a plant first. Once a plant is placed, the
     * corresponding cell button is updated with the plant's image, and the
     * button is disabled to prevent further interactions.
     *
     * @param row the row index on the board where the plant is to be placed
     * @param col the column index on the board where the plant is to be placed
     * @param cellButton the JButton representing the cell on the board, which will be updated with the plant's image
     */
    private void placePlant(int row, int col, JButton cellButton) {
        if (selectedPlant == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una planta primero.");
            return;
        }
        if (GameManager.getInstance().placePlant(board.getPlantByName(selectedPlant),row,col)) {
            String gifPath = getGifForPlant(selectedPlant);
            cellButton.setIcon(new ImageIcon(gifPath));
            cellButton.setDisabledIcon(cellButton.getIcon());
            cellButton.setEnabled(false);
            selectedPlant = null;
        }
    }

    /**
     * Retrieves the file path of the GIF image corresponding to a specified plant.
     *
     * @param plant the name of the plant for which the GIF file path is required
     * @return the file path of the GIF image if the plant is recognized; otherwise, returns an empty string
     */
    private String getGifForPlant(String plant) {
        switch (plant) {
            case "Sunflower":
                return "presentation/resources/images/Plantas/gif/Sunflower.gif";
            case "Peashooter":
                return "presentation/resources/images/Plantas/gif/Peashooter.gif";
            case "WallNut":
                return "presentation/resources/images/Plantas/gif/Wall-nut.gif";
            case "PotatoMine":
                return "presentation/resources/images/Plantas/gif/PotatoMine.gif";
            case "ECIPlant":
                return "presentation/resources/images/Plantas/Targetas/ECIPlant.gif";
            default:
                return "";
        }
    }

    /**
     * Selects a zombie for the game by setting the specified zombie as the current selection.
     *
     * @param zombie the name of the zombie to be selected
     */
    private void selectZombie(String zombie) {
        selectedZombie = zombie;
    }

    /**
     * Places a zombie on the game board at the specified row and column. If no
     * zombie is currently selected, a message is shown to the user prompting them
     * to select a zombie first. Zombies can only be placed in the last column.
     * Once a zombie is placed, the corresponding cell button is updated with the
     * zombie's image, and the button is disabled to prevent further interactions.
     *
     * @param row the row index on the board where the zombie is to be placed
     * @param col the column index on the board where the zombie is to be placed
     * @param cellButton the JButton representing the cell on the board, which will be updated with the zombie's image
     */
    public void placeZombie(int row, int col, JButton cellButton) {
        if (selectedZombie == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un zombie primero.");
            return;
        }
        if (col != 9) {
            JOptionPane.showMessageDialog(this, "Los zombis solo pueden ser colocados en la última columna.");
            return;
        }
        if (GameManager.getInstance().placeZombie(board.getZombieByName(selectedZombie), row, col)) {
            String gifPath = getGifForZombie(selectedZombie);
            cellButton.setIcon(new ImageIcon(gifPath));
            cellButton.setDisabledIcon(cellButton.getIcon());
            cellButton.setEnabled(false);
            selectedZombie = null;
        }
    }


    /**
     * Retrieves the file path of the GIF image corresponding to a specified zombie.
     *
     * @param zombie the name of the zombie for which the GIF file path is required
     * @return the file path of the GIF image if the zombie is recognized; otherwise, returns an empty string
     */
    private String getGifForZombie(String zombie) {
        switch (zombie) {
            case "Basic":
                return "presentation/resources/images/Zombies/Basic.gif";
            case "Conehead":
                return "presentation/resources/images/Zombies/Conehead.gif";
            case "Buckethead":
                return "presentation/resources/images/Zombies/Buckethead.gif";
            case "Brainstein":
                return "presentation/resources/images/Zombies/Brainstein.gif";
            case "ECIZombie":
                return "presentation/resources/images/Zombies/ECIZombie.png";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}
