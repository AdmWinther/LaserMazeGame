package Classes.Game;

import Resources.constants.FilePaths;

public class GameData {
    private static final String LEVEL_IDS_PATH = FilePaths.LEVELS_IDS_DATA_PATH;

    public static String getLevelIdsPath() {
        return LEVEL_IDS_PATH;
    }
}
