package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static presentation.AudioManager.playBackgroundMusic;

/**
 * The Select class represents a graphical user interface for selecting
 * game modes in the POOBvsZOMBIES application. It extends JFrame to create
 * a window with options for different game modes including Player vs Machine,
 * Machine vs Machine, and Player vs Player. The window prompts users for input
 * when certain modes are selected and initializes the corresponding game environment.
 */
public class Select extends JFrame {
    private JTextField playerNameField;
    private JTextField player2NameField;
    private String selectedMode = "";

    /**
     * Initializes a new instance of the Select class, setting up the user interface components
     * and their associated actions for the "POOBvsZOMBIES (Select)" window. This constructor
     * sets the title of the window, prepares the graphical elements, and defines the actions
     * that the window should respond to.
     */
    public Select() {
        setTitle("POOBvsZOMBIES (Select)");
        prepareElements();
        prepareActions();
    }


    /**
     * Configures actions for the window, specifically setting the default close operation
     * and adding a window listener to handle window closing events. Upon attempting to close
     * the window, the user is prompted with a confirmation dialog through the exit() method,
     * which handles closing actions and exits the application if the user confirms.
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
     * Displays a confirmation dialog to the user asking if they really wish to close the application.
     * If the user confirms by selecting the 'Yes' option, the method proceeds to dispose of the current
     * window and terminates the entire application by calling System.exit(0).
     * This method is typically used in scenarios where a window closing event occurs, allowing the application
     * to prompt the user for confirmation before exiting.
     */
    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Prepares the graphical elements for the application window, setting the size
     * to half the screen's width and height and centering the window on the screen.
     * This method initializes the user interface components essential for the selection
     * screen and delegates the detailed setup to the prepareElementsBoard() method.
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        playBackgroundMusic("src/presentation/resources/audio/selectmode.wav");
        prepareElementsBoard();
    }

    /**
     * Prepares the elements of the selection board by creating a panel with buttons
     * for different game modes. Each button is associated with an action listener
     * that sets the selected game mode and triggers appropriate behavior.
     *
     * The method configures the following game modes:
     * - "Player vs Machine": Displays an input dialog for entering the player's name.
     * - "Machine vs Machine": Proceeds to the machine selection interface.
     * - "Player vs Player": Displays an input dialog for entering the names of two players.
     *
     * This method creates a graphical user interface panel, arranges it using a
     * grid layout, and assigns corresponding actions to the buttons to handle
     * user interaction and navigation within the application.
     */
    public void prepareElementsBoard() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton button1 = new JButton(new ImageIcon("src/presentation//resources/images/Pantallas/Player vs Machine.png"));
        buttonPanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMode = "Player vs Machine";
                showPlayerNameInput();
            }
        });

        JButton button2 = new JButton(new ImageIcon("src/presentation//resources/images/Pantallas/Machine vs Machine.png"));
        buttonPanel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMode = "Machine vs Machine";
                proceedToMachinesSelection();
            }
        });

        JButton button3 = new JButton(new ImageIcon("src/presentation//resources/images/Pantallas/Player vs Player.png"));
        buttonPanel.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMode = "Player vs Player";
                showPlayerNamesInput();
            }
        });

        add(buttonPanel);
    }

    /**
     * Prompts the user to input a player's name using a dialog box.
     * If the user confirms the input with a valid, non-empty name,
     * it stops the background music, initializes a new instance of
     * ZombieMachines, displays it, and closes the current window.
     * If the input is empty or cancelled, it displays a warning message
     * urging the user to enter a valid name.
     */
    private void showPlayerNameInput() {
        playerNameField = new JTextField();
        int option = JOptionPane.showConfirmDialog(this, playerNameField, "Ingrese el nombre del jugador", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION && !playerNameField.getText().trim().isEmpty()) {
            AudioManager.stopBackgroundMusic();
            ZombieMachines zombieMachines = new ZombieMachines();
            zombieMachines.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido.");
        }
    }


    /**
     * Displays a dialog box to input names for two players. If valid names are
     * entered and the OK option is selected, the method stops any ongoing background
     * music and launches a new game instance. If the input is invalid or canceled,
     * a message prompts the user to provide valid names.
     *
     * This method manages the creation of the input panel, displaying labels
     * and text fields for both player names. When confirmed with valid inputs,
     * a new game window is opened and the current window is closed. The dialog
     * enforces a non-empty input requirement for both name fields to proceed.
     */
    private void showPlayerNamesInput() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        playerNameField = new JTextField();
        player2NameField = new JTextField();

        panel.add(new JLabel("Jugador 1:"));
        panel.add(playerNameField);
        panel.add(new JLabel("Jugador 2:"));
        panel.add(player2NameField);

        int option = JOptionPane.showConfirmDialog(this, panel, "Ingrese los nombres de los jugadores", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION && !playerNameField.getText().trim().isEmpty() && !player2NameField.getText().trim().isEmpty()) {
            AudioManager.stopBackgroundMusic();
            Game game = new Game();
            game.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese nombres válidos para ambos jugadores.");
        }
    }

    /**
     * Proceeds to the machine selection process by performing the following actions:
     *
     * - Stops the background music that may currently be playing.
     * - Instantiates a new ZombieMachines object to initiate the machine selection interface.
     * - Sets the ZombieMachines window visible so that the user can interact with the machine selection options.
     * - Disposes of the current window to transition fully to the new screen.
     *
     * This method is typically invoked when the user selects the option for a machine vs machine game mode,
     * allowing them to choose the type of machines for the upcoming session.
     */
    private void proceedToMachinesSelection() {
        AudioManager.stopBackgroundMusic();
        ZombiePlantSelector zombiePlantSelector = new ZombiePlantSelector();
        zombiePlantSelector.setVisible(true);
        dispose();
    }


    public static void main(String[] args) {
        Select select = new Select();
        select.setVisible(true);
    }
}
