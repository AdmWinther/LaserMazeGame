package Controller;

import Model.Classes.Level.PlayableLevel;

public class PlayableLevelController extends LevelController {

    public PlayableLevelController(PlayableLevel level) {
        super(level);
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
