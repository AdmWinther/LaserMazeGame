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
    public static void playLaserShoot(){
        try {
            URL file  = new URL(SoundPaths.LASER_EFFECT_PASSES_PATH);
            loadFile(file).start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clip getLevelCompleted(){
        try {
            URL file  = new URL(SoundPaths.LEVEL_PASSED_SOUND_PATH);
            return loadFile(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playMainMenuTheme(){
        try {
            URL file  = new URL(SoundPaths.MAIN_MENU_MUSIC2_PATH);
            loadFile(file).start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playButtonSound(Clip clip){
        if(clip != null) clip=null;
        try {
            URL file  = new URL(SoundPaths.CAMPAIGN_BUTTON);
            clip = loadFile(file);
            clip.start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playLevelCompleted() {
        try {
            URL file  = new URL(SoundPaths.LEVEL_PASSED_SOUND_PATH);
            loadFile(file).start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}