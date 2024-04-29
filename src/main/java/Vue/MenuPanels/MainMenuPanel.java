package Vue.MenuPanels;

import Controller.GameController;
import Controller.LoginController;
import Model.Classes.Level.LevelID;
import Vue.Constants.JComponentsNames;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static Controller.LevelPreparation.preparePlayableLevel;
import static Vue.Game.Game.showPanel;
import static Vue.Utils.ButtonUtil.setButtonTransparent;
import static Vue.Utils.ImageUtil.resizeImage;

/**
 * This class is responsible for the main menu panel
 *
 * @author Léonard Amsler - s231715
 * @author Nathan Gromb - s231674
 * @author Hussein (Adam)
 * @author Hugo Demule
 */
public class MainMenuPanel extends JPanel {
	GameController gameController;
	LoginController loginController;

	/**
	 * Constructor of the main menu panel class
	 *
	 * @param frame           The frame
	 * @param gameController  The game controller
	 * @param loginController The login controller
	 * @author Léonard Amsler - s231715
	 * @author Nathan Gromb - s231674
	 * @author Hussein (Adam)
	 */
	public MainMenuPanel(JFrame frame, GameController gameController, LoginController loginController) {

		this.gameController = gameController;
		this.loginController = loginController;

		int screenWidth = frame.getWidth();
		int screenHeight = frame.getHeight();
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setLayout(new BorderLayout());

		// Background image
		ImageIcon backgroundImage = new ImageIcon(VueFilePaths.BACKGROUND_TILE);
		ImagePanel backgroundPanel = new ImagePanel(backgroundImage.getImage(), gameController.getCurrentTileDimension());
		backgroundPanel.setLayout(new BorderLayout());
		add(backgroundPanel, BorderLayout.CENTER);

		// Top panel
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.L, Style.Padding.L, Style.Padding.L, Style.Padding.L));
		backgroundPanel.add(topPanel, BorderLayout.NORTH);

		// Title label
		JLabel titleLabel = getTitleLabel();
		topPanel.add(titleLabel, BorderLayout.SOUTH);

		// Logout button
		JButton logoutButton = logoutButton(frame);
		topPanel.add(logoutButton, BorderLayout.NORTH);

		// Button panel
		JPanel buttonPanel = createButtonPanel(frame);
		backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

		// Footer panel
		JPanel footerPanel = footerPanel();
		backgroundPanel.add(footerPanel, BorderLayout.SOUTH);

		frame.pack();
	}

	/**
	 * @return the title label
	 * @author Léonard Amsler - s231715
	 * @author Nathan Gromb - s231674
	 * @author Hussein (Adam)
	 */
	private static JLabel getTitleLabel() {
		JLabel titleLabel = new JLabel(JComponentsNames.Label.LASER_MAZE, SwingConstants.CENTER);
		titleLabel.setFont(new Font(Style.Font.COURIER_NEW, Font.BOLD, Style.FontSize.H1));
		titleLabel.setForeground(Color.LIGHT_GRAY);
		return titleLabel;
	}

	/**
	 * @param frame the frame
	 * @return the logout button
	 * @author Léonard Amsler - s231715
	 * @author Nathan Gromb - s231674
	 * @author Hussein (Adam)
	 */
	private JButton logoutButton(JFrame frame) {
		JButton logoutButton = new JButton(JComponentsNames.Label.LOGOUT);
		logoutButton.addActionListener(e -> {
			SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);
			loginController.logout();
			showPanel(frame, JComponentsNames.FrameID.LOGIN);
		});
		logoutButton.addMouseListener(new ButtonHoverHandler());
		logoutButton.setFont(new Font(Style.Font.COURIER_NEW, Font.PLAIN, Style.FontSize.H2));
		logoutButton.setForeground(Color.LIGHT_GRAY);
		setButtonTransparent(logoutButton);
		logoutButton.setHorizontalAlignment(SwingConstants.RIGHT);

		return logoutButton;
	}

	/**
	 * Create the button panel
	 *
	 * @param frame The frame
	 * @return JPanel The button panel
	 * @author Léonard Amsler s231715
	 * @author Nathan Gromb s231674
	 * @author Hussein (Adam)
	 */
	private JPanel createButtonPanel(JFrame frame) {
		// Button panel for the three buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(Style.Grid.MainMenu.ROWS, Style.Grid.MainMenu.COLS, 0, Style.Padding.XL));
		buttonPanel.setOpaque(false); // Make the panel transparent
		//buttonPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, sidePadding, topPadding, sidePadding));

		// Create buttons
		BufferedImage campaignButtonImage = null;
		BufferedImage sandboxButtonImage = null;
		BufferedImage randomButtonImage = null;
		try {
			campaignButtonImage = ImageIO.read(new File(VueFilePaths.CAMPAIGN_LEVELS_BUTTON));
			sandboxButtonImage = ImageIO.read(new File(VueFilePaths.SANDBOX_LEVELS_BUTTON));
			randomButtonImage = ImageIO.read(new File(VueFilePaths.RANDOM_LEVELS_BUTTON));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Resize button image such that they fit the button
		assert campaignButtonImage != null;
		assert sandboxButtonImage != null;
		assert randomButtonImage != null;
		double VScale = 1.5;
		double HScale = 4.5;
		int buttonWidth = (int) (gameController.getCurrentTileDimension().width * HScale);
		int buttonHeight = (int) (gameController.getCurrentTileDimension().height * VScale);
		campaignButtonImage = resizeImage(campaignButtonImage, buttonWidth, buttonHeight);
		sandboxButtonImage = resizeImage(sandboxButtonImage, buttonWidth, buttonHeight);
		randomButtonImage = resizeImage(randomButtonImage, buttonWidth, buttonHeight);

		// Create buttons
		JButton campaignButton = new JButton(new ImageIcon(campaignButtonImage));
		JButton sandboxButton = new JButton(new ImageIcon(sandboxButtonImage));
		JButton randomButton = new JButton(new ImageIcon(randomButtonImage));

		// Add action listeners to buttons
		campaignButton.addActionListener(e -> {
			SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);
			displayCampaignLevels(frame);
		});
		campaignButton.addMouseListener(new ButtonHoverHandler());
		sandboxButton.addActionListener(e -> {
			SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);
			displaySandboxLevels(frame);
		});
		sandboxButton.addMouseListener(new ButtonHoverHandler());
		randomButton.addActionListener(e -> {
			SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);

			gameController.turnOffCampaignGameMode();
			List<LevelID> levelIDs = gameController.getCampaignLevelIDs();
			LevelID randomLevelID = levelIDs.get((int) (Math.random() * levelIDs.size()));
			preparePlayableLevel(randomLevelID, frame, gameController, loginController);
		});
		randomButton.addMouseListener(new ButtonHoverHandler());

		// Set button to transparent

		setButtonTransparent(campaignButton);
		setButtonTransparent(sandboxButton);
		setButtonTransparent(randomButton);

		// Remove the blue line around the button
		campaignButton.setFocusPainted(false);
		sandboxButton.setFocusPainted(false);
		randomButton.setFocusPainted(false);

		// Add buttons to panel
		buttonPanel.add(campaignButton);
		buttonPanel.add(sandboxButton);
		buttonPanel.add(randomButton);

		return buttonPanel;
	}

	/**
	 * Create the footer panel
	 *
	 * @return JPanel The footer panel
	 * @author Léonard Amsler - s231715
	 * @author Nathan Gromb - s231674
	 * @author Hussein (Adam)
	 */
	private JPanel footerPanel() {

		// Initialize footer panel
		JPanel footerPanel = new JPanel();
		footerPanel.setOpaque(false);
		footerPanel.setLayout(new BorderLayout());

		// Add footer label
		JLabel footerLabel = new JLabel(JComponentsNames.Label.FOOTER, SwingConstants.RIGHT);
		footerLabel.setFont(new Font(Style.Font.COURIER_NEW, Font.PLAIN, Style.FontSize.H3));
		footerLabel.setForeground(Color.LIGHT_GRAY);
		footerLabel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.S, Style.Padding.S, Style.Padding.S, Style.Padding.S));
		footerPanel.add(footerLabel, BorderLayout.EAST);

		// Add login label
		JLabel loginLabel = new JLabel(JComponentsNames.Label.LOGGED_AS + loginController.getCurrentUsername().username, SwingConstants.LEFT);
		loginLabel.setFont(new Font(Style.Font.COURIER_NEW, Font.PLAIN, Style.FontSize.H3));
		loginLabel.setForeground(Color.LIGHT_GRAY);
		loginLabel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.S, Style.Padding.S, Style.Padding.S, Style.Padding.S));
		footerPanel.add(loginLabel, BorderLayout.WEST);

		return footerPanel;
	}

	/**
	 * Display the campaign levels
	 *
	 * @param frame The frame
	 * @author Léonard Amsler s231715
	 * @author Nathan Gromb s231674
	 * @author Hussein (Adam)
	 */
	public void displayCampaignLevels(JFrame frame) {
		gameController.turnOnCampaignGameMode();
		CampaignPanel campaignPanel = new CampaignPanel(frame, gameController, loginController);
		frame.add(campaignPanel, JComponentsNames.FrameID.CAMPAIGN_LEVELS);
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				campaignPanel.resize();
			}
		});

		showPanel(frame, JComponentsNames.FrameID.CAMPAIGN_LEVELS);
		frame.pack();
	}

	/**
	 * Display the sandbox levels
	 *
	 * @param frame the frame
	 * @author Hugo Demule
	 */
	private void displaySandboxLevels(JFrame frame) {
		SandboxPanel sandboxPanel = new SandboxPanel(frame, gameController, loginController);
		frame.add(sandboxPanel, JComponentsNames.FrameID.SANDBOX_LEVELS);
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				sandboxPanel.resize();
			}
		});

		showPanel(frame, JComponentsNames.FrameID.SANDBOX_LEVELS);
		frame.pack();
	}
}
