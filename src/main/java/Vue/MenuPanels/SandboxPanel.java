package Vue.MenuPanels;

import Controller.GameController;
import Controller.LevelPreparation;
import Controller.LoginController;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Model.Classes.Utils.DataWriter;
import Vue.Constants.JComponentsNames;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Game.Game;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;
import Vue.Utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


/**
 * This class has the responsibility to display the sandbox levels list.
 *
 * @author Hugo Demule
 */
public class SandboxPanel extends LevelMenuPanel {

	/**
	 * Constructor of the campaign panel class
	 *
	 * @param frame          The frame
	 * @param gameController The game controller
	 * @author Hugo Demule
	 */
	public SandboxPanel(JFrame frame, GameController gameController, LoginController loginController) {
		super(frame, gameController, loginController);
	}

	/**
	 * Gets the level buttons
	 *
	 * @return JScrollPane The level buttons
	 * @author Hugo Demule
	 */
	@Override
	protected JScrollPane getLevelButtonsList() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel outerPanel = new JPanel(new GridBagLayout());
		outerPanel.setOpaque(false);
		outerPanel.add(panel, new GridBagConstraints());

		JScrollPane sandboxLevelsList = initializeSandboxLevelsList(outerPanel);

		// Load images
		BufferedImage backgroundLevelList = getImage(VueFilePaths.SANDBOX_LIST_ITEM_BACKGROUND);
		BufferedImage playButtonImage = getImage(VueFilePaths.PLAY_BUTTON_ICON);
		BufferedImage editButtonImage = getImage(VueFilePaths.EDIT_BUTTON_ICON);
		BufferedImage deleteButtonImage = getImage(VueFilePaths.DELETE_BUTTON_ICON);
		BufferedImage newLevelButtonImage = getImage(VueFilePaths.SANDBOX_LIST_NEW_LEVEL_BACKGROUND);

		// Resize images
		final double ICON_RESIZE_FACTOR = 0.5;
		final int LONG_BUTTON_SCALE_FACTOR = 8;
		final double RESIZE_FACTOR = 1.5;

		backgroundLevelList = ImageUtil.resizeImage(backgroundLevelList, (int) (tileWidth * LONG_BUTTON_SCALE_FACTOR * RESIZE_FACTOR), (int) (tileHeight * RESIZE_FACTOR));
		newLevelButtonImage = ImageUtil.resizeImage(newLevelButtonImage, (int) (tileWidth * LONG_BUTTON_SCALE_FACTOR * RESIZE_FACTOR), (int) (tileHeight * RESIZE_FACTOR));
		playButtonImage = ImageUtil.resizeImage(playButtonImage, (int) (tileWidth * ICON_RESIZE_FACTOR * RESIZE_FACTOR), (int) (tileHeight * ICON_RESIZE_FACTOR * RESIZE_FACTOR));
		editButtonImage = ImageUtil.resizeImage(editButtonImage, (int) (tileWidth * ICON_RESIZE_FACTOR * RESIZE_FACTOR), (int) (tileHeight * ICON_RESIZE_FACTOR * RESIZE_FACTOR));
		deleteButtonImage = ImageUtil.resizeImage(deleteButtonImage, (int) (tileWidth * ICON_RESIZE_FACTOR * RESIZE_FACTOR), (int) (tileHeight * ICON_RESIZE_FACTOR * RESIZE_FACTOR));

		// Load level IDs
		List<LevelID> levelIDs = DataReader.readSandboxLevelIDs();

		// Add new level button

		ImageIcon scaledNewLevelIcon = new ImageIcon(newLevelButtonImage);
		JPanel newLevelButton = levelPanel(scaledNewLevelIcon);
		newLevelButton.setPreferredSize(new Dimension((int) (tileWidth * LONG_BUTTON_SCALE_FACTOR * RESIZE_FACTOR), (int) (tileHeight * RESIZE_FACTOR)));
		newLevelButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);
				LevelPreparation.prepareNewEditableLevel(frame, gameController);
			}
		});
		newLevelButton.addMouseListener(new ButtonHoverHandler());
		panel.add(newLevelButton);

		// Add level buttons
		for (LevelID levelID : levelIDs) {

			assert backgroundLevelList != null;
			ImageIcon scaledIcon = new ImageIcon(backgroundLevelList);

			JPanel levelPanel = levelPanel(scaledIcon);
			levelPanel.setPreferredSize(new Dimension((int) (tileWidth * LONG_BUTTON_SCALE_FACTOR * RESIZE_FACTOR), (int) (tileHeight * RESIZE_FACTOR)));

			JPanel levelNameContainer = levelNameContainer(levelID);

			JPanel buttonsContainer = buttonsContainer(playButtonImage, editButtonImage, deleteButtonImage, levelID);

			// Create a wrapper panel with vertical BoxLayout to center buttonsContainer vertically
			JPanel verticalWrapper = verticalWrapper(buttonsContainer);

			levelPanel.add(verticalWrapper, BorderLayout.EAST);
			levelPanel.add(levelNameContainer, BorderLayout.WEST);
			panel.add(levelPanel);
		}

		return sandboxLevelsList;
	}


	/**
	 * Initializes the sandbox levels list
	 *
	 * @param outerPanel The outer panel
	 * @return JScrollPane The sandbox levels list
	 * @author Hugo Demule
	 */
	private JScrollPane initializeSandboxLevelsList(JPanel outerPanel) {
		JScrollPane sandboxLevelsList = new JScrollPane(outerPanel);
		sandboxLevelsList.setOpaque(false); // Make the panel transparent
		sandboxLevelsList.getViewport().setOpaque(false);
		sandboxLevelsList.getVerticalScrollBar().setOpaque(false);
		sandboxLevelsList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		sandboxLevelsList.setBorder(BorderFactory.createEmptyBorder(Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL));
		return sandboxLevelsList;
	}

	/**
	 * Creates a level panel
	 *
	 * @param scaledIcon The scaled icon
	 * @return JPanel The level panel
	 * @author Hugo Demule
	 */
	private JPanel levelPanel(ImageIcon scaledIcon) {
		JPanel levelPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(scaledIcon.getImage(), 0, 0, null);
			}
		};
		levelPanel.setLayout(new BorderLayout());
		return levelPanel;
	}

	/**
	 * Creates a container for the level name
	 *
	 * @param levelID The level ID
	 * @return JPanel The level name container panel
	 * @author Hugo Demule
	 */
	private static JPanel levelNameContainer(LevelID levelID) {
		JPanel levelNameContainer = new JPanel();
		levelNameContainer.setOpaque(false);
		levelNameContainer.setLayout(new BoxLayout(levelNameContainer, BoxLayout.X_AXIS));
		levelNameContainer.setBorder(BorderFactory.createEmptyBorder(0, Style.Padding.L, 0, 0));

		// add a label with the level name
		String levelNameString = "";
		try {
			levelNameString = DataReader.readLevelIDName(levelID);
			final int LENGTH_LIMIT = 35;
			if (levelNameString.length() > LENGTH_LIMIT)
				levelNameString = levelNameString.substring(0, LENGTH_LIMIT) + "...";
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel levelName = new JLabel(levelNameString);
		levelName.setFont(new Font(Style.Font.MONOSPACED, Font.BOLD, Style.FontSize.H3));
		levelNameContainer.add(levelName);
		return levelNameContainer;
	}

	/**
	 * Creates a container for the buttons
	 *
	 * @param playButtonImage   The image of the play button
	 * @param editButtonImage   The image of the edit button
	 * @param deleteButtonImage The image of the delete button
	 * @param levelID           The level ID
	 * @return JPanel The buttons container panel
	 * @author Hugo Demule
	 */
	private JPanel buttonsContainer(BufferedImage playButtonImage, BufferedImage editButtonImage, BufferedImage deleteButtonImage, LevelID levelID) {
		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setOpaque(false);
		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(Style.Padding.S, 0, 0, Style.Padding.M));

		// add three buttons stacked to the right of the levelPanel with three different images
		JPanel playButton = playButton(playButtonImage, levelID);

		JPanel editButton = editButton(editButtonImage, levelID);

		JPanel deleteButton = deleteButton(deleteButtonImage, levelID);

		deleteButton.addMouseListener(new ButtonHoverHandler());

		buttonsContainer.add(playButton);
		buttonsContainer.add(editButton);
		buttonsContainer.add(deleteButton);
		return buttonsContainer;
	}

	/**
	 * Creates a wrapper panel with vertical BoxLayout to center buttonsContainer vertically
	 *
	 * @param buttonsContainer The buttons container panel
	 * @return JPanel The vertical wrapper panel
	 * @author Hugo Demule
	 */
	private JPanel verticalWrapper(JPanel buttonsContainer) {
		JPanel verticalWrapper = new JPanel();
		verticalWrapper.setLayout(new BoxLayout(verticalWrapper, BoxLayout.Y_AXIS));
		verticalWrapper.setOpaque(false);
		verticalWrapper.add(Box.createVerticalGlue()); // Spacer to push the content to the center
		verticalWrapper.add(buttonsContainer);
		verticalWrapper.add(Box.createVerticalGlue()); // Spacer to push the content to the center
		return verticalWrapper;
	}

	/**
	 * Creates a play button
	 *
	 * @param playButtonImage The image of the play button
	 * @param levelID         The level ID
	 * @return JPanel The play button panel
	 * @author Hugo Demule
	 */
	private JPanel playButton(BufferedImage playButtonImage, LevelID levelID) {
		JPanel playButton = new JPanel();
		playButton.setOpaque(false);
		playButton.add(new JLabel(new ImageIcon(playButtonImage)));
		playButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);
				LevelPreparation.preparePlayableLevel(levelID, frame, gameController, loginController);
			}
		});
		playButton.addMouseListener(new ButtonHoverHandler());
		return playButton;
	}

	/**
	 * Creates an edit button
	 *
	 * @param editButtonImage The image of the edit button
	 * @param levelID         The level ID
	 * @return JPanel The edit button panel
	 * @author Hugo Demule
	 */
	private JPanel editButton(BufferedImage editButtonImage, LevelID levelID) {
		JPanel editButton = new JPanel();
		editButton.setOpaque(false);
		editButton.add(new JLabel(new ImageIcon(editButtonImage)));
		editButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);
				LevelPreparation.prepareEditableLevel(levelID, frame, gameController);
			}
		});
		editButton.addMouseListener(new ButtonHoverHandler());
		return editButton;
	}

	/**
	 * Creates a delete button
	 *
	 * @param deleteButtonImage The image of the delete button
	 * @param levelID           The level ID
	 * @return JPanel The delete button panel
	 * @author Hugo Demule
	 */
	private JPanel deleteButton(BufferedImage deleteButtonImage, LevelID levelID) {
		JPanel deleteButton = new JPanel();
		deleteButton.setOpaque(false);
		deleteButton.add(new JLabel(new ImageIcon(deleteButtonImage)));
		deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);
				try {
					DataWriter.delete(levelID);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				refresh();
			}
		});
		return deleteButton;
	}

	/**
	 * Refreshes the sandbox panel
	 *
	 * @author Hugo Demule
	 */
	private void refresh() {
		frame.add(new SandboxPanel(frame, gameController, loginController), JComponentsNames.FrameID.SANDBOX_LEVELS);
		frame.getContentPane().remove(this);
		frame.revalidate();
		Game.showPanel(frame, JComponentsNames.FrameID.SANDBOX_LEVELS);
		frame.repaint();
	}
}
