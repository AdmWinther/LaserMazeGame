package Classes.Utils;

import Classes.Level.LevelID;
import Classes.Token.*;
import Resources.constants.FilePaths;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void testTokensOfALevel() {
        try {
            LevelID levelID = DataReader.extractLevelIDs(LEVELS_IDS_DATA_PATH).get(0);
            Set<Token> tokens = DataReader.readLevelIDTokens(levelID);
            Set<Token> checkList = new HashSet<>(List.of(
                    new OneSidedMirror(new TokenID("OSM - 1"), true, Orientation.RIGHT),
                    new Block(new TokenID("Block - 1"), true)
            ));
            assertEquals(tokens.size(), checkList.size());
            assertTrue(tokens.containsAll(checkList));
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }
}
