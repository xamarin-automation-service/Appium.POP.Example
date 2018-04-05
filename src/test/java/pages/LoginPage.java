package pages;

import com.microsoft.appcenter.appium.Factory;
import global.*;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class LoginPage extends EvolveBasePage {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    public LoginPage() {
    }

    @Override
    public PlatformQuery trait() {
        return new PlatformQuery()
                .setAndroid(By.id("Login"))
                .setiOS(By.id("Login"));
    }

    public LoginPage enterCredentials(String username, String password) {
        driver.findElementByAccessibilityId("LoginEntry").click();
        driver.findElementByAccessibilityId("LoginEntry").sendKeys(username);
        label("Entered username");
        driver.hideKeyboard();

        driver.findElementByAccessibilityId("PasswordEntry").click();
        driver.findElementByAccessibilityId("PasswordEntry").sendKeys(password);
        label("Entered password");
        driver.hideKeyboard();

        return this;
    }

    public void tapSignInButton() {
        driver.findElementByAccessibilityId("LoginButton").click();
        label("Tapped: 'Sign in'");
    }
}
