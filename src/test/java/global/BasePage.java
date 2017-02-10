package global;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    public final boolean onAndroid;
    public final boolean oniOS;
    public final AppiumDriver driver;
    public final WebDriverWait wait;

    public BasePage() {
        onAndroid = AppManager.getPlatform() == Platform.ANDROID;
        oniOS = AppManager.getPlatform() == Platform.IOS;
        driver = AppManager.getAppiumDriver();
        wait = AppManager.getWaitDriver();

        assertOnPage();
        label(String.format("On Page: %s", this.getClass().getSimpleName()));
    }

    public abstract PlatformQuery trait();

    public void label(String label) {
        AppManager.label(label);
    }

    public void assertOnPage() {
        assertOnPage(AppManager.LONG_TIMEOUT);
    }

    public void assertOnPage(long timeOutInSeconds) {
        String message = String.format(
                "Unable to verify on page: %s. Waited for '%s'",
                this.getClass().getSimpleName(),
                trait().getCurrent().toString()
        );

        wait
                .withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                .withMessage(message)
                .until(ExpectedConditions.presenceOfElementLocated(trait().getCurrent()));
    }

    public void waitForPageToLeave() {
        waitForPageToLeave(AppManager.SHORT_TIMEOUT);
    }

    public void waitForPageToLeave(long timeOutInSeconds) {
        ExpectedCondition present = ExpectedConditions.presenceOfElementLocated(trait().getCurrent());
        String message = String.format(
                "Unable to verify *not* on page: %s. Waited for no '%s'",
                this.getClass().getSimpleName(),
                trait().getCurrent().toString()
        );

        wait
                .withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                .withMessage(message)
                .until(ExpectedConditions.not(present));
    }
}
