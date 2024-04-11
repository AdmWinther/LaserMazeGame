package Classes.Utils;

import Classes.LevelID;
import Classes.Orientation;
import Classes.Tokens.*;
import Resources.constants.FilePaths;
import Resources.constants.JsonConsts;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataReader {
    /**
     * Reads a JSON file and returns a JSONObject
     *
     * @param path the path to the JSON file
     * @return the JSONObject of the file or null if there was an error
     * @author Hugo Demule
     */
    private static JSONObject json(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Throws a FileNotFoundException if the JSON object is null
     *
     * @param json    the JSONObject to check
     * @param idValue the value of the LevelID
     * @throws FileNotFoundException if the JSON object is null.
     */
    private static void requireFileFound(JSONObject json, String idValue) throws FileNotFoundException {
        if (json == null)
            throw new FileNotFoundException("LevelID of value \"" + idValue + "\" was not found in game data.");
    }

    /**
     * Finds a level by its ID
     *
     * @param id the ID of the level
     * @return the JSONObject of the level
     * @throws NullPointerException if the path to the JSON file does not exist
     * @author Hugo Demule
     */
    private static JSONObject findLevelByID(LevelID id) throws NullPointerException {
        JSONArray jsonLevels = Objects.requireNonNull(json(FilePaths.LEVELS_DATA_PATH)).getJSONArray(JsonConsts.ATTR_LEVELS);

        for (int i = 0; i < jsonLevels.length(); i++) {
            JSONObject level = jsonLevels.getJSONObject(i);
            if (level.get(JsonConsts.ATTR_ID).equals(id.value())) {
                return level;
            }
        }
        return null;
    }

    /**
     * Creates a Token object from a JSONObject
     *
     * @param jsonToken the JSONObject of the token
     * @return the Token object
     * @author Hugo Demule
     */
    private static Token createToken(JSONObject jsonToken, boolean isMovable) {
        TokenID id = new TokenID(jsonToken.getString(JsonConsts.ATTR_ID)); // Useless for now

        String type = jsonToken.getString(JsonConsts.ATTR_TOKEN_TYPE);
        Orientation orientation = jsonToken.has(JsonConsts.ATTR_ORIENTATION)
                ? Orientation.valueOf(jsonToken.getString(JsonConsts.ATTR_ORIENTATION))
                : null;

        switch (type) {
            case JsonConsts.VAL_TYPE_LASER_GUN:
                return new LaserGun(isMovable, orientation);
            case JsonConsts.VAL_TYPE_RECEIVER:
                return new Receiver(isMovable, orientation);
            case JsonConsts.VAL_DOUBLE_SIDED_MIRROR:
                return new DoubleSidedMirror(isMovable, orientation);
            case JsonConsts.VAL_TYPE_ONE_SIDED_MIRROR:
                return new OneSidedMirror(isMovable, orientation);
            case JsonConsts.VAL_TYPE_BLOCK:
                return new Block(isMovable);
            default:
        }

        return null;
    }

    private static Set<Token> createUnplacedTokens(JSONObject jsonUnplacedTokens) {
        //TokenID id = new TokenID(jsonToken.getString(JsonConsts.ATTR_ID)); // Useless for now

        Set<Token> unplacedTokens = new HashSet<>();
        JSONArray jsonTokensArray = jsonUnplacedTokens.getJSONArray(JsonConsts.ATTR_UNPLACED_TOKENS);
        if(!jsonTokensArray.isEmpty()){
            for (int i = 0; i < jsonTokensArray.length(); i++) {
                JSONObject jsonToken = jsonTokensArray.getJSONObject(i);
                Token token = createToken(jsonToken, true);
                unplacedTokens.add(token);
            }
            return unplacedTokens;
        }
        return null;
    }


    /**
     * Creates a set of placed tokens from a JSONObject
     *
     * @param jsonPlacedTokens the JSONObject of the PlacedTokens
     * @param widthX the Board width in X direction
     * @param heightY the Board width in Y direction
     * @return the two dimension array of tokens
     * @author Hugo Demule
     */
    private static Token[][] createPlacedTokens(JSONObject jsonPlacedTokens, int widthX, int heightY) {
        JSONArray jsonTokensArray = jsonPlacedTokens.getJSONArray(JsonConsts.ATTR_PLACED_TOKENS);
        if(!jsonTokensArray.isEmpty()){
            Token[][] placedTokens = new Token[widthX][heightY];
            for (int i = 0; i < jsonTokensArray.length(); i++) {
                JSONObject jsonToken = jsonTokensArray.getJSONObject(i);
                Token token = createToken(jsonToken, false);

                JSONObject coordinate   = jsonToken.getJSONObject(JsonConsts.ATTR_POSITION);
                int x = coordinate.getInt(JsonConsts.ATTR_X);
                int y = coordinate.getInt(JsonConsts.ATTR_Y);
                //todo: Make sure x and y are on the board
                placedTokens[x][y] = token;
            }
        }

        return new Token[0][0];
    }



    /**
     * Extracts the LevelIDs from the game data files
     * <<<<<<< HEAD
     *
     * @param path the path to the corresponding JSON file
     *             =======
     *             >>>>>>> origin/LevelBuilder
     * @return a list of LevelIDs
     * @throws NullPointerException if the path to the JSON file does not exist
     * @author Hugo Demule
     */
    public static List<LevelID> extractLevelIDs(String path) throws NullPointerException {
        JSONArray jsonIDs = Objects.requireNonNull(json(path)).getJSONArray(JsonConsts.ATTR_LEVELS_IDS);
        List<LevelID> levelIDS = new ArrayList<>();

        for (int i = 0; i < jsonIDs.length(); i++) levelIDS.add(new LevelID(jsonIDs.getString(i)));

        return levelIDS;
    }

    /**
     * Reads the name of a level given its ID
     *
     * @param id the ID of the level
     * @return the name of the level
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    public static String readLevelIDName(LevelID id) throws FileNotFoundException {
        JSONObject level = findLevelByID(id);
        requireFileFound(level, id.value());
        return level.getString(JsonConsts.ATTR_NAME);
    }

    /**
     * Reads the tokens of a level given its ID
     *
     * @param id the ID of the level
     * @return the set of tokens of the level
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    public static Set<Token> readLevelIDUnplacedTokens(LevelID id) throws FileNotFoundException {
        JSONObject jsonLevel = findLevelByID(id);
        requireFileFound(jsonLevel, id.value());
        JSONObject jsonUnplacedTokens = jsonLevel.getJSONObject(JsonConsts.ATTR_UNPLACED_TOKENS);
        return createUnplacedTokens(jsonUnplacedTokens);
    }

    /**
     * Reads the starting board of a level given its ID
     *
     * @param id the ID of the level
     * @return the board of the level
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    public static Token[][] readLevelIDPlacedTokens(LevelID id) throws FileNotFoundException {
        JSONObject jsonLevel = findLevelByID(id);
        requireFileFound(jsonLevel, id.value());
        JSONObject jsonPlacedTokens = jsonLevel.getJSONObject(JsonConsts.ATTR_PLACED_TOKENS);
        JSONObject jsonBoardSize = jsonLevel.getJSONObject(JsonConsts.ATTR_BOARD_SIZE);
        int widthX = jsonBoardSize.getInt(JsonConsts.ATTR_WIDTH_X);
        int heightY = jsonBoardSize.getInt(JsonConsts.ATTR_HEIGHT_Y);
        return createPlacedTokens(jsonPlacedTokens, widthX, heightY);
    }
}

