package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public Game() {
        gameManager = GameManager.getInstance();
        gameManager.setGame(this);
        gameManager = new GameManager(sunPoints, brainPoints, gameManager.getGameTime() );
        setTitle("POOBvsZOMBIES (Game)");
        prepareElements();
        prepareActions();
        board = new Board(sunPoints, brainPoints);
    }

    public void updateSunPointsLabel(int sunPoints) {
        SwingUtilities.invokeLater(() -> sunPointsLabel.setText("Sun Points: " + sunPoints));
    }


    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        promptForInitialValues();
        //AudioManager.playBackgroundMusic("presentation/resources/audio/game.wav");
        prepareElementsBoard();
        prepareElementsMenu();
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
    }

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
        JButton button6 = new JButton(new ImageIcon("presentation/resources/images/Plantas/Targetas/Evolve.png"));
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
                    if (Board.winner()){
                        JOptionPane.showMessageDialog(Game.this, "lAS PLANTAS YA HABIAN PERDIDO, GANARON LOS ZOMBIES");
                    }
                    else{
                        JOptionPane.showMessageDialog(Game.this, "LOS ZOMBIES NO LLEGARON A LAS CASA, GANAN LAS PLANTAS");
                    }
                }
            }
        });
        gameTimer.start();
    }


    private void setInvisibleButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    private void selectPlant(String plant) {
        selectedPlant = plant;
    }

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
            case "Evolve":
                return "presentation/resources/images/Plantas/gif/Evolve.gif";
            default:
                return "";
        }
    }

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
