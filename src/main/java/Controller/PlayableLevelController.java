package Controller;

import Model.Classes.Level.PlayableLevel;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;

public class PlayableLevelController extends LevelController {

    public PlayableLevelController(JFrame frame, PlayableLevelPanel levelPanel, PlayableLevel level) {
        super(frame, levelPanel, level);
    }

    @Override
    public boolean isSandbox() {
        return false;
    }

    public boolean levelComplete() {
        // TODO: Implement this method
        return false;
    }
}
