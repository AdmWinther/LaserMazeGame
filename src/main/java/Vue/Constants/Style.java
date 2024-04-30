package Vue.Constants;

/**
 * This class is responsible for storing the style constants of the application
 *
 * @author Hugo Demule
 */
public class Style {

    /**
     * This class stores the fonts that are used in the entire application
     *
     * @author Hugo Demule
     */
    public static final class Font {
        public static final String COURIER_NEW = "Courier New";
        public static final String MONOSPACED = "MonoSpaced";
    }

    /**
     * This class stores the paddings that are used in the entire application
     *
     * @author Hugo Demule
     */
    public static final class Padding {

        public static final int XXL = 50;
        public static final int XL = 30;
        public static final int L = 20;
        public static final int M = 10;
        public static final int S = 5;
    }

    /**
     * This class stores the font sizes that are used in the entire application
     *
     * @author Hugo Demule
     */
    public static final class FontSize {
        public static final int H1 = 50;
        public static final int H2 = 35;
        public static final int H3 = 28;
    }

    /**
     * This class stores the different grid values that are used in the entire application
     *
     * @author Hugo Demule
     */
    public static final class Grid {
        public static final class MainMenu {
            public static final int ROWS = 3;
            public static final int COLS = 1;
        }

        public static final class CampaignMenu {
            public static final int ROWS = 4;
            public static final int COLS = 4;
        }

        public static final class LoginMenu {
            public static final int ROWS = 2;
            public static final int COLS = 2;
        }
    }
}
