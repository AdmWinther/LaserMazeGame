package Vue.Handlers;

import Controller.LevelController;
import Controller.PlayableLevelController;
import Vue.SoundEffects.Sound;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TokenKeyboardHandler implements KeyListener {
    private final LevelController levelController;

    public TokenKeyboardHandler(LevelController levelController) {
        this.levelController = levelController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (levelController.getLaserGunCoordinate() != null) {
                Sound.playLaserShoot();
                if(((PlayableLevelController) (this.levelController)).isLevelCompleted() ){
                    Sound.playLevelCompleted();
                }
            }
            levelController.setShouldDisplayLaser(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}