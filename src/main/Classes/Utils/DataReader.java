package Classes.Utils;

import Classes.Level.Board;
import Classes.Level.LevelID;
import Classes.Token.Orientation;
import Classes.Token.Token;
import Classes.Token.TokenID;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

public class DataReader {

    /* TODO : change the place of final static variable inside a global class */
    private final static String LEVELSDATAPATH = "src/resources/data/levels.json";
    private final static String LEVELSIDSDATAPATH = "src/resources/data/levelsIDs.json";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String LEVELS = "levels";
    private final static String STARTING_BOARD = "startingBoard";
    private final static String SOLUTION_BOARD = "solutionBoard";
    private final static String TYPE = "type";
    private final static String ISMOVABLE = "isMovable";

    private final static String LEVELSIDS = "levelsIDs";

    /**
     * Reads a JSON file and returns a JSONObject
     * @param path the path to the JSON file
     * @return the JSONObject of the file
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
     * Finds a level by its ID
     * @param id the ID of the level
     * @return the JSONObject of the level
     * @throws Exception if the ID is not found
     */
    private static JSONObject findLevelByID(LevelID id) throws Exception {
        JSONArray jsonLevels = Objects.requireNonNull(json(LEVELSDATAPATH)).getJSONArray(LEVELS);

        for (int i = 0; i < jsonLevels.length(); i++) {
            JSONObject level = jsonLevels.getJSONObject(i);
            if (level.get(ID).equals(id.value())) {
                return level;
            }
        }
        throw new Exception("LevelID of value \"" + id.value() + "\" was not found in game data.");
    }

    /**
     * Creates a Token object from a JSONObject
     * @param token the JSONObject of the token
     * @return the Token object
     */
    private static Token createToken(JSONObject token) {
        TokenID id = new TokenID(token.getString(ID));
        String type = token.getString(TYPE);
        Orientation orientation = Orientation.valueOf(token.getString("orientation"));
        boolean isMovable = token.getBoolean("isMovable");
        /* TODO Create the Token Object */
        return null;
    }

    /**
     * Extracts the LevelIDs from the game data files
     * @param path the path to the corresponding JSON file
     * @return a list of LevelIDs
     */
    public static List<LevelID> extractLevelIDs(String path) {
        JSONArray jsonIDs = Objects.requireNonNull(json(LEVELSIDSDATAPATH)).getJSONArray(LEVELSIDS);
        List<LevelID> levelIDS = new ArrayList<>();

        for (int i = 0; i < jsonIDs.length(); i++) levelIDS.add(new LevelID(jsonIDs.getString(i)));

        return levelIDS;
    }

    /**
     * Reads the name of a level given its ID
     * @param id the ID of the level
     * @return the name of the level
     * @throws Exception if the ID is not found
     */
    /* TODO replace Exception by a custom one we create. */
    public static String readLevelIDName(LevelID id) throws Exception {
        JSONObject level = findLevelByID(id);
        return level.getString(NAME);
    }

    /**
     * Reads the tokens of a level given its ID
     * @param id the ID of the level
     * @return the set of tokens of the level
     * @throws Exception if the ID is not found
     */
    public static Set<Token> readLevelIDTokens(LevelID id) throws Exception {
        JSONObject level = findLevelByID(id);
        Set<Token> tokens = new HashSet<>();
        JSONArray jsonTokens = level.getJSONArray("tokens");
        jsonTokens.forEach(jsonToken -> tokens.add(createToken((JSONObject) jsonToken)));
        return tokens;
    }

    /**
     * Reads the starting board of a level given its ID
     * @param id the ID of the level
     * @return the board of the level
     * @throws Exception if the ID is not found
     */
    public static Board readLevelIDBoard(LevelID id) throws Exception {
        JSONObject level = findLevelByID(id);
        JSONObject board = level.getJSONObject(STARTING_BOARD);
        /* TODO */
        return null;
    }

}
