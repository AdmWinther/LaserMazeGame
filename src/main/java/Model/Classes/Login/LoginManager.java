package Model.Classes.Login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static int register(String username, String password) {
        return register(new UserName(username), new Password(password.hashCode()));
    }

    public static int checkLogin(String username, String password) {
        return checkLogin(new UserName(username), new Password(password.hashCode()));
    }

    private static int checkLogin(UserName username, Password password) {
        // Get the file, read the file, check if the username and password match
        int output = INCORRECT_LOGIN;

        try (InputStream is = new FileInputStream(path.toString())) {

            byte[] buffer = new byte[is.available()];

            is.read(buffer);

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

