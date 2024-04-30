package Controller;

import Model.Classes.Game.CampaignProgression;
import Model.Classes.Login.LoginManager;
import Model.Classes.Login.UserName;

/**
 * This class has the responsibility to control the login
 *
 * @see LoginManager
 */
public class LoginController {

    /**
     * The current username
     */
    public UserName current_username;

    /**
     * Constructor of the LoginController (default constructor)
     */
    public LoginController() {
        current_username = null;
    }

    /**
     * Check if the user can login
     *
     * @param username String - The username
     * @param password String - The password
     * @return boolean - True if the user can login, false otherwise
     * @author Léonard Amsler (s231715)
     */
    public boolean login(String username, String password) {
        int result = LoginManager.checkLogin(username, password);
        current_username = new UserName(username);
        return result == LoginManager.SUCCESS;
    }

    /**
     * Register a new user
     *
     * @param username String - The username
     * @param password String - The password
     * @return boolean - True if the user can register, false otherwise
     * @author Léonard Amsler (s231715)
     */
    public boolean register(String username, String password) {
        int result = LoginManager.register(username, password);
        current_username = new UserName(username);
        return result == LoginManager.SUCCESS;
    }

    /**
     * Get the current username
     *
     * @return UserName - The current username
     * @author Léonard Amsler (s231715)
     */
    public UserName getCurrentUsername() {
        return current_username;
    }

    /**
     * Logout the user
     *
     * @author Léonard Amsler
     */
    public void logout() {
        current_username = null;
    }

    /**
     * Get the progression of the campaign
     *
     * @return int - The progression of the campaign
     * @author Léonard Amsler
     */
    public int getCampaignProgress() {
        return CampaignProgression.getProgression(current_username.username);
    }

    /**
     * Increment the progression of the campaign
     *
     * @author Léonard Amsler
     */
    public void incrementProgression() {
        CampaignProgression.saveProgression(current_username.username, getCampaignProgress() + 1);
    }
}
