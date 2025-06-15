package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * The ZombieMachines class represents the main window for the "POOBvsZOMBIES (PlantsMachines)" game.
 * It extends the JFrame class, offering a graphical interface for users to select between different game modes.
 * This class configures the main interface, handles actions such as window events, and provides options to start the game.
 */
public class ZombieMachines extends JFrame {

    /**
     * Constructs a new instance of the ZombieMachines class.
     *
     * This constructor initializes the main game window for the "POOBvsZOMBIES (PlantsMachines)" game.
     * It sets the title of the window, prepares the graphical elements of the user interface, and sets up
     * the corresponding actions, such as handling events for closing the window.
     *
     * The constructor ensures that the game window is ready for user interaction and provides options
     * for selecting different game modes.
     */
    public ZombieMachines() {
        setTitle("POOBvsZOMBIES (PlantsMachines)");
        prepareElements();
        prepareActions();
    }

    /**
     * Configures the main window to handle user actions properly when attempting to close it.
     *
     * This method sets the default close operation to do nothing, ensuring that the window
     * does not close automatically. Instead, it adds a custom WindowListener to intercept the
     * window-closing event. When the user attempts to close the window, the custom event handler
     * triggers the exit() method, which manages the shutdown process, including prompting the
     * user for confirmation before closing.
     */
    public void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }

    /**
     * Handles the closure of the application by prompting the user for confirmation
     * before shutting down, stopping background music playback, and releasing resources.
     *
     * This method displays a confirmation dialog asking the user if they really want
     * to close the application. If the user confirms, it stops any background music
     * currently playing using the AudioManager class, disposes of the window resources,
     * and terminates the program.
     *
     * The confirmation dialog provides "Yes" and "No" options to the user. Selecting "No"
     * will cancel the shutdown process and allow the application to continue running.
     */
    public void exit(){
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            AudioManager.stopBackgroundMusic();
            dispose();
            System.exit(0);
        }
    }


    /**
     * Initializes and configures the main window for the application.
     *
     * This method sets the window size to half the dimensions of the user's screen and centers it on the screen.
     * It also delegates the preparation of the board's elements to the `prepareElementsBoard` method,
     * which loads specific components, such as buttons or panels, into the GUI.
     *
     * The method ensures the graphical user interface is properly initialized and prepared for display.
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        //AudioManager.playBackgroundMusic("presentation/resources/audio/zombiemachines.wav");
        prepareElementsBoard();
    }


    /**
     * Prepares and sets up the user interface elements for the game mode selection board.
     *
     * This method creates a panel containing two buttons, each representing a different game mode,
     * and adds them to the main window. The buttons are styled with images and include
     * action listeners that handle the transition to the respective game mode screens:
     *
     * - The first button starts the "GamePvsMO" mode and stops the background music.
     * - The second button starts the "GamePvsMS" mode and stops the background music.
     *
     * When a button is clicked, the current window is disposed to free resources,
     * and the selected game mode window is launched.
     */
    public void prepareElementsBoard() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton button1 = new JButton( new ImageIcon("presentation/resources/images/Pantallas/ZombiesOriginal.jpg"));
        button1.addActionListener(e -> {AudioManager.stopBackgroundMusic();
            GamePvsMO game = new GamePvsMO();
            game.setVisible(true);
            dispose();
        });
        buttonPanel.add(button1);
        JButton button2 = new JButton(new ImageIcon("presentation/resources/images/Pantallas/ZombiesStrategic.jpg"));
        button2.addActionListener(e -> {AudioManager.stopBackgroundMusic();
            GamePvsMS game = new GamePvsMS();
            game.setVisible(true);
            dispose();
        });
        buttonPanel.add(button2);
        add(buttonPanel);
    }

    /**
     * The entry point of the application. This method initializes and displays the main
     * window for the "POOBvsZOMBIES (PlantsMachines)" game.
     *
     * @param args Command-line arguments passed to the application. These are not used
     *             by the method in the current implementation.
     */
    public static void main(String[] args) {
        ZombieMachines select = new ZombieMachines();
        select.setVisible(true);
    }
}
