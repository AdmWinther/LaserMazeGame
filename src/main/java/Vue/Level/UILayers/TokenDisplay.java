package Vue.Level.UILayers;

import Controller.LevelController;
import Model.Classes.Token.*;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;
import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;
import Vue.Utils.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a display of tokens in the level.
 *
 * @author Nathan Gromb
 * @see TokensUI
 * @see InventoryUI
 */
public abstract class TokenDisplay implements Drawable {
	LevelController levelController;
	Map<String, BufferedImage> tokenImages = new HashMap<>();
	Map<Pair<String, Orientation>, BufferedImage> orientedTokenImages;
	Map<Token, Rectangle2D> rectangles;
	LevelPanel levelPanel;

	private Pair<Token, Position> draggedToken;

	public TokenDisplay(LevelPanel levelPanel, LevelController levelController) {
		this.levelController = levelController;
		this.levelPanel = levelPanel;

		this.orientedTokenImages = new HashMap<>();
		this.rectangles = new HashMap<>();

		this.draggedToken = null;
		setTokenImages();
	}

	/**
	 * Loads the images for the tokens.
	 *
	 * @author Léonard Amsler
	 */
	private void setTokenImages() {
		try {
			// 1. Load the images for the tokens
			BufferedImage beamerImageUP = readImage("/Tokens/lasergun_UP.png");
			BufferedImage beamerImageDOWN = readImage("/Tokens/lasergun_DOWN.png");
			BufferedImage beamerImageLEFT = readImage("/Tokens/lasergun_LEFT.png");
			BufferedImage beamerImageRIGHT = readImage("/Tokens/lasergun_RIGHT.png");

			BufferedImage blockerImage = readImage("/Tokens/block.png");

			BufferedImage mirrorImageUP = readImage("/Tokens/mirror_UP.png");
			BufferedImage mirrorImageDOWN = readImage("/Tokens/mirror_DOWN.png");
			BufferedImage mirrorImageLEFT = readImage("/Tokens/mirror_LEFT.png");
			BufferedImage mirrorImageRIGHT = readImage("/Tokens/mirror_RIGHT.png");

			BufferedImage doubleMirrorImageUPDOWN = readImage("/Tokens/doubleMirror_UD.png");
			BufferedImage doubleMirrorImageLEFTRIGHT = readImage("/Tokens/doubleMirror_RL.png");

			BufferedImage targetImageUP = readImage("/Tokens/target_UP.png");
			BufferedImage targetImageDOWN = readImage("/Tokens/target_DOWN.png");
			BufferedImage targetImageLEFT = readImage("/Tokens/target_LEFT.png");
			BufferedImage targetImageRIGHT = readImage("/Tokens/target_RIGHT.png");

			BufferedImage splitterImageLEFTRIGHT = readImage("/Tokens/splitter_RL.png");
			BufferedImage splitterImageUPDOWN = readImage("/Tokens/splitter_UD.png");

			// 2. Store the images in the maps
			String beamerClassName = LaserGun.class.getSimpleName();
			putOrientedTokenImage(beamerImageUP, beamerImageDOWN, beamerImageLEFT, beamerImageRIGHT, beamerClassName);

			String blockerClassName = Block.class.getSimpleName();
			tokenImages.put(blockerClassName, blockerImage);

			String mirrorClassName = OneSidedMirror.class.getSimpleName();
			putOrientedTokenImage(mirrorImageUP, mirrorImageDOWN, mirrorImageLEFT, mirrorImageRIGHT, mirrorClassName);

			String doubleMirrorClassName = DoubleSidedMirror.class.getSimpleName();
			putOrientedTokenImage(doubleMirrorImageUPDOWN, doubleMirrorImageUPDOWN, doubleMirrorImageLEFTRIGHT, doubleMirrorImageLEFTRIGHT, doubleMirrorClassName);

			String targetClassName = Target.class.getSimpleName();
			putOrientedTokenImage(targetImageUP, targetImageDOWN, targetImageLEFT, targetImageRIGHT, targetClassName);

			String splitterClassName = Splitter.class.getSimpleName();
			putOrientedTokenImage(splitterImageUPDOWN, splitterImageUPDOWN, splitterImageLEFTRIGHT, splitterImageLEFTRIGHT, splitterClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to read an image from the resources.
	 *
	 * @author Nathan Gromb
	 */
	private BufferedImage readImage(String path) throws Exception {
		return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
	}

	/**
	 * Utility method to store the oriented token images in the map.
	 *
	 * @author Nathan Gromb
	 */
	private void putOrientedTokenImage(BufferedImage beamerImageUP, BufferedImage beamerImageDOWN, BufferedImage beamerImageLEFT, BufferedImage beamerImageRIGHT, String beamerClassName) {
		orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.UP), beamerImageUP);
		orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.DOWN), beamerImageDOWN);
		orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.LEFT), beamerImageLEFT);
		orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.RIGHT), beamerImageRIGHT);
	}

	/**
	 * Sets the token being dragged and its position.
	 *
	 * @param token The token being dragged
	 * @param pos   The position of the mouse
	 * @author Nathan Gromb
	 */
	public void setDraggedToken(Token token, Position pos) {
		draggedToken = new Pair<>(token, pos);
	}

	/**
	 * Resets the dragged token to null.
	 *
	 * @author Nathan Gromb
	 */
	public void resetDraggedToken() {
		draggedToken = null;
	}

	/**
	 * Returns the position of the token, overriding the real position if the token is being dragged.
	 *
	 * @author Nathan Gromb
	 */
	public Position getTokenPosition(Token token, Position realPos) {
		if (isDraggedToken(token)) {
			int x = draggedToken.second().x();
			int y = draggedToken.second().y();

			return Position.of(x - levelPanel.tileWidth / 2, y - levelPanel.tileHeight / 2);
		}

		return Position.of(realPos.x(), realPos.y());
	}

	/**
	 * Returns whether the token is the one being dragged.
	 *
	 * @author Nathan Gromb
	 */
	private boolean isDraggedToken(Token token) {
		return draggedToken != null && draggedToken.first().id() == token.id();
	}

	/**
	 * Returns the token at the given position.
	 *
	 * @author Léonard Amsler
	 */
	public Token getTokenAtMousePos(Position pos) {
		for (Map.Entry<Token, Rectangle2D> entry : rectangles.entrySet()) {
			Rectangle2D rectangle = entry.getValue();
			if (rectangle.contains(pos.x(), pos.y())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Draws a token at the given position.
	 *
	 * @author Léonard Amsler
	 */
	public void drawToken(Graphics2D g2d, Token token, Position pos, int tileWidth, int tileHeight) {

		String tokenClassName = token.type();
		boolean isOrientable = token instanceof OrientedToken;

		BufferedImage tokenImage;
		if (isOrientable) {
			Orientation orientation = ((OrientedToken) token).getOrientation();
			tokenImage = orientedTokenImages.get(new Pair<>(tokenClassName, orientation));
		} else {
			tokenImage = tokenImages.get(tokenClassName);
		}

		g2d.drawImage(tokenImage, pos.x(), pos.y(), tileWidth, tileHeight, null);
	}
}
