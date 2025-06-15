package presentation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The AudioManager class provides static methods to control background music
 * playback within an application. It allows for playing a music file in a
 * continuous loop and stopping the playback when needed. This class manages
 * the audio using Java's Clip and AudioInputStream classes.
 */
public class AudioManager {
    private static Clip backgroundMusic;

    /**
     * Plays the background music specified by the given file path in a continuous loop.
     *
     * @param musicPath The path to the music file to be played as background music.
     *                  The file should exist at the specified location and be a valid audio format
     *                  supported by the system's audio input stream.
     */
    public static void playBackgroundMusic(String musicPath) {
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

    /**
     * Stops the playback of the background music if it is currently running.
     * This method checks if the background music clip is initialized and playing.
     * If so, it stops and releases the audio resources associated with it.
     */
    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }
}