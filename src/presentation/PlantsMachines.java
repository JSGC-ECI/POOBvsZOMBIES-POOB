package presentation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * PlantsMachines is a GUI application class that extends JFrame.
 * It provides a user interface for selecting between different plant-related strategies
 * and managing background music during navigation within the application.
 * The class handles window closing events with user confirmation and prepares
 * UI elements including buttons for plant strategy selection.
 */
public class PlantsMachines extends JFrame {

    /**
     * Constructs a new PlantsMachines frame.
     *
     * This constructor initializes the PlantsMachines window by setting its title
     * to "POOBvsZOMBIES (PlantsMachines)" and calling methods to prepare its elements
     * and associated actions. The prepareElements method sets up the user interface,
     * including the size, location, and initial components of the window. The
     * prepareActions method configures the window's behavior upon closing, prompting
     * the user for confirmation before exiting.
     */
    public PlantsMachines() {
        setTitle("POOBvsZOMBIES (PlantsMachines)");
        prepareElements();
        prepareActions();
    }

    /**
     * Configures the actions to be performed when the window is about to close.
     * This method sets the default close operation to do nothing on close,
     * thereby preventing the window from closing automatically. It adds a custom
     * WindowListener that overrides the windowClosing method to prompt the user
     * for confirmation before exiting the application.
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
     * Prompts the user with a confirmation dialog to confirm if they wish to close the application.
     * If the user confirms the action, the current window is disposed and the application is terminated.
     * The dialog presents "Yes" and "No" options for the user to choose from.
     * Utilizing this method ensures that users do not accidentally close the application without confirmation.
     */
    public void exit(){
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Prepares the user interface elements of the application window.
     *
     * This method sets the size of the window to half the width and height of the user's screen
     * and centers the window on the screen by setting its location relative to null. It also
     * initializes background music by playing a specified audio file in a continuous loop.
     * Furthermore, this method delegates additional UI component preparation to the
     * prepareElementsBoard method.
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        //AudioManager.playBackgroundMusic("presentation/resources/audio/zombiemachines.wav");
        prepareElementsBoard();
    }

    /**
     * Initializes and prepares the button panel for the application window.
     *
     * This method creates a JPanel with a GridLayout to host two buttons: "PlantsIntelligent"
     * and "PlantsStrategic," each associated with an ImageIcon (empty in the current implementation).
     * Both buttons share the same ActionListener, which stops the background music and disposes of the current window
     * when clicked. After initializing the buttons, it adds them to the panel, which is then added to the current JFrame.
     */
    public void prepareElementsBoard() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton button1 = new JButton("PlantsIntelligent", new ImageIcon(""));
        button1.addActionListener(e -> {
            AudioManager.stopBackgroundMusic();

            dispose();
        });
        buttonPanel.add(button1);
        JButton button2 = new JButton("PlantsStrategic",new ImageIcon(""));
        button2.addActionListener(e -> {
            AudioManager.stopBackgroundMusic();

            dispose();
        });
        buttonPanel.add(button2);
        add(buttonPanel);
    }

    public static void main(String[] args) {
        PlantsMachines select = new PlantsMachines();
        select.setVisible(true);
    }
}
