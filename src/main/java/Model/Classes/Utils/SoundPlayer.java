package Model.Classes.Utils;
import Model.constants.SoundPaths;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
public class SoundPlayer {
    public static void main(String[] args) throws Exception {
        play(SoundPaths.LOADING_GAME_SOUND_PATH,true);
    }
    public static void play(String path, boolean loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
        System.out.println("Press 'Enter' to stop the audio.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();  // Waits for the user to press Enter

        clip.stop();  // Stop playing the sound
        clip.close(); // Close the audio clip and release the resources
        audioInputStream.close(); // Close the stream and release resources
        scanner.close();  // Close the scanner
    }
    public static void play(String path) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        play(path,false);
    }
}

