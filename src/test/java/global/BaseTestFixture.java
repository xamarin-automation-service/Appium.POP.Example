package global;

import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

/**
 * Created by ethand on 1/10/17.
 */
public class BaseTestFixture {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    public boolean onAndroid;
    public boolean oniOS;
    public AppiumDriver driver;
    public WebDriverWait wait;

    public void start() {
        AppManager.startApp();
        AppManager.label("App Launched");

        onAndroid = AppManager.getPlatform() == Platform.ANDROID;
        oniOS = AppManager.getPlatform() == Platform.IOS;
        driver = AppManager.getAppiumDriver();
        wait = AppManager.getWaitDriver();
    }

    @Before
    public void beforeEachTest() {
        start();
        skipLogInPage();
    }

    public void skipLogInPage() {
        // When testing iOS locally in simulator or Android:
        if (onAndroid || System.getenv("XTC_PLATFORM").equals("ios-simulator")) {
            new LoginPage()
                    .skip();
    }
}