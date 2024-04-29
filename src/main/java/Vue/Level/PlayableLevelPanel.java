package Vue.Level;

import Controller.GameController;
import Controller.LoginController;
import Model.Classes.Level.LevelID;
import Controller.PlayableLevelController;
import Model.Classes.Utils.DataReader;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.ExtrasUI;
import Vue.MenuPanels.MainMenuPanel;
import Vue.SoundEffects.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static Controller.LevelPreparation.preparePlayableLevel;
import static Vue.Game.Game.showPanel;


public final class PlayableLevelPanel extends LevelPanel {

    private final AnimationsUI animationsUI;
    private final ExtrasUI extrasUI;
    private final Clip tada;
    private boolean areActionsForLevelComplitionStarted = false;
    private Timer timer;


    public PlayableLevelPanel(JFrame frame, GameController gameController, PlayableLevelController levelController, LoginController loginController) {
        super(frame, gameController, levelController, loginController);
        extrasUI = new ExtrasUI(this);
        animationsUI = new AnimationsUI(this);
        this.tada = Sound.getLevelCompleted();
    }

    /**
     * Paints the components of the level. In case the level is completed, it will display the bingo message and
     * start the timer to go to the next level or go back to the main menu.
     *
     * @param g The graphics object
     * @Author Léonard Amsler, Adam Winther, Nathan Gromb
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);
        drawTiles(g2d);
        drawLasers(g2d);
        extrasUI.draw(g2d);
        drawTokens(g2d);
        animationsUI.draw(g2d);
        if(((PlayableLevelController) (this.levelController)).isLevelCompleted() ){
            extrasUI.drawBingo(g2d);
            if(!areActionsForLevelComplitionStarted){
                animationsUI.confetti();
                areActionsForLevelComplitionStarted = true;
                Sound.playLevelCompleted();
                this.timer = new Timer("delayAfterLevelCompleted");
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            gameThread = null;
                            if (gameController.isInCampaignGameMode()) {

                                //if in the campaign mode, it should go to the next level.
                                int campaignProgression = loginController.getCampaignProgress();
                                LevelID currentLevel = gameController.getCurrentLevelID();
                                LevelID campaignProgressionLevelID = DataReader.readCampaignLevelIDs().get(campaignProgression -1);
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

                        ;
                    }, 2000);
            }
        }
    }

    public ExtrasUI getExtrasUI() {
        return extrasUI;
    }

    /**
     * Starts the game thread
     *
     * @Author Léonard Amsler, Adam Winther
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
}
