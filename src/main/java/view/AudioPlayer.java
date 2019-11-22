package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {

    private static final String soundtrack = "res/sounds/soundtrack.wav";

    public static synchronized void playSoundtrack() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = javax.sound.sampled.AudioSystem.getClip();
                    AudioInputStream inputStream = javax.sound.sampled.AudioSystem.getAudioInputStream(new File(soundtrack));
                    clip.open(inputStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static synchronized void playSound (final String sound) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = javax.sound.sampled.AudioSystem.getClip();
                    AudioInputStream inputStream = javax.sound.sampled.AudioSystem.getAudioInputStream(new File(sound));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
