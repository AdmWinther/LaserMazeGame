package Vue.SoundEffects;

import Model.constants.SoundPaths;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

    private static Clip loadFile(URL file){
        try {
            assert file != null;
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Clip laserShoot(){
        try {
            URL file  = new URL("file:./src/main/java/Vue/Resources/Sounds/laserShoot.wav");
            return loadFile(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clip levelCompleted(){
        try {
            URL file  = new URL("file:./src/main/java/Vue/Resources/Sounds/levelCompleted.wav");
            return loadFile(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clip click(){
        try {
            URL file  = new URL("file:./src/main/java/Vue/Resources/Sounds/click.wav");
            return loadFile(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clip mainMenuClip(){
        try {
            URL file  = new URL("file:./src/main/java/Vue/Resources/Sounds/MainMenu.wav");
            return loadFile(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clip playCampaignButtonSound(Clip clip){
        if(clip != null) clip=null;
        try {
            URL file  = new URL(SoundPaths.CAMPAING_BUTTON);
            clip = loadFile(file);
            clip.start();
            return clip;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clip getCampaignButtonClickSound() {
        try {
            URL file  = new URL(SoundPaths.CAMPAING_BUTTON);
            return loadFile(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
