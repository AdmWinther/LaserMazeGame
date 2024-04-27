package Vue.Level;

import Controller.GameController;
import Controller.LoginController;
import Controller.PlayableLevelController;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.ExtrasUI;
import Vue.SoundEffects.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public final class PlayableLevelPanel extends LevelPanel {

    private final AnimationsUI animationsUI;
    private final ExtrasUI extrasUI;
    private final Clip tada;
    private boolean tadaPlayed = false;
    private Timer timer;


    public PlayableLevelPanel(JFrame frame, GameController gameController, PlayableLevelController levelController, LoginController loginController) {
        super(frame, gameController, levelController, loginController);
        extrasUI = new ExtrasUI(this);
        animationsUI = new AnimationsUI(this);
        this.tada = Sound.getLevelCompleted();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);
        drawTiles(g2d);
        drawLasers(g2d);
        extrasUI.draw(g2d);
        drawTokens(g2d);
        animationsUI.draw(g2d);
    }

    public ExtrasUI getExtrasUI() {
        return extrasUI;
    }

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

//            if (((PlayableLevelController) (this.levelController)).levelComplete()) {
//                if (!tadaPlayed) {
//                    this.tada.start();
//                    this.tadaPlayed = true;
//                    this.timer = new Timer("delayAfterLevelCompleted");
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            gameThread = null;
//                            int campaignProgression = loginController.getCampaignProgress();
//                            if (gameController.getCampaignGameMode()) {
//                                //if in the campaign mode, it should go to the next level.
//                                LevelID currentLevel = gameController.getCurrentLevelID();
//                                LevelID campaignProgressionLevelID = DataReader.readCampaignLevelIDs().get(campaignProgression + 1);
//                                if (currentLevel.equals(campaignProgressionLevelID)) {
//                                    loginController.incrementProgression();
//                                }
//                                List<LevelID> levelIDs = DataReader.readCampaignLevelIDs();
//                                LevelID nextLevelID = levelIDs.get(campaignProgression + 1);
//                                preparePlayableLevel(nextLevelID, frame, gameController, loginController);
//                            } else {
//                                //if we are in Random level mode
//                                MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController, loginController);
//                                frame.add(mainMenuPanel, "MainMenu");
//                                showPanel(frame, "MainMenu");
//                                frame.pack();
//                            }
//                        }
//
//                        ;
//                    }, 2000);
//                }
            //this.extrasUI.drawBingo();
            //removeMouseListener(this.levelMouseHandler);

            if (delta >= 1) {
                repaint();
                delta--;
                count++;
            }
        }
    }
}
