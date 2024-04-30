package Model.Classes.Login;

import Model.Classes.Game.CampaignProgression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class that manages the login and registration of users
 * It reads and writes to the Login.txt file
 * The Login.txt file contains the username and password of the users
 * The password is hashed
 * The Login.txt file is in the format:
 * username:hashed_password
 * username:hashed_password
 * username:hashed_password
 * @see CampaignProgression
 * @author Leonard Amsler
 */
public class LoginManager {

    public static final int SUCCESS = 0;
    public static final int USER_ALREADY_EXISTS = -1;
    public static final int INCORRECT_LOGIN = -2;
    public static final int INCORRECT_REGISTER = -3;

    private final static Path path = Paths.get("src/main/java/Model/Resources/Login.txt");
    // Login.txt example:
    // user1:03924758t5134567198
    // user2:3857820374534652374
    // user3:1234567890123456789

    /**
     * An overload for the register method, while the username and password are strings
     * @author Leonard Amsler
     */
    public static int register(String username, String password) {
        return register(new UserName(username), new Password(password.hashCode()));
    }

    /**
     * An overload for the checkLogin method, while the username and password are strings
     * @author Leonard Amsler
     */
    public static int checkLogin(String username, String password) {
        return checkLogin(new UserName(username), new Password(password.hashCode()));
    }

    /**
     * Register a new user to the Login.txt file
     * @param username of type UserName
     * @param password of type Password
     * @return SUCCESS if the user was successfully registered.
     * @author Leonard Amsler
     */
    private static int checkLogin(UserName username, Password password) {
        // Get the file, read the file, check if the username and password match
        int output = INCORRECT_LOGIN;

        try (InputStream inputStream = new FileInputStream(path.toString())) {

            byte[] buffer = new byte[inputStream.available()];

            inputStream.read(buffer);

            String fileContent = new String(buffer);

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts[0].equals(username.username)) {
                    if (Integer.parseInt(parts[1]) == password.hashed_password) {
                        output = SUCCESS;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * Register a new user to the Login.txt file
     * @param username of type UserName
     * @param password of type Password
     * @return SUCCESS if the user was successfully registered.
     * @author Leonard Amsler
     */
    private static int register(UserName username, Password password) {
        int output = INCORRECT_REGISTER;

        try (InputStream is = new FileInputStream(path.toString())) {

            byte[] buffer = new byte[is.available()];

            is.read(buffer);

            String fileContent = new String(buffer);

            // Check if the username already exists
            String[] lines = fileContent.split("\n");
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts[0].equals(username.username)) {
                    output = USER_ALREADY_EXISTS;
                    return output;
                }
            }

            fileContent += username.username + ":" + password.hashed_password + "\n";

            // Set the initial progression to 1
            CampaignProgression.saveProgression(username.username,  1);

            is.close();

            FileOutputStream os = new FileOutputStream(path.toString());
            os.write(fileContent.getBytes());
            os.close();

            output = SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }
}

