package pages;

import global.*;
import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class LoginPage extends EvolveBasePage {

    private By usernameField;
    private By passwordField;
    private By signInButton;
    private By notNowButton;

    public LoginPage() {
        usernameField = By.id("EmailTextField");
        passwordField = By.id("PasswordTextField");
        signInButton = By.id("SignInButton");
        notNowButton = By.id("NotNowButton");
    }

    @Override
    public PlatformQuery trait() {
        return new PlatformQuery()
                .setAndroid(By.id("SignInButton"))
                .setiOS(By.id("SignInButton"));
    }

    public LoginPage enterCredentials(String username, String password) {
        driver.findElement(usernameField).click();
        driver.findElement(usernameField).sendKeys(username);
        label("Entered username");
        driver.hideKeyboard();

        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
        label("Entered password");
        driver.hideKeyboard();

        return this;
    }

    public void tapSignInButton() {
        driver.findElement(signInButton).click();
        label("Tapped: 'Sign in'");
    }

    public void skip() {
        driver.findElement(notNowButton).click();
        label("Tapped: 'Not Now'");
    }
}
