package Controller;

import Model.Classes.Game.CampaignProgression;
import Model.Classes.Login.LoginManager;
import Model.Classes.Login.UserName;

public class LoginController {

    public UserName current_username;

    public LoginController() {
    }

    public boolean login(String username, String password) {
        int result = LoginManager.checkLogin(username, password);
        current_username = new UserName(username);
        return result == LoginManager.SUCCESS;
    }

    public boolean register(String username, String password) {
        int result = LoginManager.register(username, password);
        current_username = new UserName(username);
        return result == LoginManager.SUCCESS;
    }

    public UserName getCurrentUsername() {
        return current_username;
    }

    public void logout() {
        current_username = null;
    }

    public int getCampaignProgress() {
        return CampaignProgression.getProgression(current_username.username);
    }

    public void incrementProgression() {
        CampaignProgression.saveProgression(current_username.username, getCampaignProgress() + 1);
    }
}
