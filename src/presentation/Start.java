package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * The Start class represents the introductory window for the POOBvsZOMBIES application.
 * This class extends JFrame and handles the initial user interface,
 * including window preparation, actions, and transitioning to the game selection window.
 */
public class Start extends JFrame {

    private Clip backgroundMusic;


    /**
     * Constructs a new Start window for the POOBvsZOMBIES application.
     * This constructor initializes the main introductory screen by setting its title,
     * preparing the visual elements, and configuring user interface actions.
     * It serves as the entry point for the application's initial user interface.
     */
    public Start() {
        setTitle("POOBvsZOMBIES (Start)");
        prepareElements();
        prepareActions();
    }


    /**
     * Configures the actions and event listeners for the Start window.
     *
     * This method sets the default close operation to prevent the window from closing directly
     * and registers necessary event listeners for user interactions. The actions include:
     *
     * - Handling the window closing event by invoking the exit() method.
     * - Handling mouse click events by transitioning to the game selection window.
     *
     * The method ensures proper application behavior when closing the window or initiating
     * transitions to other screens.
     */
    public void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openGameWindow();
            }
        });
    }


    /**
     * Handles the process of exiting the application.
     *
     * This method prompts the user with a confirmation dialog when they attempt to close the application.
     * If the user confirms the closure by selecting "Yes", the method disposes of the current window
     * and terminates the application.
     *
     * The user must select "Yes" in the confirmation dialog to proceed with application termination.
     * If "No" is chosen, the application continues running without any changes.
     */
    public void exit(){
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }


    /**0
     * Prepares and sets up all the visual elements for the Start window.
     *
     * This method configures the initial dimensions and position of the window
     * relative to the user's screen size and centers it on the screen. It also
     * adds a background image to the window, providing the introductory visual
     * appearance for the application. The background image is set using a
     * JLabel, and a BorderLayout is applied to the label to potentially allow
     * additional components to be added in the future.
     *
     * Any necessary audio setup, such as background music, can be initiated
     * within this method (currently commented-out in the implementation).
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/presentation/resources/images/Pantallas/start.png"));
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel);
        AudioManager.playBackgroundMusic("src/presentation/resources/audio/Main.wav");
    }


    /**
     * Transitions from the current Start window to the game selection screen.
     *
     * This method performs the following actions:
     * - Stops and releases resources associated with the background music if it is currently playing.
     * - Creates an instance of the game selection window (`Select`)*/
    private void openGameWindow() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
        Select selectWindow = new Select();
        selectWindow.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Start start = new Start();
            start.setVisible(true);
        });
    }
}

