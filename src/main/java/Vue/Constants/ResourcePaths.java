package Vue.Constants;

/**
 * This class is responsible for storing the paths of the graphic files used in the application
 *
 * @author Hugo Demule
 */
public class ResourcePaths {

    /**
     * Class that stores the paths of the textures
     *
     * @author Hugo Demule
     */
    public static final class Textures {

        public static final String BACKGROUND_TILE = "src/main/java/Vue/Resources/Tiles/background.png";
        public static final String SANDBOX_LIST_ITEM_BACKGROUND = "src/main/java/Vue/Resources/MenuButtons/level.png";
        public static final String SANDBOX_LIST_NEW_LEVEL_BACKGROUND = "src/main/java/Vue/Resources/MenuButtons/new_level.png";
    }

    /**
     * Class that stores the paths of the buttons
     *
     * @author Hugo Demule
     */
    public static final class Buttons {

        public static final String CAMPAIGN_MENU = "src/main/java/Vue/Resources/MenuButtons/campaign.png";
        public static final String SANDBOX_MENU = "src/main/java/Vue/Resources/MenuButtons/sandbox.png";
        public static final String RANDOM_LEVEL = "src/main/java/Vue/Resources/MenuButtons/random.png";
        public static final String CAMPAIGN_LEVEL = "src/main/java/Vue/Resources/Tiles/boardTile1.png";
        public static final String DISABLED_CAMPAIGN_LEVEL = "src/main/java/Vue/Resources/Tiles/disabled_boardTile.png";
    }

    /**
     * Class that stores the paths of the icons used in the application
     *
     * @author Hugo Demule
     */
    public static final class Icons {

        public static final String PLAY_BUTTON_ICON = "src/main/java/Vue/Resources/Objects/play.png";
        public static final String EDIT_BUTTON_ICON = "src/main/java/Vue/Resources/Objects/edit.png";
        public static final String DELETE_BUTTON_ICON = "src/main/java/Vue/Resources/Objects/bin.png";
        public static final String RESET_BUTTON_ICON = "src/main/java/Vue/Resources/Objects/reset.png";
        public static final String GAME_ICON = "./src/main/java/Vue/Resources/Tokens/icon.png";
    }

    /**
     * Class that stores the paths of the tokens PNGs used in the application
     *
     * @author Hugo Demule
     */
    public static final class Tokens {
        public static final String BLOCK = "/Tokens/block.png";

        public static final class LaserGun {
            public static final String UP = "/Tokens/laserGun_UP.png";
            public static final String DOWN = "/Tokens/laserGun_DOWN.png";
            public static final String LEFT = "/Tokens/laserGun_LEFT.png";
            public static final String RIGHT = "/Tokens/laserGun_RIGHT.png";
        }

        public static final class Mirror {
            public static final String UP = "/Tokens/mirror_UP.png";
            public static final String DOWN = "/Tokens/mirror_DOWN.png";
            public static final String LEFT = "/Tokens/mirror_LEFT.png";
            public static final String RIGHT = "/Tokens/mirror_RIGHT.png";
        }

        public static final class DoubleMirror {
            public static final String UP_DOWN = "/Tokens/doubleMirror_UD.png";
            public static final String RIGHT_LEFT = "/Tokens/doubleMirror_RL.png";
        }

        public static final class Target {
            public static final String UP = "/Tokens/target_UP.png";
            public static final String DOWN = "/Tokens/target_DOWN.png";
            public static final String LEFT = "/Tokens/target_LEFT.png";
            public static final String RIGHT = "/Tokens/target_RIGHT.png";
        }

        public static final class Splitter {
            public static final String UP_DOWN = "/Tokens/splitter_UD.png";
            public static final String RIGHT_LEFT = "/Tokens/splitter_RL.png";
        }

        public static final class Checkpoint {
            public static final String UP_DOWN = "/Tokens/checkpoint_UD.png";
            public static final String RIGHT_LEFT = "/Tokens/checkpoint_RL.png";
        }
    }
}
