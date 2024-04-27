package Vue.LoginMenu;

import Controller.GameController;
import Controller.LoginController;
import Vue.Handlers.ButtonHoverHandler;
import Vue.MainMenu.ImagePanel;
import Vue.MainMenu.MainMenuPanel;
import Vue.SoundEffects.Sound;

import javax.swing.*;
import java.awt.*;

import static Vue.MainMenu.LevelPreparation.showPanel;

public class LoginMenu extends JPanel {

	Thread mainMenuThread;
	GameController gameController;
	LoginController loginController;

	public LoginMenu(JFrame frame, LoginController loginController, GameController gameController) {
		this.gameController = gameController;
		this.loginController = loginController;

		int screenWidth = frame.getWidth();
		int screenHeight = frame.getHeight();
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setLayout(new BorderLayout());

		final int panelBorder = 46;
		final int preferredWidth = 300;
		final int preferredHeight = 200;

		// Background panel
		ImageIcon backgroundImage = new ImageIcon("src/main/java/Vue/Resources/Tiles/background.png");
		ImagePanel backgroundPanel = new ImagePanel(backgroundImage.getImage());
		backgroundPanel.setLayout(new BorderLayout());
		add(backgroundPanel);

		// Title label
		JLabel titleLabel = new JLabel("Laser Maze", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		titleLabel.setForeground(Color.LIGHT_GRAY);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(panelBorder, 0, panelBorder, 0));
		// Center the title
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titlePanel.setOpaque(false);
		titlePanel.add(titleLabel);
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);

		// Login & Register panel
		JPanel loginRegisterPanel = new JPanel();
		// I want a layout that display the two panels one under the other
		// When I resize the window, I want the panels to stay centered and with the same vertical space between them
		loginRegisterPanel.setLayout(new BoxLayout(loginRegisterPanel, BoxLayout.Y_AXIS));
		loginRegisterPanel.setOpaque(false);
		loginRegisterPanel.setBorder(BorderFactory.createEmptyBorder(panelBorder, 0, panelBorder, 0));

		// Login panel
		LoginPanel loginPanel = new LoginPanel(frame, gameController, loginController);
		loginPanel.setOpaque(false);
		loginPanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		loginRegisterPanel.add(loginPanel);

		// Add some space between the panels

		// Register panel
		registerPanel registerPanel = new registerPanel(frame, gameController, loginController);
		registerPanel.setOpaque(false);
		registerPanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		loginRegisterPanel.add(registerPanel);

		backgroundPanel.add(loginRegisterPanel, BorderLayout.CENTER);

		frame.setMinimumSize(new Dimension(800, 600));

		add(backgroundPanel);
	}
}

class LoginPanel extends JPanel {
	public LoginPanel(JFrame frame, GameController gameController, LoginController loginController) {
		// Login panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		loginPanel.setOpaque(false);

		//Inputs panel
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new GridLayout(2, 2));
		inputsPanel.setOpaque(false);

		// Username label
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setForeground(Color.LIGHT_GRAY);
		inputsPanel.add(usernameLabel);

		// Username text field
		JTextField usernameTextField = new JTextField();
		inputsPanel.add(usernameTextField);

		// Password label
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(Color.LIGHT_GRAY);
		inputsPanel.add(passwordLabel);

		// Password text field
		JPasswordField passwordTextField = new JPasswordField();
		inputsPanel.add(passwordTextField);

		// Login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(e -> {
			Sound.playButtonSound();

			// Check if the username and password are correct
			boolean success = loginController.login(usernameTextField.getText(), new String(passwordTextField.getPassword()));
			if (success) {
				// Show the main menu
				MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController, loginController);
				frame.add(mainMenuPanel, "MainMenu");
				showPanel(frame, "MainMenu");
			} else {
				// Show an error message
				JOptionPane.showMessageDialog(frame, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		loginButton.addMouseListener(new ButtonHoverHandler());

		loginPanel.add(inputsPanel);

		// Place the button in the center
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setOpaque(false);
		buttonPanel.add(loginButton);
		loginPanel.add(buttonPanel);

		add(loginPanel);
	}
}

class registerPanel extends JPanel {
	public registerPanel(JFrame frame, GameController gameController, LoginController loginController) {
		// Register panel
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
		registerPanel.setOpaque(false);

		//Inputs panel
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new GridLayout(3, 2));
		inputsPanel.setOpaque(false);

		// Username label
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setForeground(Color.LIGHT_GRAY);
		inputsPanel.add(usernameLabel);

		// Username text field
		JTextField usernameTextField = new JTextField();
		inputsPanel.add(usernameTextField);

		// Password label
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(Color.LIGHT_GRAY);
		inputsPanel.add(passwordLabel);

		// Password text field
		JPasswordField passwordTextField = new JPasswordField();
		inputsPanel.add(passwordTextField);

		// Confirm Password label
		JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordLabel.setForeground(Color.LIGHT_GRAY);
		inputsPanel.add(confirmPasswordLabel);

		// Confirm Password text field
		JPasswordField confirmPasswordTextField = new JPasswordField();
		inputsPanel.add(confirmPasswordTextField);

		// Register button
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(e -> {
			Sound.playButtonSound();

			// Check if the password and confirm password are the same
			if (!new String(passwordTextField.getPassword()).equals(new String(confirmPasswordTextField.getPassword()))) {
				// Show an error message
				JOptionPane.showMessageDialog(frame, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Check that the username is not empty and the password is not empty
			if (usernameTextField.getText().equals("") || new String(passwordTextField.getPassword()).equals("")) {
				// Show an error message
				JOptionPane.showMessageDialog(frame, "Username or password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Register the user
			boolean result = loginController.register(usernameTextField.getText(), new String(passwordTextField.getPassword()));
			if (result) {
				// Show the main menu
				MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController, loginController);
				frame.add(mainMenuPanel, "MainMenu");
				showPanel(frame, "MainMenu");
			} else {
				// Show an error message
				JOptionPane.showMessageDialog(frame, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		registerButton.addMouseListener(new ButtonHoverHandler());

		registerPanel.add(inputsPanel);

		// Place the button in the center
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setOpaque(false);
		buttonPanel.add(registerButton);
		registerPanel.add(buttonPanel);

		add(registerPanel);
	}
}

