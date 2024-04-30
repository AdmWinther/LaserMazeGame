package Vue.Utils;

import Controller.GameController;
import Controller.LoginController;
import Vue.Constants.JComponentsNames;
import Vue.MenuPanels.CampaignMenuPanel;
import Vue.MenuPanels.LoginMenuPanel;
import Vue.MenuPanels.MainMenuPanel;
import Vue.MenuPanels.SandboxPanel;

import javax.swing.*;
import java.awt.*;

public class FrameUtil {

    /**
     * Shows a panel
     *
     * @param frame     The frame
     * @param panelName The panel name
     * @author Léonard Amsler - s231715
     */
    public static void showPanel(JFrame frame, String panelName) {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), panelName);
    }

    public static void displayMainMenu(JFrame frame) {
        showPanel(frame, JComponentsNames.FrameID.MAIN_MENU);
        frame.pack();
    }

    public static void displaySandboxMenu(JFrame frame) {
        showPanel(frame, JComponentsNames.FrameID.SANDBOX_MENU);
        frame.pack();
    }

    public static void displayCampaignMenu(JFrame frame) {
        showPanel(frame, JComponentsNames.FrameID.CAMPAIGN_MENU);
        frame.pack();
    }

    public static void displayLoginMenu(JFrame frame) {
        showPanel(frame, JComponentsNames.FrameID.LOGIN);
        frame.pack();
    }

    public static void displayLevel(JFrame frame, String frameID) {
        showPanel(frame, frameID);
        frame.pack();
    }

    public static void addLoginMenu(JFrame frame, LoginController loginController, GameController gameController) {
        LoginMenuPanel loginMenu = new LoginMenuPanel(frame, loginController, gameController);
        loginMenu.setName(JComponentsNames.FrameID.LOGIN);
        frame.add(loginMenu, JComponentsNames.FrameID.LOGIN);
    }

    public static void addMainMenu(JFrame frame, GameController gameController, LoginController loginController) {
        MainMenuPanel mainMenu = new MainMenuPanel(frame, gameController, loginController);
        mainMenu.setName(JComponentsNames.FrameID.MAIN_MENU);
        frame.add(mainMenu, JComponentsNames.FrameID.MAIN_MENU);
    }

    private static void addCampaignMenu(JFrame frame, GameController gameController, LoginController loginController) {
        CampaignMenuPanel campaignMenu = new CampaignMenuPanel(frame, gameController, loginController);
        campaignMenu.setName(JComponentsNames.FrameID.CAMPAIGN_MENU);
        frame.add(campaignMenu, JComponentsNames.FrameID.CAMPAIGN_MENU);
    }

    private static void addSandboxMenu(JFrame frame, GameController gameController, LoginController loginController) {
        SandboxPanel sandboxPanel = new SandboxPanel(frame, gameController, loginController);
        sandboxPanel.setName(JComponentsNames.FrameID.SANDBOX_MENU);
        frame.add(sandboxPanel, JComponentsNames.FrameID.SANDBOX_MENU);
    }

    public static void addLevelPanel(JFrame frame, JPanel levelPanel, String panelName) {
        levelPanel.setName(panelName);
        frame.add(levelPanel, panelName);
    }

    public static void createMainMenuIfNotExists(JFrame frame, GameController gameController, LoginController loginController) {
        if (!containsPanel(frame, JComponentsNames.FrameID.MAIN_MENU)) {
            addMainMenu(frame, gameController, loginController);
        }
    }

    public static void createLoginMenuIfNotExists(JFrame frame, LoginController loginController, GameController gameController) {
        if (!containsPanel(frame, JComponentsNames.FrameID.LOGIN)) {
            addLoginMenu(frame, loginController, gameController);
        }
    }

    public static void createCampaignMenuIfNotExists(JFrame frame, GameController gameController, LoginController loginController) {
        if (!containsPanel(frame, JComponentsNames.FrameID.CAMPAIGN_MENU)) {
            addCampaignMenu(frame, gameController, loginController);
        }
    }

    public static boolean containsPanel(JFrame frame, String panelName) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component.getName().equals(panelName)) {
                return true;
            }
        }
        return false;
    }

    public static void createSandboxMenuIfNotExists(JFrame frame, GameController gameController, LoginController loginController) {
        if (!containsPanel(frame, JComponentsNames.FrameID.SANDBOX_MENU)) {
            addSandboxMenu(frame, gameController, loginController);
        }
    }


    public static void removeLevel(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component.getName().equals(JComponentsNames.FrameID.LEVEL)) {
                frame.remove(component);
            }
        }
    }

    public static void removeSandBoxPanel(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component.getName().equals(JComponentsNames.FrameID.SANDBOX_MENU)) {
                frame.remove(component);
            }
        }
    }

    public static void refreshSandboxMenu(JFrame frame, GameController gameController, LoginController loginController) {
        removeSandBoxPanel(frame);
        createSandboxMenuIfNotExists(frame, gameController, loginController);
    }

    public static void refreshCampaignMenu(JFrame frame, GameController gameController, LoginController loginController) {
        removeCampaignMenu(frame);
        createCampaignMenuIfNotExists(frame, gameController, loginController);
    }

    public static void removeMainMenu(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component.getName().equals(JComponentsNames.FrameID.MAIN_MENU)) {
                frame.remove(component);
            }
        }
    }

    public static void removeCampaignMenu(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component.getName().equals(JComponentsNames.FrameID.CAMPAIGN_MENU)) {
                frame.remove(component);
            }
        }
    }
}
