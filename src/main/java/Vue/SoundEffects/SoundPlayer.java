package Vue.SoundEffects;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * This class is used to play sound effects and music in the game
 *
 * @author Amin
 */
public class SoundPlayer {

    public static void play(String path) {
        play(path, false);
    }

    /**
     * This function is used to play a sound effect or music
     *
     * @param path the path of the sound effect or music
     * @param loop whether the sound effect or music should loop
     * @author Amin
     */
    public static void play(String path, boolean loop) {
        try {
            URL file = new URL(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // This will cause the music/sound-effect to loop indefinitely
            } else {
                clip.start(); // This function will play the music/sound-effect once
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}

