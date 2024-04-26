package Model.Classes.Utils;
import Model.constants.SoundPaths;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
public class SoundPlayer {
    public static void main(String[] args) throws Exception {
        play(SoundPaths.LOADING_GAME_SOUND_PATH,true);
    }
    public static void play(String path, boolean loop) {
        try {
            File file = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            //test comment

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // This will cause the music/sound-effect to loop indefinitely
            } else {
                clip.start(); // This function will play the music/sound-effect once
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
    public static void play(String path) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        play(path,false);
    }
}

