package Classes.Level;
import Classes.Token.Token;
import Classes.Utils.DataReader;
import Interfaces.Buildable;

import java.util.List;
import java.util.Set;

public class LevelBuilder implements Buildable<Level> {
    private LevelID id;
    public LevelBuilder(LevelID id) {
        this.id = id;
    }

    public void setID(LevelID id){
        this.id = id;
    }

    @Override
    public Level build() {
        try {
            String name = DataReader.readLevelIDName(id);
            Board startingBoard = DataReader.readLevelIDStartingBoard(id);
            Board solutionBoard = DataReader.readLevelIDSolutionBoard(id);
            List<Token> tokens = DataReader.readLevelIDTokens(id);
            return new Level(startingBoard, solutionBoard, tokens, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
