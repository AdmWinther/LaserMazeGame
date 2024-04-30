package Vue.MenuPanels;

import Controller.GameController;
import Controller.LoginController;
import Vue.Constants.JComponentsNames;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;

import javax.swing.*;
import java.awt.*;

import static Vue.Game.Game.showPanel;

public class LoginMenu extends JPanel {

    Thread mainMenuThread;
    GameController gameController;
    LoginController loginController;

    public LoginMenu(JFrame frame, LoginController loginController, GameController gameController) {
        this.gameController = gameController;
        this.loginController = loginController;

        final int H_SCALE = 100;
        final int V_SCALE = 100;
        final int preferredWidth = gameController.getCurrentTileDimension().width * H_SCALE;
        final int preferredHeight = gameController.getCurrentTileDimension().height * V_SCALE;

        //setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        setLayout(new BorderLayout());

        // Background panel
        ImagePanel backgroundPanel = backgroundPanel(gameController);
        this.add(backgroundPanel);

        // Title label
        JPanel titlePanel = titlePanel();
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);

        // Login & Register panel
        JPanel loginRegisterPanel = loginRegisterPanel();

        // Login panel
        LoginPanel loginPanel = loginPanel(frame, loginController, gameController, preferredWidth, preferredHeight);
        loginRegisterPanel.add(loginPanel);

        // Register panel
        RegisterPanel registerPanel = registerPanel(frame, loginController, gameController, preferredWidth, preferredHeight);
        loginRegisterPanel.add(registerPanel);

        backgroundPanel.add(loginRegisterPanel, BorderLayout.CENTER);

        add(backgroundPanel);
    }

    /**
     * Creates the background panel
     *
     * @param gameController the game controller
     * @return the background panel
     * @author Léonard Amsler
     */
    private ImagePanel backgroundPanel(GameController gameController) {
        final ImagePanel backgroundPanel;
        ImageIcon backgroundImage = new ImageIcon(VueFilePaths.BACKGROUND_TILE);
        backgroundPanel = new ImagePanel(backgroundImage.getImage(), new Dimension(gameController.getCurrentTileDimension().width, gameController.getCurrentTileDimension().height));
        backgroundPanel.setLayout(new BorderLayout());
        return backgroundPanel;
    }

    /**
     * Creates the title panel
     *
     * @return the title panel
     * @author Léonard Amsler
     */
    private JPanel titlePanel() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(JComponentsNames.Label.LASER_MAZE, SwingConstants.CENTER);
        titleLabel.setFont(new Font(Style.Font.COURIER_NEW, Font.BOLD, Style.FontSize.H1));
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.XXL, 0, Style.Padding.XXL, 0));
        // Center the title
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    /**
     * Creates the login and register panel
     *
     * @return the login and register panel
     * @author Léonard Amsler
     */
    private JPanel loginRegisterPanel() {
        JPanel loginRegisterPanel = new JPanel();

        // I want a layout that display the two panels one under the other
        // When I resize the window, I want the panels to stay centered and with the same vertical space between them
        loginRegisterPanel.setLayout(new BoxLayout(loginRegisterPanel, BoxLayout.Y_AXIS));
        loginRegisterPanel.setOpaque(false);
        loginRegisterPanel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.XXL, 0, Style.Padding.XXL, 0));
        return loginRegisterPanel;
    }

    /**
     * Creates the login panel
     *
     * @param frame           the frame on which the panel is displayed
     * @param loginController the login controller
     * @param gameController  the game controller
     * @param preferredWidth  the preferred width of the panel
     * @param preferredHeight the preferred height of the panel
     * @return the login panel
     * @author Léonard Amsler
     */
    private LoginPanel loginPanel(JFrame frame, LoginController loginController, GameController gameController, int preferredWidth, int preferredHeight) {
        LoginPanel loginPanel = new LoginPanel(frame, gameController, loginController);
        loginPanel.setOpaque(false);
        loginPanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        return loginPanel;
    }

    /**
     * Creates the register panel
     *
     * @param frame           the frame on which the panel is displayed
     * @param loginController the login controller
     * @param gameController  the game controller
     * @param preferredWidth  the preferred width of the panel
     * @param preferredHeight the preferred height of the panel
     * @return the register panel
     */
    private static RegisterPanel registerPanel(JFrame frame, LoginController loginController, GameController gameController, int preferredWidth, int preferredHeight) {
        RegisterPanel registerPanel = new RegisterPanel(frame, gameController, loginController);
        registerPanel.setOpaque(false);
        registerPanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        return registerPanel;
    }

}

class LoginPanel extends JPanel {

    /**
     * Creates the login panel
     *
     * @param frame           the frame on which the panel is displayed
     * @param gameController  the game controller
     * @param loginController the login controller
     * @author Léonard Amsler
     */
    public LoginPanel(JFrame frame, GameController gameController, LoginController loginController) {
        // Login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setOpaque(false);

        //Inputs panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new GridLayout(Style.Grid.LoginMenu.ROWS, Style.Grid.LoginMenu.COLS));
        inputsPanel.setOpaque(false);

        Dimension resizeDimension = new Dimension(gameController.getCurrentTileDimension().width * 4, (int) (gameController.getCurrentTileDimension().height * 0.5));
        Dimension resizeDimension2 = new Dimension(resizeDimension.width * 2, resizeDimension.height * 2);
        Font resizeFont = new Font(Style.Font.MONOSPACED, Font.BOLD, Style.FontSize.H2);

        // Username label
        JLabel usernameLabel = new JLabel(JComponentsNames.Label.USERNAME);
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        usernameLabel.setFont(resizeFont);
        usernameLabel.setPreferredSize(resizeDimension);
        inputsPanel.add(usernameLabel);

        // Username text field
        JTextField usernameTextField = new JTextField();
        usernameTextField.setFont(resizeFont);
        usernameTextField.setPreferredSize(resizeDimension);
        inputsPanel.add(usernameTextField);

        // Password label
        JLabel passwordLabel = new JLabel(JComponentsNames.Label.PASSWORD);
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        passwordLabel.setFont(resizeFont);
        passwordLabel.setPreferredSize(resizeDimension);
        inputsPanel.add(passwordLabel);

        // Password text field
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setFont(resizeFont);
        passwordTextField.setPreferredSize(resizeDimension);
        inputsPanel.add(passwordTextField);

        // Login button
        JButton loginButton = new JButton(JComponentsNames.Label.LOGIN);
        loginButton.setPreferredSize(resizeDimension2);
        loginButton.setFont(resizeFont);
        loginButton.addActionListener(e -> {
            SoundPlayer.play(SoundPaths.BUTTON_CLICK);

            // Check if the username and password are correct
            boolean success = loginController.login(usernameTextField.getText(), new String(passwordTextField.getPassword()));
            if (success) {
                // Show the main menu
                MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController, loginController);
                frame.add(mainMenuPanel, JComponentsNames.FrameID.MAIN_MENU);
                showPanel(frame, JComponentsNames.FrameID.MAIN_MENU);
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

class RegisterPanel extends JPanel {

    /**
     * Creates the register panel
     *
     * @param frame           the frame on which the panel is displayed
     * @param gameController  the game controller
     * @param loginController the login controller
     * @author Léonard Amlser
     */
    public RegisterPanel(JFrame frame, GameController gameController, LoginController loginController) {
        // Register panel
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setOpaque(false);

        //Inputs panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new GridLayout(3, 2));
        inputsPanel.setOpaque(false);

        Dimension resizeDimension = new Dimension(gameController.getCurrentTileDimension().width * 4, (int) (gameController.getCurrentTileDimension().height * 0.5));
        Dimension resizeDimension2 = new Dimension(resizeDimension.width * 2, resizeDimension.height * 2);
        Font resizeFont = new Font(Style.Font.MONOSPACED, Font.BOLD, Style.FontSize.H2);

        // Username label
        JLabel usernameLabel = new JLabel(JComponentsNames.Label.USERNAME);
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        usernameLabel.setFont(resizeFont);
        usernameLabel.setPreferredSize(resizeDimension);
        inputsPanel.add(usernameLabel);

        // Username text field
        JTextField usernameTextField = new JTextField();
        usernameTextField.setFont(resizeFont);
        usernameTextField.setPreferredSize(resizeDimension);
        inputsPanel.add(usernameTextField);

        // Password label
        JLabel passwordLabel = new JLabel(JComponentsNames.Label.PASSWORD);
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        passwordLabel.setFont(resizeFont);
        passwordLabel.setPreferredSize(resizeDimension);
        inputsPanel.add(passwordLabel);

        // Password text field
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setFont(resizeFont);
        passwordTextField.setPreferredSize(resizeDimension);
        inputsPanel.add(passwordTextField);

        // Confirm Password label
        JLabel confirmPasswordLabel = new JLabel(JComponentsNames.Label.CONFIRM_PASSWORD);
        confirmPasswordLabel.setForeground(Color.LIGHT_GRAY);
        confirmPasswordLabel.setFont(resizeFont);
        confirmPasswordLabel.setPreferredSize(resizeDimension);
        inputsPanel.add(confirmPasswordLabel);

        // Confirm Password text field
        JPasswordField confirmPasswordTextField = new JPasswordField();
        confirmPasswordTextField.setFont(resizeFont);
        confirmPasswordTextField.setPreferredSize(resizeDimension);
        inputsPanel.add(confirmPasswordTextField);

        // Register button
        JButton registerButton = new JButton(JComponentsNames.Label.REGISTER);
        registerButton.setPreferredSize(resizeDimension2);
        registerButton.setFont(resizeFont);
        registerButton.addActionListener(e -> {
            SoundPlayer.play(SoundPaths.BUTTON_CLICK);

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
                frame.add(mainMenuPanel, JComponentsNames.FrameID.MAIN_MENU);
                showPanel(frame, JComponentsNames.FrameID.MAIN_MENU);
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

