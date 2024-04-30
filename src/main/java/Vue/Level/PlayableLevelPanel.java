package Vue.Level;

import Controller.GameController;
import Controller.LoginController;
import Controller.PlayableLevelController;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.ExtrasUI;
import Vue.MenuPanels.MainMenuPanel;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static Controller.LevelPreparation.preparePlayableLevel;
import static Vue.Game.Game.showPanel;

/**
 * This class has the responsibility to control the playable level panel and extends the functionalities of the level panel.
 *
 * @author Hossein (Adam)
 * @author Léonard Amsler
 * @author Nathan Gromb
 * @see LevelPanel
 */
public final class PlayableLevelPanel extends LevelPanel {

    private final AnimationsUI animationsUI;
    private final ExtrasUI extrasUI;
    private boolean areActionsForLevelComplitionStarted = false;
    private Timer timer;


    /**
     * Constructor of the PlayableLevelPanel
     *
     * @param frame           The frame of the game
     * @param gameController  The game controller of the game
     * @param levelController The level controller of the game
     * @param loginController The login controller of the game
     * @author Léonard Amsler
     */
    public PlayableLevelPanel(JFrame frame, GameController gameController, PlayableLevelController levelController, LoginController loginController) {
        super(frame, gameController, levelController, loginController);
        extrasUI = new ExtrasUI(this);
        animationsUI = new AnimationsUI(this);
    }

    /**
     * Starts the game thread
     *
     * @author Léonard Amsler
     * @author Hossein (Adam)
     */
    @Override
    public void run() {

        double delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;

        int count = 0;
        int lastSecond = 0;

        while (gameThread != null) {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - lastTime) / (double) frameTime;
            lastTime = currentTime;

            if (currentTime / 1000 != lastSecond) {
                lastSecond = (int) (currentTime / 1000);
                count = 0;
            }

            //Disable the mouse and keyboard listeners when the level is completed.
            if (((PlayableLevelController) (this.levelController)).isLevelCompleted()) {
                removeMouseListener(this.levelMouseHandler);
                removeMouseListener(this.tokenMouseHandler);
                removeKeyListener(this.tokenKeyboardHandler);
            }

            if (delta >= 1) {
                repaint();
                delta--;
                count++;
            }
        }
    }

    /**
     * Paints the components of the level. In case the level is completed, it will display the bingo message and
     * start the timer to go to the next level or go back to the main menu.
     *
     * @param g The graphics object
     * @author Léonard Amsler
     * @author Adam Winther
     * @author Nathan Gromb
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawTiles(g2d);
        drawLasers(g2d);
        extrasUI.draw(g2d);
        drawTokens(g2d);
        animationsUI.draw(g2d);
        if (((PlayableLevelController) (this.levelController)).isLevelCompleted()) {
            if (!areActionsForLevelComplitionStarted) {
                animationsUI.confetti();
                animationsUI.bingo();


                areActionsForLevelComplitionStarted = true;
                SoundPlayer.play(SoundPaths.LEVEL_PASSED_SOUND_PATH);
                this.timer = new Timer("delayAfterLevelCompleted");
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        animationsUI.circle(Color.BLACK);
                    }
                }, 1000);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        gameThread = null;
                        if (gameController.isInCampaignGameMode()) {

                            //if in the campaign mode, it should go to the next level.
                            int campaignProgression = loginController.getCampaignProgress();
                            LevelID currentLevel = gameController.getCurrentLevelID();
                            LevelID campaignProgressionLevelID = DataReader.readCampaignLevelIDs().get(campaignProgression - 1);
                            if (currentLevel.equals(campaignProgressionLevelID)) {
                                loginController.incrementProgression();
                            }
                            List<LevelID> levelIDs = DataReader.readCampaignLevelIDs();
                            int index = levelIDs.indexOf(currentLevel);
                            LevelID nextLevelID = levelIDs.get(index + 1);
                            preparePlayableLevel(nextLevelID, frame, gameController, loginController);

                        } else {
                            //if we are in Random level mode
                            MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController, loginController);
                            frame.add(mainMenuPanel, "MainMenu");
                            showPanel(frame, "MainMenu");
                            frame.pack();
                        }
                    }

                }, 4000);
            }
        }
    }

    /**
     * Get the extra UI
     *
     * @return The extra UI
     * @author Nathan Gromb
     */
    public ExtrasUI getExtrasUI() {
        return extrasUI;
    }
}
