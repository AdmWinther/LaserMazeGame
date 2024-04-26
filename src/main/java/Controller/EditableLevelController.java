package Controller;

import Model.Classes.Level.EditableLevel;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.DataWriter;
import Model.Interfaces.Inventory;
import Model.constants.FilePaths;

public class EditableLevelController extends LevelController{

    public EditableLevelController(EditableLevel level) {
        super(level);
    }

    public boolean addToPlacedTokens(Token token, Coordinate coordinate) {
        return ((EditableLevel) level).tokenManager().addToPlacedTokens(token, coordinate);
    }

    public Token removeFromPlacedTokens(Coordinate coordinate) {
        return ((EditableLevel) level).tokenManager().removeFromPlacedTokens(coordinate);
    }

    public boolean addToUnplacedTokens(Token token) {
        return ((EditableLevel) level).tokenManager().addToUnplacedTokens(token);
    }

    public boolean removeFromUnplacedTokens(Token token) {
        return ((EditableLevel) level).tokenManager().removeFromUnplacedTokens(token);
    }

    public boolean saveLevel() {
        ((EditableLevel) level).tokenManager().setPlacedTokensMovability(false);
        ((EditableLevel) level).tokenManager().setUnplacedTokensMovability(true);
        return DataWriter.write(level, FilePaths.SANDBOX_LEVELS_PATH);
    }

    public Inventory getInventory() {
        return ((EditableLevel) level).tokenManager().getInventory();
    }

    @Override
    public boolean isSandbox() {
        return true;
    }

    public void removeToken(Token token) {
        ((EditableLevel) level).tokenManager().removeToken(token);
    }
}
