package Vue.Level;

import Controller.GameController;
import Controller.LoginController;
import Controller.PlayableLevelController;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.ExtrasUI;
import Vue.SoundEffects.Sound;
import Vue.Utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static Controller.LevelPreparation.preparePlayableLevel;


public final class PlayableLevelPanel extends LevelPanel {

	private final AnimationsUI animationsUI;
	private final ExtrasUI extrasUI;
	private boolean areActionsForLevelComplitionStarted = false;
	private Timer timer;


	public PlayableLevelPanel(JFrame frame, GameController gameController, PlayableLevelController levelController, LoginController loginController) {
		super(frame, gameController, levelController, loginController);
		extrasUI = new ExtrasUI(this);
		animationsUI = new AnimationsUI(this);
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
		if (((PlayableLevelController) (this.levelController)).isLevelCompleted()) {
			if (!areActionsForLevelComplitionStarted) {
				animationsUI.confetti();
				animationsUI.bingo();

				areActionsForLevelComplitionStarted = true;
				Sound.playLevelCompleted();
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

						switch (gameController.getLevelType()) {
							case CAMPAIGN:
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
								FrameUtil.removeLevel(frame);
								preparePlayableLevel(nextLevelID, frame, gameController, loginController);
								break;
							case RANDOM:
								FrameUtil.removeLevel(frame);
								FrameUtil.createMainMenuIfNotExists(frame, gameController, loginController);
								FrameUtil.displayMainMenu(frame);
								break;
							case SANDBOX:
								FrameUtil.removeLevel(frame);
								FrameUtil.createSandboxMenuIfNotExists(frame, gameController, loginController);
								FrameUtil.displaySandboxMenu(frame);
								break;
						}
					}
				}, 4000);
			}
		}
	}

	public ExtrasUI getExtrasUI() {
		return extrasUI;
	}
}
