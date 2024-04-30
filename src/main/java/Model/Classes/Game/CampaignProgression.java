package Model.Classes.Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CampaignProgression {

    // Path to the file that contains the progression for each user
    private static final Path path = Paths.get("src/main/java/Model/Resources/campaignProgression.txt");

    /**
     * Save the progression of a user
     *
     * @param username the username of the user
     * @param level    the level of the user
     * @Author Leonhard Amsler
     */
    public static void saveProgression(String username, int level) {
        // Get the file, read the file, check if the username and password match
        try (InputStream is = new FileInputStream(path.toString())) {

            byte[] buffer = new byte[is.available()];

            is.read(buffer);

            String fileContent = new String(buffer);

            String[] lines = fileContent.split("\n");

            boolean found = false;
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    found = true;
                    fileContent = fileContent.replace(line, username + ":" + level);
                }
            }

            if (!found) {
                fileContent += username + ":" + level + "\n";
            }

            // Save the new progression
            FileOutputStream os = new FileOutputStream(path.toString());
            os.write(fileContent.getBytes());
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the progression of a user
     *
     * @param username the username of the user
     * @return the progression of the user as an integer.
     * @Author Leonhard Amsler
     */
    public static int getProgression(String username) {
        // Get the file, read the file, check if the username and password match
        int output = -1;

        try (InputStream is = new FileInputStream(path.toString())) {

            byte[] buffer = new byte[is.available()];

            is.read(buffer);

            String fileContent = new String(buffer);

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    output = Integer.parseInt(parts[1]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * Reset the progression of a user
     *
     * @param username the username of the user
     * @Author Leonhard Amsler
     */
    public static void resetProgression(String username) {
        try (InputStream is = new FileInputStream(path.toString())) {

            byte[] buffer = new byte[is.available()];

            is.read(buffer);

            String fileContent = new String(buffer);

            String[] lines = fileContent.split("\n");

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    fileContent = fileContent.replace(line, "");
                }
            }

            // Save the new progression
            FileOutputStream os = new FileOutputStream(path.toString());
            os.write(fileContent.getBytes());
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reset the progression of all users. After reset, the user level will be 0.
     *
     * @Author Leonhard Amsler
     */
    public static void resetAllProgression() {
        try (FileOutputStream os = new FileOutputStream(path.toString())) {
            os.write(("").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
