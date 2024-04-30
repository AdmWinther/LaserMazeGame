package Vue.Handlers;

import Controller.LevelController;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that handles the keyboard events on the token panel
 *
 * @author Nathan Gromb
 */
public final class TokenKeyboardHandler implements KeyListener {
    private final LevelController levelController;

    /**
     * Constructor of the TokenKeyboardHandler
     *
     * @param levelController the controller of the level
     * @author Nathan Gromb
     */
    public TokenKeyboardHandler(LevelController levelController) {
        this.levelController = levelController;
    }

    /**
     * Method that handles the key typed event
     *
     * @param e the event to be processed
     * @author Nathan Gromb
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Method that handles the key pressed event
     * Triggers the laser shoot sound when the space key is pressed
     *
     * @param e the event to be processed
     * @author Nathan Gromb
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (levelController.getLaserGunCoordinate() != null) {
                SoundPlayer.play(SoundPaths.LASER_EFFECT_PASSES_PATH);
            }
            levelController.setShouldDisplayLaser(true);
        }
    }

    /**
     * Method that handles the key released event
     *
     * @param e the event to be processed
     * @author Nathan Gromb
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
