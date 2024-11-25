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
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class PlantsMachines extends JFrame {

    private Clip backgroundMusic;
    private JPanel select;

    /**
     * Constructor for objects of class PlantsMachines
     */
    public PlantsMachines() {
        setTitle("POOBvsZOMBIES (PlantsMachines)");
        prepareElements();
        prepareActions();
    }

    public void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }
    public void exit(){
        int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea cerrar?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        playBackgroundMusic("presentation/resources/audio/selectmode.wav");
        prepareElementsBoard();
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
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton button1 = new JButton("PlantsIntelligent", new ImageIcon(""));
        buttonPanel.add(button1);
        JButton button2 = new JButton("PlantsStrategic",new ImageIcon(""));
        buttonPanel.add(button2);
        add(buttonPanel);
    }

    public static void main(String[] args) {
        PlantsMachines select = new PlantsMachines();
        select.setVisible(true);
    }
}
