package Vue.MainMenu;

import Controller.GameController;
import Controller.LoginController;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.Sound;
import Vue.Utils.ButtonUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static Vue.MainMenu.LevelPreparation.preparePlayableLevel;
import static Vue.MainMenu.LevelPreparation.showPanel;
import static Vue.Utils.ImageUtil.resizeImage;

/**
 * This class is responsible for the campaign panel
 *
 * @Author Léonard Amsler - s231715
 * @Author Nathan Gromb - s231674
 */
public class CampaignPanel extends JPanel {
	private final JFrame frame;
	private final JPanel buttons;
	private final JLabel backButton;
	GameController gameController;
	LoginController loginController;
	private int tileWidth = 16;
	private int tileHeight = 16;

	/**
	 * Constructor of the campaign panel class
	 *
	 * @param frame          - The frame
	 * @param gameController - The game controller
	 * @Author Léonard Amsler - s231715
	 * @Author Nathan Gromb - s231674
	 */
	public CampaignPanel(JFrame frame, GameController gameController, LoginController loginController) {
		this.loginController = loginController;
		this.gameController = gameController;
		this.frame = frame;

		setDoubleBuffered(true);

		setLayout(new BorderLayout());

		// Background image
		ImageIcon backgroundImage = new ImageIcon("src/main/java/Vue/Resources/Tiles/background.png");
		ImagePanel backgroundPanel = new ImagePanel(backgroundImage.getImage());
		backgroundPanel.setLayout(new BorderLayout());
		add(backgroundPanel, BorderLayout.CENTER);

		// Create and add level buttons
		this.buttons = getLevelButtons();
		backgroundPanel.add(buttons, BorderLayout.CENTER);

		this.backButton = getBackButton();
		backgroundPanel.add(backButton, BorderLayout.SOUTH);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resize();
			}
		});
	}

	/**
	 * Get the level buttons
	 *
	 * @return JPanel - The level buttons
	 * @Author Léonard Amsler - s231715
	 * @Author Nathan Gromb - s231674
	 */
	private JPanel getLevelButtons() {
		int campaignProgression = loginController.getCampaignProgress();
		campaignProgression += 1; // The user should be able to play the next level

		JPanel levelButtonPanel = new JPanel();
		int rows = 4; // TODO depend on amount of campaign levels
		int cols = 4;
		levelButtonPanel.setLayout(new GridLayout(rows, cols, 0, 0));

		levelButtonPanel.setOpaque(false); // Make the panel transparent

		int padding = 50;
		levelButtonPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

		BufferedImage enable_image = null;
		BufferedImage disable_image = null;
		try {
			enable_image = ImageIO.read(new File("src/main/java/Vue/Resources/Tiles/boardTile1.png"));
			disable_image = ImageIO.read(new File("src/main/java/Vue/Resources/Tiles/disabled_boardTile.png"));
		} catch (Exception e) {
			System.out.println("Error loading campaign button image");
		}
		assert enable_image != null;
		assert disable_image != null;

		int scale = 5;
		enable_image = resizeImage(enable_image, tileWidth * scale, tileHeight * scale);
		disable_image = resizeImage(disable_image, tileWidth * scale, tileHeight * scale);

		List<LevelID> levelIDs = DataReader.readCampaignLevelIDs();

		int i = 1;

		for (LevelID levelID : levelIDs) {
			ImageIcon scaledIcon = new ImageIcon(enable_image);
			JButton levelButton = new JButton(String.valueOf(i++), scaledIcon);

			levelButton.setHorizontalAlignment(SwingConstants.CENTER);

			levelButton.setHorizontalTextPosition(SwingConstants.CENTER);
			levelButton.setVerticalTextPosition(SwingConstants.CENTER);
			levelButton.setFont(new Font("MonoSpaced", Font.BOLD, 30));

			ButtonUtil.setButtonTransparent(levelButton);

			levelButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					Sound.playButtonSound();

					if (!levelButton.isEnabled()) {
						return;
					}
					preparePlayableLevel(new LevelID("level" + levelButton.getText()), frame, gameController, loginController);
				}
			});
			levelButton.addMouseListener(new ButtonHoverHandler());

			if (i <= campaignProgression) {
				levelButton.setEnabled(true);
			} else {
				levelButton.setEnabled(false);
				// apply a gray filter to the button
				levelButton.setDisabledIcon(new ImageIcon(disable_image));
			}

			levelButtonPanel.add(levelButton);
		}

		return levelButtonPanel;
	}

	/**
	 * Get the back button
	 *
	 * @return JLabel - The back button
	 * @Author Nathan Gromb - s231674
	 */
	private JLabel getBackButton() {
		JLabel button = new JLabel();

		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalAlignment(SwingConstants.CENTER);

		int padding = 50;
		button.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Sound.playButtonSound();
				frame.getContentPane().remove(CampaignPanel.this);
				showPanel(frame, "MainMenu");
			}
		});
		button.addMouseListener(new ButtonHoverHandler());

		return button;
	}

	/**
	 * Resize the campaign panel
	 *
	 * @Author Léonard Amsler - s231715
	 * @Author Nathan Gromb - s231674
	 */
	public void resize() {

		tileWidth = 100;
		tileHeight = 100;

		// Set button sizes and positions
		for (Component component : buttons.getComponents()) {
			if (component instanceof JLabel button) {
				ImageIcon icon = new ImageIcon("src/main/java/Vue/Resources/Tiles/boardTile1.png");
				ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(tileWidth, tileHeight, Image.SCALE_DEFAULT));
				button.setIcon(scaledIcon);
				button.setFont(new Font("MonoSpaced", Font.BOLD, tileWidth / 2));
			}
		}

		// Update back button size and position
		int backButtonSize = tileWidth / 3 * 2;

		// Set the size and position of the back button
		backButton.setPreferredSize(new Dimension(backButtonSize, backButtonSize));
		JLabel button = backButton;
		ImageIcon icon = new ImageIcon("src/main/java/Vue/Resources/Objects/reset.png");
		ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(backButtonSize, backButtonSize, Image.SCALE_DEFAULT));
		button.setIcon(scaledIcon);
		button.setFont(new Font("MonoSpaced", Font.BOLD, tileWidth / 2));
	}
}
