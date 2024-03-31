<<<<<<< HEAD
package java.Classes.Utils;

public class DataReaderTest {

=======
package Classes.Utils;

import Classes.Level.LevelID;
import Classes.Token.Block;
import Classes.Token.OneSidedMirror;
import Classes.Token.Orientation;
import Classes.Token.Token;
import Resources.constants.FilePaths;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DataReaderTest {

    private static final String LEVELS_IDS_DATA_PATH = FilePaths.LEVELS_IDS_DATA_PATH;
    private static final String LEVELS_DATA_PATH = FilePaths.LEVELS_DATA_PATH;

    @Test
    public void testLevelIDs1() {
        List<LevelID> jsonIDs = DataReader.extractLevelIDs(LEVELS_IDS_DATA_PATH);
        List<LevelID> checkList = new ArrayList<>(List.of(
                new LevelID("0"),
                new LevelID("1")
        ));
        assertEquals(jsonIDs, checkList);
    }

    @Test
    public void testLevelIDs2() {
        List<LevelID> jsonIDs = DataReader.extractLevelIDs(LEVELS_IDS_DATA_PATH);
        List<LevelID> checkList = new ArrayList<>(List.of(
                new LevelID("0"),
                new LevelID("1"),
                new LevelID("2")
        ));
        assertNotEquals(jsonIDs, checkList);
    }

    @Test
    public void testLevelName() {
        try {
            LevelID levelID = DataReader.extractLevelIDs(LEVELS_IDS_DATA_PATH).get(0);
            String levelName = DataReader.readLevelIDName(levelID);
            assertEquals(levelName, "Level 1");
        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

    @Test
    public void testTokensOfALevel(){
        try {
            LevelID levelID = DataReader.extractLevelIDs(LEVELS_IDS_DATA_PATH).get(0);
            List<Token> tokens = DataReader.readLevelIDTokens(levelID);
            List<Token> checkList = new ArrayList<>(List.of(
                    new OneSidedMirror(true, Orientation.RIGHT),
                    new Block(true)
            ));
            //assertEquals(tokens, checkList); // Should work if equals is redefined in Token
            assertEquals(tokens.get(0).getClass(), checkList.get(0).getClass());
            assertEquals(tokens.get(1).getClass(), checkList.get(1).getClass());
            assertEquals(tokens.get(0).isMovable(), checkList.get(0).isMovable());
            assertEquals(tokens.get(1).isMovable(), checkList.get(1).isMovable());
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }


>>>>>>> origin/LevelBuilder
}
