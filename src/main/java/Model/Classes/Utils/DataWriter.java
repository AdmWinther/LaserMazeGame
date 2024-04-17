package Model.Classes.Utils;

import Model.Classes.Level;
import Model.Classes.LevelID;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Token;
import Model.Classes.Token.TokenID;
import Model.constants.FilePaths;
import Model.constants.JsonConstants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class DataWriter {

    /**
     * Writes a level to a JSON file into the default directory (SANDBOX_LEVELS_PATH)
     *
     * @param level The level to be written
     * @return True if the level was written successfully, false otherwise
     */
    public static boolean write(Level level) {
        return write(level, FilePaths.SANDBOX_LEVELS_PATH);
    }

    /**
     * Writes a level to a JSON file into a given directory
     *
     * @param level         The level to be written
     * @param parentDirPath The path of the directory where the file will be written
     *                      | USE FilePaths.SANDBOX_LEVELS_PATH for sandbox levels
     *                      | USE FilePaths.CAMPAIGN_LEVELS_PATH for campaign levels
     * @return True if the level was written successfully, false otherwise
     */
    public static boolean write(Level level, String parentDirPath) {

        JSONObject jsonLevel = new JSONObject();
        addName(jsonLevel, level);
        addBoardSize(jsonLevel, level);
        addPlacedTokens(jsonLevel, level);
        addUnplacedTokens(jsonLevel, level);

        String id = level.id().value();
        try (FileWriter writer = new FileWriter(parentDirPath + id + ".json")) {
            writer.write(jsonLevel.toString(4));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a level from the default directory (SANDBOX_LEVELS_PATH)
     *
     * @param levelID The ID of the level to be deleted
     * @return True if the level was deleted successfully, false otherwise
     * @throws IOException if the file does not exist
     */
    public static boolean delete(LevelID levelID) throws IOException {
        if (Files.exists(Path.of(FilePaths.SANDBOX_LEVELS_PATH + levelID.value() + ".json"))) {
            Files.delete(Path.of(FilePaths.SANDBOX_LEVELS_PATH + levelID.value() + ".json"));
        } else if (Files.exists(Path.of(FilePaths.CAMPAIGN_LEVELS_PATH + levelID.value() + ".json"))) {
            Files.delete(Path.of(FilePaths.CAMPAIGN_LEVELS_PATH + levelID.value() + ".json"));
        } else {
            return false;
        }
        return true;
    }

    private static void addName(JSONObject jsonLevel, Level level) {
        String name = level.name();
        jsonLevel.put(JsonConstants.ATTR_NAME, name);
    }

    private static void addBoardSize(JSONObject jsonLevel, Level level) {
        JSONObject boardSize = new JSONObject();
        boardSize.put(JsonConstants.ATTR_WIDTH_X, level.width);
        boardSize.put(JsonConstants.ATTR_HEIGHT_Y, level.height);
        jsonLevel.put(JsonConstants.ATTR_BOARD_SIZE, boardSize);
    }

    private static void addPlacedTokens(JSONObject jsonLevel, Level level) {
        Token[][] placedTokens = level.tokenManager().getPlacedTokens();
        JSONArray placedTokensJson = createPlacedTokensJson(placedTokens);
        jsonLevel.put(JsonConstants.ATTR_PLACED_TOKENS, placedTokensJson);
    }

    private static void addUnplacedTokens(JSONObject jsonLevel, Level level) {
        Set<Token> unplacedTokens = level.tokenManager().getUnplacedTokens();
        JSONArray unplacedTokensJson = createUnplacedTokensJson(unplacedTokens);
        jsonLevel.put(JsonConstants.ATTR_UNPLACED_TOKENS, unplacedTokensJson);
    }


    private static JSONArray createPlacedTokensJson(Token[][] placedTokens) {
        JSONArray placedTokensJson = new JSONArray();
        for (int x = 0; x < placedTokens.length; x++) {
            for (int y = 0; y < placedTokens[0].length; y++) {
                if (placedTokens[x][y] != null) {
                    JSONObject tokenJson = new JSONObject();
                    Token token = placedTokens[x][y];

                    TokenID tokenID = token.id();
                    tokenJson.put(JsonConstants.ATTR_TOKEN_ID, tokenID.value());

                    tokenJson.put(JsonConstants.ATTR_TOKEN_TYPE, token.type());

                    Orientation orientation;
                    if ((orientation = getOrientation(token)) != null) {
                        tokenJson.put(JsonConstants.ATTR_ORIENTATION, orientation);
                    }

                    JSONObject position = new JSONObject();
                    position.put(JsonConstants.ATTR_X, x);
                    position.put(JsonConstants.ATTR_Y, y);
                    tokenJson.put(JsonConstants.ATTR_POSITION, position);
                    placedTokensJson.put(tokenJson);
                }
            }
        }
        return placedTokensJson;
    }

    private static JSONArray createUnplacedTokensJson(Set<Token> unplacedTokens) {
        JSONArray unplacedTokensJson = new JSONArray();
        for (Token token : unplacedTokens) {
            JSONObject tokenJson = new JSONObject();
            TokenID tokenID = token.id();
            tokenJson.put(JsonConstants.ATTR_TOKEN_ID, tokenID.value());
            tokenJson.put(JsonConstants.ATTR_TOKEN_TYPE, token.type());
            Orientation orientation;
            if ((orientation = getOrientation(token)) != null) {
                tokenJson.put(JsonConstants.ATTR_ORIENTATION, orientation);
            }
            unplacedTokensJson.put(tokenJson);
        }
        return unplacedTokensJson;
    }

    private static Orientation getOrientation(Token token) {
        if (token instanceof OrientedToken) {
            return ((OrientedToken) token).getOrientation();
        } else return null;
    }


}
