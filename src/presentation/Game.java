package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;


/**
 * Game crepresents the graphical user interface for the Player vs Player game.
 */
public class Game extends JFrame {

    private JPanel boardG;
    private JPanel game;
    private JPanel plants;
    private JPanel zombies;
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
    private String selectedElement = "";
    private boolean isPlant;

    /**
     * Constructs a new Game instance.
     * Initializes the game manager, sets the game instance to the game manager,
     * and configures initial game parameters such as sun points, brain points, and game time.
     * Sets the title for the game, prepares the game elements, sets actions for user interaction,
     * and initializes the game board.
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
     * Updates the label that displays the current amount of sun points.
     * The update is performed on the Event Dispatch Thread to ensure
     * thread safety when interacting with the Swing component.
     *
     * @param sunPoints the new value of sun points to be displayed on the label
     */
    public void updateSunPointsLabel(int sunPoints) {
        SwingUtilities.invokeLater(() -> sunPointsLabel.setText("Sun Points: " + sunPoints));
    }

    /**
     * Updates the label that displays the current amount of brain points.
     * The update is performed on the Event Dispatch Thread to ensure
     * thread safety when interacting with the Swing component.
     *
     * @param brainPoints the new value of brain points to be displayed on the label
     */
    public void updateBrainPointsLabel(int brainPoints) {
        SwingUtilities.invokeLater(() -> brainPointsLabel.setText("Brain Points: " + brainPoints));
    }


    /**
     * Prepares the initial elements and settings for the game.
     *
     * This method initializes the game window size and position based on the screen size,
     * collects initial values for key parameters such as sun points, brain points, and game time,
     * and sets up the board and menu components. The method ensures that the game starts
     * with the necessary configurations and elements in place.
     *
     * Key tasks performed:
     * - Sets the window size to half of the screen dimensions and positions it in the center.
     * - Prompts the user for initial values such as starting*/
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        promptForInitialValues();
        AudioManager.playBackgroundMusic("presentation/resources/audio/game.wav");
        prepareElementsBoard();
        prepareElementsMenu();
    }

    /**
     * Configures the actions and event listeners for the game window.
     *
     * This method performs the following tasks:
     * 1. Sets the default close operation to prevent the window from closing directly.
     * 2. Adds a custom window listener to handle the window closing event.
     *    The `exit` method is invoked when the user attempts to close the window,
     *    ensuring proper confirmation before exiting the game.
     * 3. Calls the `prepareActionsMenu` method to attach action listeners to menu items
     *    for handling menu-related actions.
     */
    public void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        prepareActionsMenu();
    }


    /**
     * Handles the process of exiting the game application.
     *
     * This method displays a confirmation dialog to the user, asking whether they
     * really want to close the game. If the user confirms (selects "Yes"), the
     * application performs the following:
     * 1. Closes the current game window by disposing of its resources.
     * 2. Exits the application by terminating the Java Virtual Machine.
     *
     * If the user declines (selects "No"), the application remains open and
     * no actions are taken.
     */
    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }


    /**
     * Prompts the user to enter initial values for key game parameters including
     * sun points, brain points, and game time. The user is prompted via input dialogs.
     *
     * If the entered values are valid integers, the method initializes a new GameManager
     * instance with the provided values. The sun and brain points labels are also updated
     * to reflect the newly entered values.
     *
     * If the user provides invalid input (non-integer values), an error message is displayed,
     * requesting valid input.
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
     * Initializes and sets up the elements board for the game interface. This method configures
     * the entire game layout and its components, including panels, buttons, and labels, for
     * interacting with plants, zombies, and game status information.
     *
     * The elements added include:
     * 1. A side panel with a "Shovel" button for selecting the shovel tool.
     * 2. A background panel with a backyard image that acts as the central playing board:
     *    - The board contains a grid layout (5x10) of buttons representing tiles.
     * 3. A top panel with plant cards to let players choose plant types for deployment:
     *    - Includes Sunflower, Peashooter, Wall-nut, Potato Mine, and ECIPlant cards.
     * 4. A bottom panel with zombie cards to allow players to choose zombie types:
     *    - Includes Basic, Conehead, Buckethead, Brainstein, and ECIZombie cards.
     * 5. A right-side panel for game information, including:
     *    - Sun points and brain points labels tracking resources.
     *    - A timer label displaying the remaining game time.
     *
     * The method ensures proper layout management and attaches action listeners to buttons
     * for handling user interaction during the game. It also starts the game timer after fully
     * initializing the game interface.
     */
    public void prepareElementsBoard() {
        game = new JPanel();
        game.setLayout(new BorderLayout());
        JButton shovelButton = new JButton("Shovel");
        shovelButton.addActionListener(e -> selectElement("Shovel", true));
        add(shovelButton, BorderLayout.WEST);

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
            tile.addActionListener(e -> placeElement(row, col, tile));
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
        button1.addActionListener(e -> selectElement("Sunflower", true));
        setInvisibleButton(button1);
        plants.add(button1);
        JButton button2 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Peashooter.png"));
        button2.addActionListener(e -> selectElement("Peashooter",true));
        setInvisibleButton(button2);
        plants.add(button2);
        JButton button3 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Wall-nut.png"));
        button3.addActionListener(e -> selectElement("WallNut", true));
        setInvisibleButton(button3);
        plants.add(button3);
        JButton button4 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/PotatoMine.png"));
        button4.addActionListener(e -> selectElement("PotatoMine", true));
        setInvisibleButton(button4);
        plants.add(button4);
        JButton button5 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/ECIPlant.png"));
        button5.addActionListener(e -> selectElement("ECIPlant", true));
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
        buttonBasic.addActionListener(e -> selectElement("Basic", false));
        setInvisibleButton(buttonBasic);
        zombies.add(buttonBasic);
        JButton buttonConehead = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/Conehead.png"));
        buttonConehead.addActionListener(e -> selectElement("Conehead", false));
        setInvisibleButton(buttonConehead);
        zombies.add(buttonConehead);
        JButton buttonBuckethead = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/Buckethead.png"));
        buttonBuckethead.addActionListener(e -> selectElement("Buckethead", false));
        setInvisibleButton(buttonBuckethead);
        zombies.add(buttonBuckethead);
        JButton buttonBrainstein = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/Brainstein.png"));
        buttonBrainstein.addActionListener(e -> selectElement("Brainstein", false));
        setInvisibleButton(buttonBrainstein);
        zombies.add(buttonBrainstein);
        JButton buttonECIZombie = new JButton(new ImageIcon("presentation/resources/images/Zombies/Targetas/ECIZombie.png"));
        buttonECIZombie.addActionListener(e -> selectElement("ECIZombie", false));
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

        timeLabel = new JLabel("Tiempo restante: " + gameManager.getGameTime() + " seg");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsPanel.add(timeLabel);

        add(pointsPanel, BorderLayout.EAST);

        startGameTimer();
    }



    /**
     * Starts and manages the game timer that tracks the countdown of the game's remaining time.
     *
     * This method initializes a Timer instance to decrement the game time every second, update the
     * displayed time label, and handle actions when the timer reaches zero. The timer operates with
     * one-second intervals and is stopped when the remaining time reaches zero.
     *
     * Key functionalities:
     * - Retrieves the current game time from `gameManager`.
     * - Updates the time label to reflect the remaining time in seconds.
     * - Stops the timer and displays a message dialog when the time reaches zero.
     *
     * The timer updates the game state and UI components related to game time, ensuring proper synchronization
     * between the game manager and the display components.
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
     * Configures the given JButton to be invisible by setting its appearance properties.
     * The button becomes transparent by making it non-opaque, removing its content area fill,
     * and disabling its border painting.
     *
     * @param button the JButton to be made invisible
     */
    private void setInvisibleButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    /**
     * Selects an element for use in the game and specifies whether it is a plant or not.
     *
     * @param element the name of the element to be selected
     * @param isPlant a boolean indicating whether the selected element is a plant (true) or not (false)
     */
    public void selectElement(String element, boolean isPlant) {
        this.selectedElement = element;
        this.isPlant = isPlant;
        System.out.println("Elemento seleccionado: " + selectedElement + " (Es planta: " + isPlant + ")");
    }


    /**
     * Places an element on the game board at the specified row and column.
     * This method handles the placement of plants, zombies, or the removal of plants
     * using a shovel, depending on the currently selected element.
     *
     * The method validates the cell's state before placing an element:
     * - Checks if a plant or zombie is selected.
     * - Ensures the cell is unoccupied before placing an element.
     * - Places the selected element and updates the cell's appearance accordingly.
     *
     * @param row the row index of the cell where the element is to be placed
     * @param col the column index of the cell where the element is to be placed
     * @param cellButton the button representing the grid cell on the game board
     */
    public void placeElement(int row, int col, JButton cellButton) {
        if (selectedElement == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una planta o un zombi primero.");
            return;
        }
        if (cellButton.getIcon() != null) {
            JOptionPane.showMessageDialog(this, "Esta celda ya está ocupada.");
            return;
        }

        if (isPlant) {
            placePlant(row, col, cellButton);
        }
        else if("Shovel".equals(selectedElement)) {
            shovel(row, col, cellButton);
        }
        else{
            placeZombie(row, col, cellButton);
        }
        selectedElement = null;
    }

    /**
     * Removes a plant from the specified cell on the game board if there is one present.
     * Displays appropriate messages based on whether the cell contains a plant
     * and if the removal operation was successful.
     *
     * @param row the row index of the cell from which the plant is to be removed
     * @param col the column index of the cell from which the plant is to be removed
     * @param cellButton the JButton representing the grid cell on the game board
     */
    private void shovel(int row, int col, JButton cellButton) {
        if (cellButton.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "No hay ninguna planta para eliminar en esta celda.");
            return;
        }
        boolean wasPlantRemoved = GameManager.getInstance().removePlant(row, col);
        if (wasPlantRemoved) {
            cellButton.setIcon(null);
            cellButton.setDisabledIcon(null);
            cellButton.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Planta eliminada con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "No se puede usar la pala en esta celda.");
        }
    }



    /**
     * Places a plant on the game board at the specified row and column.
     * Updates the visual representation of the cell and disables it if the placement is successful.
     *
     * @param row       the row index of the cell where the plant is to be placed
     * @param col       the column index of the cell where the plant is to be placed
     * @param cellButton the JButton representing the grid cell on the game board
     */
    private void placePlant(int row, int col, JButton cellButton) {
        if (GameManager.getInstance().placePlant(board.getPlantByName(selectedElement),row,col)) {
            String gifPath = getGifForPlant(selectedElement);
            cellButton.setIcon(new ImageIcon(gifPath));
            cellButton.setDisabledIcon(cellButton.getIcon());
            cellButton.setEnabled(false);
        }
    }


    /**
     * Retrieves the file path to the GIF associated with the given plant name.
     *
     * @param plant the name of the plant for which the GIF file path is required
     * @return the file path to the GIF corresponding to the specified plant name,
     *         or an empty string if the plant name does not match any known plant
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
     * Places a zombie on the game board at the specified row and column.
     * The zombie can only be placed in the last column. If placement is successful,
     * updates the button's icon to represent the zombie.
     *
     * @param row the row position where the zombie will be placed
     * @param col the column position where the zombie will be placed, must be 9
     * @param cellButton the JButton representing the cell where the zombie will be placed
     */
    public void placeZombie(int row, int col, JButton cellButton) {
        if (col != 9) {
            JOptionPane.showMessageDialog(this, "Los zombis solo pueden ser colocados en la última columna.");
            return;
        }
        if (GameManager.getInstance().placeZombie(board.getZombieByName(selectedElement), row, col)) {
            String gifPath = getGifForZombie(selectedElement);
            cellButton.setIcon(new ImageIcon(gifPath));
            cellButton.setDisabledIcon(cellButton.getIcon());
            cellButton.setEnabled(false);
        }
    }


    /**
     * Determines the file path or identifier for the GIF associated with a specific type of zombie.
     *
     * @param zombie the type of zombie for which the GIF or identifier is required. Examples include "Basic", "Conehead", "Buckethead", "Brainstein", and "ECIZombie".
     * @return the file path or identifier for the associated zombie GIF.
     *         Returns an empty string if the given zombie type is not recognized.
     */
    private String getGifForZombie(String zombie) {
        switch (zombie) {
            case "Basic":
                return "plantado";
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
     * Initializes and prepares the elements of the menu bar for the application.
     *
     * This method sets up a menu bar and its menu items such as "Nuevo", "Abrir",
     * "Guardar como", "Importar", "Exportar como", and "Salir". It also organizes
     * these menu items with separators for better visual representation. The menu
     * is labeled as "Archivo", and it is added to the menu bar, which is then set
     * to the application window.
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
     * Prepares the actions menu by adding action listeners to menu items.
     * Each menu item is associated with a corresponding method that handles
     * the specific action when the item is selected.
     *
     * The method links the following actions to their respective menu items:
     * - Item1: Triggers the optionNew() method.
     * - Item2: Triggers the optionOpen() method.
     * - Item3: Triggers the optionSave() method.
     * - Item4: Triggers the optionImport() method.
     * - Item5: Triggers the optionExport() method.
     */
    public void prepareActionsMenu() {
        Item1.addActionListener(e -> optionNew());
        Item2.addActionListener(e -> optionOpen());
        Item3.addActionListener(e -> optionSave());
        Item4.addActionListener(e -> optionImport());
        Item5.addActionListener(e -> optionExport());
    }

    /**
     * Resets the game settings and displays a dialog indicating a new game has started.
     * This method reinitializes the game by creating a new instance of GameManager with the current
     * values of sun points, brain points, and game time. After reinitialization, it shows a message
     * dialog to notify the user about the new game setup.
     */
    public void optionNew() {
        gameManager = new GameManager(sunPoints, brainPoints, gameManager.getGameTime() );
        JOptionPane.showMessageDialog(this, "Nuevo");
    }

    /**
     * Opens a file selection dialog for the user to load a file.
     * This method allows the user to select a file, which will then be processed
     * and used to initialize a GameManager instance if the file is valid.
     * If the selected file is valid and successfully loaded, a confirmation message
     * is displayed. If an error occurs while attempting to load the file, an error
     * message is shown.
     *
     * The method uses a JFileChooser to select the file and handles both
     * successful file selection and potential exceptions during the loading process.
     *
     * Preconditions:
     * - The method assumes that there is a valid file structure acceptable by the GameManager.
     *
     * Postconditions:
     * - On successful file selection and loading, the gameManager is initialized with the selected file.
     * - A message dialog is displayed to inform the user of success or failure.
     *
     * Dialogs:
     * - JFileChooser is used to allow users to select files from the file system.
     * - JOptionPane is used to display success or error messages.
     *
     * Exceptions:
     * - POOBvsZOMBIESException may be thrown during the loading process if the file is invalid or cannot be processed.
     */
    public void optionOpen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                gameManager = GameManager.open(selectedFile);
                JOptionPane.showMessageDialog(this, "Archivo cargado correctamente.");
            } catch (POOBvsZOMBIESException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Handles the functionality for saving a game file.
     * Opens a file chooser dialog to allow the user to specify the location and name of the file to save.
     * Ensures the file has a ".dat" extension if not explicitly specified by the user.
     * Attempts to save the file using the gameManager's save method.
     * Displays a success message if the file is saved successfully or an error message if an exception occurs.
     *
     * Behavior:
     * - Opens a JFileChooser dialog in "save" mode and restricts selection to files only.
     * - Appends ".dat" extension to the selected file if not already present.
     * - Interacts with the gameManager for saving the file.
     * - Uses JOptionPane to display success or error messages based on the save operation result.
     *
     * Exceptions:
     * - Displays an error dialog if a POOBvsZOMBIESException occurs during the save operation.
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
                gameManager.save(selectedFile);
                JOptionPane.showMessageDialog(this, "Archivo guardado correctamente.");
            } catch (POOBvsZOMBIESException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Prompts the user with a file selection dialog to import a file.
     * The method allows the user to select a file from the file system and attempts to
     * import its contents using the GameManager's import method.
     * If an error occurs during the import process, an error message dialog is displayed
     * to the user with the corresponding error message.
     *
     * Uses a `JFileChooser` to let the user navigate and select a file.
     * Handles possible exceptions that might occur during the import operation.
     */
    public void optionImport() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                GameManager.aimport(selectedFile);
            } catch (POOBvsZOMBIESException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opens a file chooser dialog to allow the user to select a file for exporting game data.
     * The method utilizes a {@link JFileChooser} for file selection and invokes the export functionality
     * of {@link GameManager} to save the game data to the selected file. If an error occurs during
     * the export process, an error message is displayed.
     *
     * Behavior:
     * - The file chooser restricts selection to files only.
     * - If the user approves the selection, the game data is exported into the chosen file.
     * - Displays an error dialog if an exception occurs during the export process.
     *
     * Exceptions:
     * Handles {@link POOBvsZOMBIESException} by showing an error message to the user.
     */
    public void optionExport() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                GameManager.export(selectedFile);
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
