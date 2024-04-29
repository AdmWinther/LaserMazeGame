package Controller;

import Model.Classes.Level.PlayableLevel;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;

public class PlayableLevelController extends LevelController {

    public PlayableLevelController(GameController gameController, JFrame frame, PlayableLevelPanel levelPanel, PlayableLevel level) {
        super(gameController, frame, levelPanel, level);
    }

    @Override
    public boolean isSandbox() {
        return false;
    }

    public boolean isLevelCompleted() {
        return ((PlayableLevel) level).isLevelCompleted();
    }
}