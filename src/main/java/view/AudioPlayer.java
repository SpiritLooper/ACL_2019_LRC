package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {

    /**
     * path to the soundtrack wav file
     */
    private static final String SOUNDTRACK_FILE = "res/sounds/soundtrack.wav";

    /**
     * path to the hit sound file
     */
    private static final String HIT_SOUND_FILE = "res/sounds/hit.wav";

    /**
     * path to the heal sound file
     */
    private static final String HEAL_SOUND_FILE = "res/sounds/heal.wav";

    /**
     * Continuously plays the soundtrack of the game
     */
    public static synchronized void playSoundtrack() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = javax.sound.sampled.AudioSystem.getClip();
                    AudioInputStream inputStream = javax.sound.sampled.AudioSystem.getAudioInputStream(new File(SOUNDTRACK_FILE));
                    clip.open(inputStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    /**
     * Plays a given sound
     * @param sound sound to play
     */
    private static synchronized void playSound (final String sound) {
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

    /**
     * Plays a hit sound effect
     */
    public static synchronized void playHitSound () {
        playSound(HIT_SOUND_FILE);
    }

    /**
     * Plays a heal sound effect
     */
    public static synchronized void playHealSound () {
        playSound(HEAL_SOUND_FILE);
    }

}
