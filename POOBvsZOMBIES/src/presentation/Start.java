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
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Start extends JFrame {

    private Clip backgroundMusic;

    /**
     * Constructor for objects of class Start
     */
    public Start() {
        setTitle("POOBvsZOMBIES (Start)");
        prepareElements();
        prepareActions();
    }

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
        JLabel backgroundLabel = new JLabel(new ImageIcon("presentation/resources/images/Pantallas/pvzb.jpeg"));
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel);
        playBackgroundMusic("presentation/resources/audio/Main.wav");
    }

    private void playBackgroundMusic(String musicPath) {
        try {
            File musicFile = new File(musicPath);
            if (musicFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioStream);
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Repetir música
            } else {
                System.out.println("Archivo de música no encontrado: " + musicPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

