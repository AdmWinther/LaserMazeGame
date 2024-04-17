package step;

import Model.Classes.Login.LoginManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.FileOutputStream;

public class LoginSteps {

    LoginManager loginManager = new LoginManager();

    @Given("There is a user with username \"user\" and password \"password\" hashed")
    public void thereIsAUserWithUsernameAndPasswordHashed() {
        try (FileOutputStream fos = new FileOutputStream("src/main/java/Model/Resources/Login.txt")) {
            int hash = "password".hashCode();
            fos.write(("user:" + hash + "\n").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("The login should be successful with username \"user\" and password \"password\"")
    public void theLoginShouldBeSuccessfulWithUsernameAndPassword() {
        assert LoginManager.checkLogin("user", "password") == LoginManager.SUCCESS;
    }

    @Given("There is not a user with username {string} and password {string} hashed")
    public void thereIsNotAUserWithUsernameAndPasswordHashed(String arg0, String arg1) {
        try (FileOutputStream fos = new FileOutputStream("src/main/java/Model/Resources/Login.txt")) {
            fos.write(("").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("The login should fail with username \"user\" and password \"password\"")
    public void theLoginShouldFailWithUsernameAndPassword() {
        assert LoginManager.checkLogin("user", "password") == LoginManager.INCORRECT_LOGIN;
    }

    @Then("The login should fail with username {string} and password anything but {string}")
    public void theLoginShouldFailWithUsernameAndPasswordAnythingBut(String arg0, String arg1) {
        assert LoginManager.checkLogin(arg0, arg1) == LoginManager.SUCCESS;
        String randomString = String.valueOf(System.currentTimeMillis());
        assert LoginManager.checkLogin(arg0, randomString) == LoginManager.INCORRECT_LOGIN;
        assert LoginManager.checkLogin(arg0, arg1 + " ") == LoginManager.INCORRECT_LOGIN;
        assert LoginManager.checkLogin(arg0, arg1 + "-") == LoginManager.INCORRECT_LOGIN;
    }

    @Then("The registration should be successful with username {string} and password {string}")
    public void theRegistrationShouldBeSuccessfulWithUsernameAndPassword(String arg0, String arg1) {
        assert LoginManager.register(arg0, arg1) == LoginManager.SUCCESS;
    }

    @Then("The user should not be created with username {string} and any password")
    public void theUserShouldNotBeCreatedWithUsernameAndAnyPassword(String arg0) {
        assert LoginManager.register(arg0, "password") == LoginManager.USER_ALREADY_EXISTS;
        assert LoginManager.register(arg0, "aksjdb ") == LoginManager.USER_ALREADY_EXISTS;
        assert LoginManager.register(arg0, "02347802 ") == LoginManager.USER_ALREADY_EXISTS;
    }
}
