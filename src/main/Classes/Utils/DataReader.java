package Classes.Utils;

import Classes.Level.Board;
import Classes.Level.LevelID;
import Classes.Token.Token;
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

    private final static String LEVELSIDS = "levelsIDs";

    private static JSONObject json(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject findLevelByID(LevelID id) throws Exception{
        JSONArray jsonLevels = Objects.requireNonNull(json(LEVELSDATAPATH)).getJSONArray(LEVELS);

        for (int i = 0; i < jsonLevels.length(); i++) {
            JSONObject level = jsonLevels.getJSONObject(i);
            if (level.get(ID).equals(id.value())) {
                return level;
            }
        }
        throw new Exception("LevelID of value \"" + id.value() + "\" was not found in game data.");
    }

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

    public static void main(String[] args) {
        try {
            System.out.println(readLevelIDName(new LevelID("2")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Board readLevelIDBoard(LevelID id) {
        /* TODO */
        return null;
    }

    public static Set<Token> readLevelIDTokens(LevelID id) throws Exception {

        return null;
    }
    
}
