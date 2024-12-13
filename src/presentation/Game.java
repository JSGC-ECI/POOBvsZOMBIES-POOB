package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * The Game class manages the main functionality of a Plants vs. Zombies-inspired game.
 * It handles the game board setup, player actions, score updates, and game timer.
 */
public class Game extends JFrame {

    private JPanel boardG;
    private JPanel game;
    private JPanel plants;
    private JPanel zombies;
    private String selectedPlant;
    private String selectedZombie;
    private Board board;
    private GameManager gameManager;
    private JLabel sunPointsLabel;
    private JLabel brainPointsLabel;
    private JPanel pointsPanel;
    private int sunPoints;
    private int brainPoints;
    private JLabel timeLabel;
    private Timer gameTimer;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem Item1;
    private JMenuItem Item2;
    private JMenuItem Item3;
    private JMenuItem Item4;
    private JMenuItem Item5;
    private JMenuItem Item6;


    /**
     * Initializes a new instance of the Game class.
     * This constructor sets up the Game by obtaining a singleton instance of the GameManager,
     * configuring initial game parameters, and preparing the necessary game elements and actions.
     * The game title is set, and the main game board, sun points, and brain points are initialized.
     */
    public Game() {
        gameManager = GameManager.getInstance();
        gameManager.setGame(this);
        gameManager = new GameManager(sunPoints, brainPoints, gameManager.getGameTime() );
        setTitle("POOBvsZOMBIES (Game)");
        prepareElements();
        prepareActions();
        board = new Board(sunPoints, brainPoints);
    }

    /**
     * Updates the text of the sun points label to display the current amount of sun points.
     * This method ensures that the update to the label's text is performed on the Event Dispatch Thread.
     *
     * @param sunPoints the current number of sun points to be displayed
     */
    public void updateSunPointsLabel(int sunPoints) {
        SwingUtilities.invokeLater(() -> sunPointsLabel.setText("Sun Points: " + sunPoints));
    }

    /**
     * Updates the text of the brain points label to display the current amount of brain points.
     * This method ensures that the update to the label's text is performed on the Event Dispatch Thread.
     *
     * @param brainPoints the current number of brain points to be displayed
     */
    public void updateBrainPointsLabel(int brainPoints) {
        SwingUtilities.invokeLater(() -> brainPointsLabel.setText("Brain Points: " + brainPoints));
    }

    /**
     * Prepares the initial visual and interactive elements of the game interface.
     *
     * This method sets up the main game window by configuring its size to half
     * the screen dimensions and centering it on the screen. It also prompts the
     * user for initial game values such as sun points and brain points. The game
     * board and menu elements are then prepared, laying out the necessary components
     * and interactive elements for gameplay.
     *
     * The method accomplishes the following actions:
     * 1. Configures the window size to half of the screen's width and height.
     * 2. Positions the window in the center of the screen.
     * 3. Prompts the user to input initial game parameters.
     * 4. Prepares the game board visual components and layout.
     * 5. Sets up the menu structure required for the game.
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        promptForInitialValues();
        //AudioManager.playBackgroundMusic("presentation/resources/audio/game.wav");
        prepareElementsBoard();
        prepareElementsMenu();
    }


    /**
     * Prepares the actions for the game, including handling window closing events.
     *
     * This method sets the default close operation to do nothing, preventing the
     * window from closing immediately when the user attempts to close it. It also
     * adds a window listener to handle the window closing event, prompting the user
     * with a confirmation dialog to decide whether to exit the game.
     *
     * The method delegates the actual exit process to the `exit()` method if the
     * user confirms the intention to close the game.
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
     * Initiates the process for closing the game application.
     *
     * This method presents the user with a confirmation dialog to verify their intention
     * to exit the game. If the user confirms, the method proceeds to dispose of the game
     * window and terminates the application. It uses a modal confirmation dialog to ensure
     * that the user explicitly acknowledges the desire to close the application, thereby
     * preventing accidental termination.
     *
     * The confirmation dialog offers 'Yes' or 'No' options, only proceeding with the exit
     * if the user selects 'Yes'.
     */
    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Prompts the user to input initial values for the game, including sun points, brain points, and game time.
     *
     * This method uses dialog boxes to request input from the user and attempts to parse these inputs into integer values.
     * If valid numbers are entered, a new GameManager instance is created with the specified values. In the event of
     * invalid input (such as non-numeric values), an error message is displayed. The game state is then updated to reflect
     * the entered values by updating the labels for sun points and brain points.
     *
     * The method performs the following actions:
     * 1. Displays dialog boxes to prompt the user for sun points, brain points, and game time.
     * 2. Parses input strings into integers.
     * 3. Creates a new GameManager with the entered values if inputs are valid.
     * 4. Displays an error message if any input is invalid.
     * 5. Updates the labels for sun points and brain points with the new values.
     *
     * The method is essential for initializing game settings based on user input, ensuring that gameplay starts with
     * the desired configurations.
     */
    private void promptForInitialValues() {
        String sunInput = JOptionPane.showInputDialog(this, "Ingrese los puntos de sol iniciales:");
        String brainInput = JOptionPane.showInputDialog(this, "Ingrese los cerebros iniciales:");
        String timeInput = JOptionPane.showInputDialog(this, "Ingrese el tiempo de la partida en segundos:");

        try {
            sunPoints = Integer.parseInt(sunInput);
            brainPoints = Integer.parseInt(brainInput);
            int gameTime = Integer.parseInt(timeInput);

            gameManager = new GameManager(sunPoints, brainPoints, gameTime);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.");
        }

        updateSunPointsLabel(sunPoints);
        updateBrainPointsLabel(brainPoints);
    }

    /**
     * Initializes and prepares the main elements of the game board for a Plants vs. Zombies style game.
     * This method sets up the game's graphical user interface components and their layouts, including backgrounds,
     * buttons, and points labels. It integrates features into regions of a border layout, allowing for a structured
     * interface that displays plants and zombies, sun and brain points indicators, and a timer.
     *
     * The game board is divided into five sections:
     * 1. The west region holds the main game panel with a layout for interaction buttons.
     * 2. The center region contains a grid of interactive tiles where players can place plants.
     * 3. The north region features a selection panel with buttons to choose various plant types.
     * 4. The south region includes a selection panel to choose different zombie types.
     * 5. The east region displays crucial game statistics such as sun and brain points along with remaining game time.
     *
     * The method also configures individual button properties, including visual settings and action listeners
     * to handle player interactions. For each button within the plant and zombie selection sections,
     * visual customization and event linking (such as selecting a plant or zombie) are implemented.
     *
     * A custom background image is applied to various panels, enhancing visual appeal. The method ensures
     * that the game components are optimally laid out and interactive from the game start.
     *
     * Additionally, the game timer is initiated at the end of the setup to reflect the game's time dynamics.
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

        // Panel de puntos de sol y cerebros
        pointsPanel = new JPanel();
        pointsPanel.setLayout(new GridLayout(2, 1));

        sunPointsLabel = new JLabel("Sun Points: " + sunPoints);
        sunPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunPointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsPanel.add(sunPointsLabel);

        brainPointsLabel = new JLabel("Brain Points: " + brainPoints);
        brainPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brainPointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsPanel.add(brainPointsLabel);

        timeLabel = new JLabel("Tiempo restante: " + gameManager.getGameTime() + " min");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsPanel.add(timeLabel);

        add(pointsPanel, BorderLayout.EAST);

        startGameTimer();
    }


    /**
     * Initializes and starts the game timer which counts down every second.
     * The timer updates the remaining game time displayed on the UI and manages
     * the game's end when time runs out.
     *
     * The timer operates with a delay of one second (1000 milliseconds) and is associated
     * with an ActionListener that checks the remaining time at each tick. If the time
     * is greater than zero, it decreases the time by one second and updates the display
     * label with the new time. Once the time reaches zero, the timer stops and a
     * message dialog is shown indicating that the game has ended.
     *
     * The timer ensures that time-dependent game actions are performed consistently
     * every second, contributing to the game flow.
     */
    private void startGameTimer() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int remainingTime = gameManager.getGameTime();
                if (remainingTime > 0) {
                    gameManager.setGameTime(remainingTime - 1);
                    timeLabel.setText("Tiempo restante: " + remainingTime + " seg");
                } else {
                    gameTimer.stop();
                    JOptionPane.showMessageDialog(Game.this, "¡Se acabó el tiempo! El juego ha terminado. En construccion");
                }
            }
        });
        gameTimer.start();
    }


    /**
     * Configures a JButton to be invisible by removing its default visual properties.
     *
     * This method modifies the button's appearance by setting several properties to
     * give it an invisible look. These changes include making the button non-opaque,
     * removing the content area fill, and not painting its border.
     *
     * @param button the JButton to be made invisible. The method alters its appearance
     *               properties to ensure that the button does not display its background
     *               and border, effectively rendering it invisible.
     */
    private void setInvisibleButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }


    /**
     * Selects a plant for use in the game by updating the currently selected plant.
     *
     * @param plant the name of the plant to be selected
     */
    private void selectPlant(String plant) {
        selectedPlant = plant;
    }

    /**
     * Attempts to place the selected plant at the specified position on the game board.
     * This method checks whether a plant has been selected and places it on the board
     * if the position is valid and available. After placing the plant, it updates the button
     * to display the plant's image and disables it to prevent further interaction.
     *
     * @param row the row index on the game board where the plant is to be placed
     * @param col the column index on the game board where the plant is to be placed
     * @param cellButton the JButton representing the cell on the board where the plant will be placed
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
     * Returns the file path to the GIF image associated with the specified plant name.
     *
     * @param plant the name of the plant for which the GIF file path is requested
     * @return the file path to the corresponding plant GIF image; returns an empty
     *         string if the plant name does not match any known plants
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
     * Selects a zombie for use in the game by updating the currently selected zombie.
     *
     * @param zombie the name of the zombie to be selected
     */
    private void selectZombie(String zombie) {
        selectedZombie = zombie;
    }


    /**
     * Places a zombie on the game board at the specified row and column using the specified button.
     * If no zombie is currently selected, or if the column is not the last one, a message dialog will notify the user.
     *
     * @param row the row on the board where the zombie will be placed
     * @param col the column on the board where the zombie will be placed; must be the last column
     * @param cellButton the JButton that represents the cell on the board where the zombie will be placed
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
     * Retrieves the file path for the GIF image corresponding to the specified zombie type.
     *
     * @param zombie the type of the zombie for which the GIF file path is needed.
     *               This can be one of the following: "Basic", "Conehead", "Buckethead",
     *               "Brainstein", or "ECIZombie".
     * @return the file path to the GIF image as a string if the zombie type is recognized;
     *         an empty string otherwise.
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

    /**
     * Prepares the menu bar and its menu items for the application window.
     *
     * This method initializes a `JMenuBar` and sets it to the current window.
     * It creates a "Archivo" menu with several items: "Nuevo", "Abrir", "Guardar como",
     * "Importar", "Exportar como", and "Salir". Each item is separated by a menu separator.
     * The menu bar is constructed and added to the window to provide options for file operations.
     */
    public void prepareElementsMenu() {
        this.menuBar = new JMenuBar();
        this.setJMenuBar(this.menuBar);
        this.menu = new JMenu("Archivo");
        this.menuBar.add(this.menu);
        this.Item1 = new JMenuItem("Nuevo");
        this.menu.add(this.Item1);
        this.menu.addSeparator();
        this.Item2 = new JMenuItem("Abrir");
        this.menu.add(this.Item2);
        this.menu.addSeparator();
        this.Item3 = new JMenuItem("Guardar como");
        this.menu.add(this.Item3);
        this.menu.addSeparator();
        this.Item4 = new JMenuItem("Importar");
        this.menu.add(this.Item4);
        this.menu.addSeparator();
        this.Item5 = new JMenuItem("Exportar como");
        this.menu.add(this.Item5);
        this.menu.addSeparator();
        this.Item6 = new JMenuItem("Salir");
        this.menu.add(this.Item6);
    }

//    public void prepareActionsMenu() {
//        Item1.addActionListener(e -> optionNew());
//        Item2.addActionListener(e -> optionOpen());
//        Item3.addActionListener(e -> optionSave());
//        Item4.addActionListener(e -> optionImport());
//        Item5.addActionListener(e -> optionExport());
//    }

//    public void optionNew() {
//        gameManager = new gameManager();
//        JOptionPane.showMessageDialog(this, "Nuevo");
//    }
//
//    public void optionOpen() {
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int result = chooser.showOpenDialog(this);
//
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = chooser.getSelectedFile();
//            try {
//                gameManager = AManufacturing.open(selectedFile);
//                JOptionPane.showMessageDialog(this, "Archivo cargado correctamente.");
//            } catch (POOBvsZOMBIESException e) {
//                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    public void optionSave() {
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int result = chooser.showSaveDialog(this);
//
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = chooser.getSelectedFile();
//            if (!selectedFile.getName().endsWith(".dat")) {
//                selectedFile = new File(selectedFile.getAbsolutePath() + ".dat");
//            }
//            try {
//                gameManager.save(selectedFile);
//                JOptionPane.showMessageDialog(this, "Archivo guardado correctamente.");
//            } catch (POOBvsZOMBIESException e) {
//                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    public void optionImport() {
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int result = chooser.showOpenDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = chooser.getSelectedFile();
//            try {
//                GameManager.aimport(selectedFile);
//            } catch (POOBvsZOMBIESException e) {
//                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    public void optionExport() {
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int result = chooser.showSaveDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = chooser.getSelectedFile();
//            try {
//                GameManager.export(selectedFile);
//            } catch (POOBvsZOMBIESException e) {
//                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}
