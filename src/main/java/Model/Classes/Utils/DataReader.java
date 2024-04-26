package Model.Classes.Utils;

import Model.Classes.Level.LevelID;
import Model.Classes.Token.*;
import Model.constants.FilePaths;
import Model.constants.JsonConstants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

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
            Path filePath = Paths.get(path);
            if (Files.notExists(filePath)) return null;

            String content = new String(Files.readAllBytes(filePath));
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
     * @return the JSONObject of the level, null if not found
     * @author Hugo Demule
     */
    private static JSONObject findLevelByID(LevelID id) {
        JSONObject jsonLevel;
        if ((jsonLevel = json(FilePaths.CAMPAIGN_LEVELS_PATH + id.value() + ".json")) != null) {
            return jsonLevel;
        } else if ((jsonLevel = json(FilePaths.SANDBOX_LEVELS_PATH + id.value() + ".json")) != null) {
            return jsonLevel;
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
        TokenID id = new TokenID(jsonToken.getString(JsonConstants.ATTR_TOKEN_ID)); // Useless for now

        String type = jsonToken.getString(JsonConstants.ATTR_TOKEN_TYPE);
        Orientation orientation = jsonToken.has(JsonConstants.ATTR_ORIENTATION)
                ? Orientation.valueOf(jsonToken.getString(JsonConstants.ATTR_ORIENTATION))
                : null;

        switch (type) {
            case JsonConstants.VAL_TYPE_LASER_GUN:
                return new LaserGun(isMovable, orientation);
            case JsonConstants.VAL_TYPE_TARGET:
                return new Target(isMovable, orientation);
            case JsonConstants.VAL_DOUBLE_SIDED_MIRROR:
                return new DoubleSidedMirror(isMovable, orientation);
            case JsonConstants.VAL_TYPE_SPLITTER:
                return new Splitter(isMovable, orientation);
            case JsonConstants.VAL_TYPE_ONE_SIDED_MIRROR:
                return new OneSidedMirror(isMovable, orientation);
            case JsonConstants.VAL_TYPE_BLOCK:
                return new Block(isMovable);
            default:
        }

        return null;
    }

    private static Set<Token> createUnplacedTokens(JSONArray jsonArrayUnplacedTokens) {
        //TokenID id = new TokenID(jsonToken.getString(JsonConsts.ATTR_ID)); // Useless for now

        Set<Token> unplacedTokens = new HashSet<>();
        if (!jsonArrayUnplacedTokens.isEmpty()) {
            for (int i = 0; i < jsonArrayUnplacedTokens.length(); i++) {
                JSONObject jsonToken = jsonArrayUnplacedTokens.getJSONObject(i);
                Token token = createToken(jsonToken, true);
                unplacedTokens.add(token);
            }
        }
        return unplacedTokens;
    }


    /**
     * Creates a set of placed tokens from a JSONObject
     *
     * @param jsonTokensArray the JSONArray of the tokens
     * @param widthX          the Board width in X direction
     * @param heightY         the Board width in Y direction
     * @return the two dimension array of tokens
     * @author Hugo Demule
     */
    private static Token[][] createPlacedTokens(JSONArray jsonTokensArray, int widthX, int heightY) {
        Token[][] placedTokens = new Token[widthX][heightY];
        if (!jsonTokensArray.isEmpty()) {
            for (int i = 0; i < jsonTokensArray.length(); i++) {
                JSONObject jsonToken = jsonTokensArray.getJSONObject(i);
                Token token = createToken(jsonToken, false);

                JSONObject coordinate = jsonToken.getJSONObject(JsonConstants.ATTR_POSITION);
                int x = coordinate.getInt(JsonConstants.ATTR_X);
                int y = coordinate.getInt(JsonConstants.ATTR_Y);
                //todo: Make sure x and y are on the board
                placedTokens[x][y] = token;
            }
        }

        return placedTokens;
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
        return level.getString(JsonConstants.ATTR_NAME);
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
        //getting the JSON for level using the level ID
        JSONObject jsonLevel = findLevelByID(id);
        requireFileFound(jsonLevel, id.value());

        //Make a JSONArray of unplaced tokens.
        JSONArray jsonArrayUnplacedTokens = jsonLevel.getJSONArray(JsonConstants.ATTR_UNPLACED_TOKENS);
        return createUnplacedTokens(jsonArrayUnplacedTokens);
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

        //getting the JSON for level using the level ID
        JSONObject jsonLevel = findLevelByID(id);

        //Check if the level is empty, it means that it could not find the level.
        requireFileFound(jsonLevel, id.value());

        //Make a JSONArray of the placed tokens
        JSONArray jsonArrayPlacedTokens = jsonLevel.getJSONArray(JsonConstants.ATTR_PLACED_TOKENS);

        //getting the board size, width and height.
        JSONObject jsonBoardSize = jsonLevel.getJSONObject(JsonConstants.ATTR_BOARD_SIZE);
        int widthX = jsonBoardSize.getInt(JsonConstants.ATTR_WIDTH_X);
        int heightY = jsonBoardSize.getInt(JsonConstants.ATTR_HEIGHT_Y);

        //generate the 2D array of Tokens, named as PlacedTokens
        return createPlacedTokens(jsonArrayPlacedTokens, widthX, heightY);
    }

    /**
     * Reads the campaign level IDs
     *
     * @return a list of LevelIDs of the campaign levels
     * @author Hugo Demule
     */
    public static List<LevelID> readCampaignLevelIDs() {
        return readLevelIDs(FilePaths.CAMPAIGN_LEVELS_PATH);
    }

    /**
     * Reads the sandbox level IDs
     *
     * @return a list of LevelIDs of the sandbox levels
     * @author Hugo Demule
     */
    public static List<LevelID> readSandboxLevelIDs() {
        return readLevelIDs(FilePaths.SANDBOX_LEVELS_PATH);
    }

    private static List<LevelID> readLevelIDs(String path) {
        List<LevelID> levelIDs = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            // Create a list of strings that contains all the files names stored at FilePaths.CAMPAIGN_LEVELS_PATH
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        String fileName = file.getFileName().toString();
                        String fileID = fileName.substring(0, fileName.lastIndexOf('.'));
                        levelIDs.add(new LevelID(fileID));
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return levelIDs;
    }
}



