package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.*;

/**
 *
 *
 * @author Juan Sebastián Guayazán Clavijo
 * @version 1
 */
public class Select extends JFrame {

    private Clip backgroundMusic;
    private JPanel select;

    /**
     * Constructor for objects of class Select
     */
    public Select() {
        setTitle("POOBvsZOMBIES (Select)");
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
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton button1 = new JButton("Player vs Machine", new ImageIcon("presentation/resources/images/Nueva carpeta/DALL·E 2024-11-23 20.17.18 - Create a game icon for 'Player vs Machine' mode. The design should feature a split-screen concept_ one side shows a green plant symbolizing the player (convert.io).png"));
        buttonPanel.add(button1);
        JButton button2 = new JButton("Machine vs Machine",new ImageIcon("presentation/resources/images/Nueva carpeta/PvsM (convert.io).png"));
        buttonPanel.add(button2);
        JButton button3 = new JButton("Player vs Player", new ImageIcon("presentation/resources/images/Nueva carpeta/PvsM.png"));
        buttonPanel.add(button3);
        add(buttonPanel);
    }

    public static void main(String[] args) {
        Select select = new Select();
        select.setVisible(true);
    }

}
