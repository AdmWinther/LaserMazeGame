package Classes.Utils;

import org.junit.Test;

import java.Classes.Utils.DataReader;
import java.Classes.Level.LevelID;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DataReaderTest {

    @Test
    public void testBase() {
        List<LevelID> jsonIDs = DataReader.extractLevelIDs();
        List<LevelID> checkList = new ArrayList<>(List.of(
                new LevelID("0"),
                new LevelID("1")
        ));

        assertEquals(jsonIDs, checkList);
    }

}
