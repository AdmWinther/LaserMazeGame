package Classes.Utils;

import Classes.Level.Board;
import Classes.Level.Level;
import Classes.Level.LevelID;
import Classes.Token.*;
import Resources.constants.FilePaths;
import Resources.constants.JsonTokens;
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

    private static void requireFileFound(JSONObject json, String idValue) throws FileNotFoundException {
        if (json == null) throw new FileNotFoundException("LevelID of value \"" + idValue + "\" was not found in game data.");
    }

    /**
     * Finds a level by its ID
     * @param id the ID of the level
     * @return the JSONObject of the level
     * @throws NullPointerException if the path to the JSON file does not exist
     * @author Hugo Demule
     */
    private static JSONObject findLevelByID(LevelID id) throws NullPointerException {
        JSONArray jsonLevels = Objects.requireNonNull(json(FilePaths.LEVELSDATAPATH)).getJSONArray(JsonTokens.LEVELS);

        for (int i = 0; i < jsonLevels.length(); i++) {
            JSONObject level = jsonLevels.getJSONObject(i);
            if (level.get(JsonTokens.ID).equals(id.value())) {
                return level;
            }
        }
        return null;
    }

    /**
     * Creates a Token object from a JSONObject
     * @param jsonToken the JSONObject of the token
     * @return the Token object
     * @author Hugo Demule
     */
    private static Token createToken(JSONObject jsonToken) {
        TokenID id = new TokenID(jsonToken.getString(JsonTokens.ID));
        String type = jsonToken.getString(JsonTokens.TYPE);
        Orientation orientation = Orientation.valueOf(jsonToken.getString("orientation"));
        boolean isMovable = jsonToken.getBoolean("isMovable");
        switch (type) {
            case JsonTokens.LASER_GUN_TOKEN:
                return new LazerGun(isMovable, orientation);
            case JsonTokens.RECEIVER_TOKEN:
                return new Receiver(isMovable, orientation);
            case JsonTokens.DOUBLE_SIDED_MIRROR_TOKEN:
                return new DoubleSidedMirror(isMovable, orientation);
            case JsonTokens.ONE_SIDED_MIRROR_TOKEN:
                return new OneSidedMirror(isMovable, orientation);
            case JsonTokens.BLOCK_TOKEN:
                return new Block(isMovable);
            default:
        }
        return null;
    }

    /**
     * Creates a set of placed tokens from a JSONObject
     * @param jsonBoard the JSONObject of the board
     * @return the set of placed tokens
     * @author Hugo Demule
     */
    private static Set<Pair<Token, Coordinate>> createPlacedTokens(JSONObject jsonBoard){
        Set<Pair<Token, Coordinate>> placedTokens = new HashSet<>();
        JSONArray jsonTokens = jsonBoard.getJSONArray("tokens");
        for (int i = 0; i < jsonTokens.length(); i++) {
            JSONObject jsonToken = jsonTokens.getJSONObject(i);
            Token token = createToken(jsonToken);

            JSONObject coordinate = jsonToken.getJSONObject("coordinate");
            int x = coordinate.getInt("x");
            int y = coordinate.getInt("y");

            Pair<Token, Coordinate> placedToken = new Pair<>(token, new Coordinate(x, y));
            placedTokens.add(placedToken);
        }
        return placedTokens;
    }

    /**
     * Creates a board from a JSONObject
     * @param id the ID of the level
     * @param boardType the type of the board (starting or solution)
     * @return the board object
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    private static Board createBoard(LevelID id, String boardType) throws FileNotFoundException {
        JSONObject level = findLevelByID(id);
        requireFileFound(level, id.value());
        JSONObject board = level.getJSONObject(boardType);
        return new Board(createPlacedTokens(board));
    }

    /**
     * Extracts the LevelIDs from the game data files
     * @param path the path to the corresponding JSON file
     * @return a list of LevelIDs
     * @author Hugo Demule
     */
    public static List<LevelID> extractLevelIDs(String path) {
        JSONArray jsonIDs = Objects.requireNonNull(json(FilePaths.LEVELSIDSDATAPATH)).getJSONArray(JsonTokens.LEVELS_IDS);
        List<LevelID> levelIDS = new ArrayList<>();

        for (int i = 0; i < jsonIDs.length(); i++) levelIDS.add(new LevelID(jsonIDs.getString(i)));

        return levelIDS;
    }

    /**
     * Reads the name of a level given its ID
     * @param id the ID of the level
     * @return the name of the level
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    public static String readLevelIDName(LevelID id) throws FileNotFoundException {
        JSONObject level = findLevelByID(id);
        requireFileFound(level, id.value());
        return level.getString(JsonTokens.NAME);
    }

    /**
     * Reads the tokens of a level given its ID
     * @param id the ID of the level
     * @return the set of tokens of the level
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    public static List<Token> readLevelIDTokens(LevelID id) throws FileNotFoundException {
        JSONObject level = findLevelByID(id);
        requireFileFound(level, id.value());
        List<Token> tokens = new ArrayList<>();
        JSONArray jsonTokens = level.getJSONArray("tokens");
        jsonTokens.forEach(jsonToken -> tokens.add(createToken((JSONObject) jsonToken)));
        return tokens;
    }

    /**
     * Reads the starting board of a level given its ID
     * @param id the ID of the level
     * @return the board of the level
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    public static Board readLevelIDStartingBoard(LevelID id) throws FileNotFoundException, NullPointerException {
        return createBoard(id, JsonTokens.STARTING_BOARD);
    }

    /**
     * Reads the solution board of a level given its ID
     * @param id the ID of the level
     * @return the solution board of the level
     * @throws FileNotFoundException if the LevelID is not found in the game data
     * @author Hugo Demule
     */
    public static Board readLevelIDSolutionBoard(LevelID id) throws FileNotFoundException, NullPointerException {
        return createBoard(id, JsonTokens.SOLUTION_BOARD);
    }

}
