package Classes.Level;
import Classes.Token.Token;
import Classes.Utils.DataReader;
import Interfaces.Buildable;

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
            Board board = DataReader.readLevelIDBoard(id);
            Set<Token> tokens = DataReader.readLevelIDTokens(id);
            return new Level(board, tokens, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
