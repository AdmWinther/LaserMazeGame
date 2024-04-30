package Vue.SoundEffects;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

    public static void playLaserShoot() {
        try {
            URL file = new URL(SoundPaths.LASER_EFFECT_PASSES_PATH);
            loadFile(file).start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Clip loadFile(URL file) {
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

    public static Clip getLevelCompleted() {
        try {
            URL file = new URL(SoundPaths.LEVEL_PASSED_SOUND_PATH);
            return loadFile(file);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playMainMenuTheme() {
        URL file = null;
        try {
            file = new URL(SoundPaths.MAIN_MENU_MUSIC_PATH);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Clip clip = loadFile(file);
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-30.0f); // Reduce volume by 30 decibels.
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public static void playButtonSound() {
        try {
            URL file = new URL(SoundPaths.CAMPAIGN_BUTTON);
            Clip clip = loadFile(file);
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