package presentation;

import domain.Board;
import domain.GameManagerPvsMO;
import domain.POOBvsZOMBIESException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static presentation.AudioManager.playBackgroundMusic;

/**
 * GamePvsMO represents the graphical user interface for the Player vs Machine (ZombiesOriginal) game.
 * It manages user interactions, game board setup, timer, and game logic through various components and panels.
 */
public class GamePvsMO extends JFrame {
    private JPanel boardG;
    private JPanel game;
    private JPanel plants;
    private String selectedPlant;
    private Board board;
    private GameManagerPvsMO gameManager;
    private JLabel sunPointsLabel;
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
     * Constructs a GamePvsMO instance and initializes the Player versus Multiplayer Online (PvMO) game mode.
     *
     * This constructor performs the following actions:
     * - Retrieves or creates a singleton instance of GameManagerPvsMO and associates it with the current game.
     * - Initializes a new GameManagerPvsMO instance with specific parameters for sun points, brain points,
     *   and game time based on the retrieved game manager.
     * - Sets the window title for the game.
     * - Prepares the graphical and interactive components of the game window by invoking methods
     *   to initialize game elements (`prepareElements`) and configure user interactions (`prepareActions`).
     * - Creates a new game board associated with the specified sun and brain points.
     */
    public GamePvsMO() {
        gameManager = GameManagerPvsMO.getInstance();
        gameManager.setGame(this);
        gameManager = new GameManagerPvsMO(sunPoints, brainPoints, gameManager.getGameTime() );
        setTitle("POOBvsZOMBIES (Game)");
        prepareElements();
        prepareActions();
        board = new Board(sunPoints, brainPoints);
    }

    /**
     * Updates the label displaying the current amount of Sun Points in the game.
     * The update is performed on the Event Dispatch Thread to ensure thread safety.
     *
     * @param sunPoints the current number of sun points to be displayed in the label
     */
    public void updateSunPointsLabel(int sunPoints) {
        SwingUtilities.invokeLater(() -> sunPointsLabel.setText("Sun Points: " + sunPoints));
    }

    /**
     * Initializes and prepares the essential graphical and interactive components
     * required for the game interface. This method performs the following actions:
     *
     * 1. Sets the initial size and location of the game window based on the screen size.
     * 2. Prompts the user to input initial values such as starting sun points and game time.
     * 3. Plays background music for the game using the AudioManager class with a predefined audio file.
     * 4. Prepares the game board by invoking the method responsible for configuring board elements.
     * 5. Prepares the game menu components by invoking the corresponding initialization method.
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        promptForInitialValues();
        //AudioManager.playBackgroundMusic("src/presentation/resources/audio/game.wav");
        prepareElementsBoard();
        prepareElementsMenu();
    }

    /**
     * Configures the necessary actions and event listeners for the game window.
     *
     * This method performs the following tasks:
     * - Sets the default close operation for the window to prevent it from closing automatically.
     * - Adds a custom behavior for the window closing event, invoking the exit method
     *   to handle the closing process in a controlled manner, which includes prompting
     *   the user for confirmation before the application exits.
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
     * Handles the exit process for the application, including prompting the user
     * for confirmation and closing the application upon confirmation.
     *
     * The method performs the following actions:
     * - Displays a confirmation dialog asking the user if they truly want to exit the application.
     * - If the user selects "Yes" in the confirmation dialog, it:
     *   - Closes and disposes of the main application window.
     *   - Terminates the application by calling System.exit(0).
     */
    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Prompts the user to input initial values for the game, such as the starting sun points and the game duration.
     * These values are required to configure and initialize the game's settings.
     *
     * The method performs the following operations:
     * 1. Displays input dialogs for the user to enter the initial sun points and game time in seconds.
     * 2. Validates the entered values, checking if they are numeric and converting them to integers.
     * 3. Initializes a new instance of GameManagerPvsMO with the provided sun points, brain points, and game time.
     * 4. Handles invalid input by displaying an error message, prompting the user to enter valid values.
     * 5. Updates the label displaying the current sun points using the user-provided initial value.
     *
     * @throws POOBvsZOMBIESException if any specific error related to the game configuration occurs.
     */
    private void promptForInitialValues() throws POOBvsZOMBIESException{
        String sunInput = JOptionPane.showInputDialog(this, "Ingrese los puntos de sol iniciales:");
        String timeInput = JOptionPane.showInputDialog(this, "Ingrese el tiempo de la partida en segundos:");

        try {
            sunPoints = Integer.parseInt(sunInput);
            int gameTime = Integer.parseInt(timeInput);

            gameManager = new GameManagerPvsMO(sunPoints, brainPoints, gameTime);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.");
        }

        updateSunPointsLabel(sunPoints);
    }

    /**
     * Prepares and configures the primary elements and components of the game board
     * for the Player versus Multiplayer Online (PvMO) mode.
     *
     * This method initializes various panels, buttons, and labels essential for
     * interacting with the game, including:
     *
     * 1. Configuring the main game panel with a `BorderLayout`.
     * 2. Creating a background panel with a custom image and adding a
     *    semi-transparent grid to represent board positions.
     * 3. Populating the board grid with clickable tiles, enabling the placement
     *    of plants based on user actions.
     * 4. Initializing the plant selection panel with a selection of predefined plants.
     *    Each plant option is represented by a button with an associated image and
     *    action listener.
     * 5. Setting visibility properties for individual plant buttons to ensure aesthetic consistency.
     * 6. Creating a points panel to display current sun points and the remaining game time.
     *    These labels are continuously updated as the game progresses.
     * 7. Adding necessary components to the main game frame to create a cohesive layout.
     *
     * It also initiates a game timer to monitor the progression of the game session.
     */
    public void prepareElementsBoard() {
        game = new JPanel();
        game.setLayout(new BorderLayout());
        add(new Button("West"), BorderLayout.WEST);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("src/presentation/resources/images/Fondos/backyard.jpg").getImage();
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
                ImageIcon background = new ImageIcon("src/presentation/resources/images/Plantas/Targetas/SeedBank.png");
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
        JButton button1 = new JButton(new ImageIcon("src/presentation/resources/images/Plantas/Targetas/Sunflower.png"));
        button1.addActionListener(e -> selectPlant("Sunflower"));
        setInvisibleButton(button1);
        plants.add(button1);
        JButton button2 = new JButton(new ImageIcon("src/presentation/resources/images/Plantas/Targetas/Peashooter.png"));
        button2.addActionListener(e -> selectPlant("Peashooter"));
        setInvisibleButton(button2);
        plants.add(button2);
        JButton button3 = new JButton(new ImageIcon("src/presentation/resources/images/Plantas/Targetas/Wall-nut.png"));
        button3.addActionListener(e -> selectPlant("WallNut"));
        setInvisibleButton(button3);
        plants.add(button3);
        JButton button4 = new JButton(new ImageIcon("src/presentation/resources/images/Plantas/Targetas/PotatoMine.png"));
        button4.addActionListener(e -> selectPlant("PotatoMine"));
        setInvisibleButton(button4);
        plants.add(button4);
        JButton button5 = new JButton(new ImageIcon("src/presentation/resources/images/Plantas/Targetas/ECIPlant.png"));
        button5.addActionListener(e -> selectPlant("ECIPlant"));
        setInvisibleButton(button5);
        plants.add(button5);
        JButton button6 = new JButton(new ImageIcon("src/presentation/resources/images/Plantas/Targetas/Evolve.png"));
        button6.addActionListener(e -> selectPlant("Evolve"));
        setInvisibleButton(button6);
        plants.add(button6);

        add(plants, BorderLayout.NORTH);

        // Panel de puntos de sol y cerebros
        pointsPanel = new JPanel();
        pointsPanel.setLayout(new GridLayout(2, 1));

        sunPointsLabel = new JLabel("Sun Points: " + sunPoints);
        sunPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunPointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsPanel.add(sunPointsLabel);

        timeLabel = new JLabel("Tiempo restante: " + gameManager.getGameTime() + " min");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsPanel.add(timeLabel);

        add(pointsPanel, BorderLayout.EAST);

        startGameTimer();
    }


    /**
     * Starts the game timer to manage the countdown for the ongoing game session.
     *
     * The game timer executes a task at one-second intervals using the Swing Timer,
     * updating the remaining game time and handling end-of-game events as follows:
     *
     * - At each interval:
     *   - Decrements the game time by 1 if time remains.
     *   - Updates the game time label to reflect the remaining time in seconds.
     * - If the game time reaches 0:
     *   - Stops the timer and displays a "time's up" message to the user.
     *   - Checks for the winner of the game:
     *     - Displays a message for the victory of zombies if all plants have been eliminated.
     *     - Displays a message for the victory of plants if zombies fail to reach the destination.
     *
     * The method leverages the structure and logic provided by the GameManager and updates
     * the time tracking interface in real-time.
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
                    JOptionPane.showMessageDialog(GamePvsMO.this, "¡Se acabó el tiempo! El juego ha terminado. En construccion");
                    if (Board.winner()){
                        JOptionPane.showMessageDialog(GamePvsMO.this, "lAS PLANTAS YA HABIAN PERDIDO, GANARON LOS ZOMBIES");
                    }
                    else{
                        JOptionPane.showMessageDialog(GamePvsMO.this, "LOS ZOMBIES NO LLEGARON A LAS CASA, GANAN LAS PLANTAS");
                    }
                }
            }
        });
        gameTimer.start();
    }


    /**
     * Sets the visual properties of a given JButton to make it appear invisible.
     * This involves making the button non-opaque, removing its content area fill,
     * and hiding its border.
     *
     * @param button the JButton to be styled as invisible
     */
    private void setInvisibleButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    /**
     * Selects a plant for placement in the game by updating the currently selected plant.
     *
     * @param plant the name of the plant to be selected
     */
    private void selectPlant(String plant) {
        selectedPlant = plant;
    }

    /**
     * Attempts to place a plant at the specified position on the game board.
     * This method checks if a plant has been selected and whether the placement
     * is valid. If the conditions are met, the plant is placed on the board,
     * and the corresponding visual updates are applied to the associated button.
     *
     * @param row the row index on the game board where the plant is to be placed
     * @param col the column index on the game board where the plant is to be placed
     * @param cellButton the button representing the cell on the board where the plant will be placed
     */
    private void placePlant(int row, int col, JButton cellButton) {
        if (selectedPlant == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una planta primero.");
            return;
        }
        if (GameManagerPvsMO.getInstance().placePlant(board.getPlantByName(selectedPlant),row,col)) {
            String gifPath = getGifForPlant(selectedPlant);
            cellButton.setIcon(new ImageIcon(gifPath));
            cellButton.setDisabledIcon(cellButton.getIcon());
            cellButton.setEnabled(false);
            selectedPlant = null;
        }
    }


    /**
     * Retrieves the file path of the GIF associated with the given plant.
     *
     * This method maps specific plant names to their respective GIF file paths.
     * If the provided plant name does not match any of the predefined cases,
     * an empty string is returned.
     *
     * @param plant the name of the plant for which the GIF file path is required
     * @return the file path of the GIF associated with the specified plant;
     *         returns an empty string if the plant name is not recognized
     */
    private String getGifForPlant(String plant) {
        switch (plant) {
            case "Sunflower":
                return "src/presentation/resources/images/Plantas/gif/Sunflower.gif";
            case "Peashooter":
                return "src/presentation/resources/images/Plantas/gif/Peashooter.gif";
            case "WallNut":
                return "src/presentation/resources/images/Plantas/gif/Wall-nut.gif";
            case "PotatoMine":
                return "src/presentation/resources/images/Plantas/gif/PotatoMine.gif";
            case "ECIPlant":
                return "src/presentation/resources/images/Plantas/gif/ECIPlant.gif";
            case "Evolve":
                return "src/presentation/resources/images/Plantas/gif/Evolve.gif";
            default:
                return "";
        }
    }

    /**
     * Retrieves the file path of the GIF associated with the specified zombie type.
     *
     * This method evaluates the provided zombie name and returns the corresponding
     * file path for its associated GIF representation. If the zombie type is not
     * recognized, it returns an empty string.
     *
     * @param zombie the name of the zombie for which the GIF file path is required
     * @return the file path of the GIF associated with the specified zombie type;
     *         returns an empty string if the zombie name is not recognized
     */
    private String getGifForZombie(String zombie) {
        switch (zombie) {
            case "Basic":
                return "src/presentation/resources/images/Zombies/Basic.gif";
            case "Conehead":
                return "src/presentation/resources/images/Zombies/Conehead.gif";
            case "Buckethead":
                return "src/presentation/resources/images/Zombies/Buckethead.gif";
            case "Brainstein":
                return "src/presentation/resources/images/Zombies/Brainstein.gif";
            case "ECIZombie":
                return "src/presentation/resources/images/Zombies/ECIZombie.png";
            default:
                return "";
        }
    }

    /**
     * Initializes and configures the elements of the application's menu.
     * This method creates a menu bar and attaches it to the frame.
     * It then creates a menu named "Archivo" and populates it with
     * several menu items, separators, and appropriate labels for each menu item.
     *
     * The menu items include:
     * - Nuevo
     * - Abrir
     * - Guardar como
     * - Importar
     * - Exportar como
     * - Salir
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

    /**
     * Prepares and sets up the actions menu by assigning action listeners to
     * each menu item. Each menu item is associated with a specific method
     * that defines its respective functionality.
     *
     * The following actions are associated with the menu items:
     * - Item1 triggers the method `optionNew()`.
     * - Item2 triggers the method `optionOpen()`.
     * - Item3 triggers the method `optionSave()`.
     * - Item4 triggers the method `optionImport()`.
     * - Item5 triggers the method `optionExport()`.
     */
    public void prepareActionsMenu() {
        Item1.addActionListener(e -> optionNew());
        Item2.addActionListener(e -> optionOpen());
        Item3.addActionListener(e -> optionSave());
        Item4.addActionListener(e -> optionImport());
        Item5.addActionListener(e -> optionExport());
    }

    /**
     * Initializes a new game session with predefined parameters.
     * Configures the game manager using player vs. machine opponent mode,
     * setting the initial sun points, brain points, and current game time.
     * Displays a dialog box to inform the user that a new game has been created.
     */
    public void optionNew() {
        gameManager = new GameManagerPvsMO(sunPoints, brainPoints, gameManager.getGameTime());
        JOptionPane.showMessageDialog(this, "Nuevo");
    }

    /**
     * Opens a file chooser dialog allowing the user to select a file and performs an action based on the selection.
     *
     * This method utilizes a JFileChooser with a selection mode constrained to files only. Upon user selection, it attempts to
     * process the selected file and displays a success or error message based on the outcome. If the user cancels the file
     * selection, no action is taken.
     *
     * Exceptions that occur during the file handling process, such as POOBvsZOMBIESException, are caught and displayed to
     * the user in the form of an error message dialog.
     */
    public void optionOpen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
//                gameManager = GameManagerPvsMO.open(selectedFile);
                JOptionPane.showMessageDialog(this, "Archivo cargado correctamente.");
            } catch (POOBvsZOMBIESException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opens a file chooser dialog to save a file with a ".dat" extension.
     * The method allows the user to select a file location and name.
     * If the selected file does not have a ".dat" extension, it appends the extension to the file name.
     * Displays a message dialog indicating success or an error message if an exception occurs.
     *
     * Behavior:
     * - A file chooser dialog appears, allowing the user to select a location to save the file.
     * - If the user approves the selection and the file does not have a ".dat" extension, the extension is added automatically.
     * - Displays a success message if the file is saved successfully.
     * - Handles exceptions by displaying the error message in a dialog box.
     */
    public void optionSave() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (!selectedFile.getName().endsWith(".dat")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".dat");
            }
            try {
//                gameManager.save(selectedFile);
                JOptionPane.showMessageDialog(this, "Archivo guardado correctamente.");
            } catch (POOBvsZOMBIESException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opens a file chooser dialog to allow the user to import a file.
     * The method restricts the selection to files only and, upon user approval,
     * retrieves the selected file. If an error occurs during the import process,
     * an error message is displayed in a dialog box.
     *
     * The method utilizes JFileChooser for file selection and JOptionPane
     * for displaying error messages.
     */
    public void optionImport() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
//                GameManager.aimport(selectedFile);
            } catch (POOBvsZOMBIESException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Handles the export functionality by allowing the user to select a file for saving.
     * Opens a file chooser dialog to select a file destination for exporting data.
     * If the user approves the selection, it attempts to export to the chosen file.
     * Displays an error message dialog if an exception occurs during the export process.
     */
    public void optionExport() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
//                GameManager.export(selectedFile);
            } catch (POOBvsZOMBIESException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}
