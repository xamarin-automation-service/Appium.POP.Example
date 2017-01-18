package pages;

import global.BasePage;
import global.Trait;
import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class LoginPage extends BasePage {

    By usernameField;
    By passwordField;
    By signInButton;

    @Override
    protected void setTrait() {
        Trait pageTrait = new Trait();
        pageTrait.setAndroid(By.id("SignInButton"));
        pageTrait.setiOS(By.id("SignInButton"));

        trait = pageTrait;
    }

    public LoginPage() throws Exception {
        usernameField = By.id("EmailTextField");
        passwordField = By.id("PasswordTextField");
        signInButton = By.id("SignInButton");
    }

    public LoginPage EnterCredentials(String username, String password) throws Exception {
        getDriver().findElement(usernameField).sendKeys(username);
        screenshot("Entered username");
        getDriver().hideKeyboard();

        getDriver().findElement(passwordField).sendKeys(password);
        screenshot("Entered password");
        getDriver().hideKeyboard();

        return this;
    }

    public void TapSignInButton() throws Exception {
        getDriver().findElement(signInButton).click();
        screenshot("Tapped: 'Sign in'");
    }
}
