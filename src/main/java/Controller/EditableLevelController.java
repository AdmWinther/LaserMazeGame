package Controller;

import Model.Classes.Level.EditableLevel;
import Model.Classes.Token.Token;
import Model.Classes.Utils.DataWriter;
import Model.Constants.FilePaths;
import Model.Interfaces.Inventory;
import Vue.Level.EditableLevelPanel;

import javax.swing.*;

/**
 * This class has the responsibility to control the editable level
 *
 * @author Hugo Demule
 * @author Nathan Gromb
 * @author Léonard Amsler
 * @see LevelController
 */
public class EditableLevelController extends LevelController<EditableLevelPanel, EditableLevel> {

    /**
     * Constructor of the EditableLevelController
     *
     * @param gameController GameController - The game controller
     * @param frame          JFrame - The frame
     * @param levelPanel     LevelPanel - The level panel
     * @param level          EditableLevel - The level
     * @author Hugo Demule (s231675)
     */
    public EditableLevelController(GameController gameController, JFrame frame, EditableLevelPanel levelPanel, EditableLevel level) {
        super(gameController, frame, levelPanel, level);
    }

    /**
     * Check if the level is in sandbox mode
     *
     * @return boolean - True if the level is in sandbox mode, false otherwise
     * @author Hugo Demule (s231675)
     */
    @Override
    public boolean isSandbox() {
        return true;
    }

    /**
     * Add a token to the unplaced tokens
     *
     * @param token Token - The token to add to the unplaced tokens
     * @author Hugo Demule (s231675)
     */
    public void addToUnplacedTokens(Token token) {
        level.tokenManager().addToUnplacedTokens(token);
    }

    /**
     * Saves the current level by setting the placed tokens as unmovable and the unplaced tokens as movables
     *
     * @return True if the level was saved successfully
     */
    public boolean saveLevel() {
        level.tokenManager().setPlacedTokensMovability(false);
        level.tokenManager().setUnplacedTokensMovability(true);
        return DataWriter.write(level, FilePaths.SANDBOX_LEVELS_PATH);
    }

    /**
     * Get the inventory
     *
     * @return Inventory - The inventory
     * @author Nathan Gromb
     */
    public Inventory getInventory() {
        return level.tokenManager().getInventory();
    }

    /**
     * Remove a token
     *
     * @param token Token - The token to remove
     * @author Nathan Gromb (s231674)
     */
    public void removeToken(Token token) {
        level.tokenManager().removeToken(token);
    }
}