package tests;

import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import org.junit.After;
import org.junit.rules.TestWatcher;
import org.junit.Rule;
import global.*;
import org.junit.Test;
import pages.*;

/**
 * Created by matisseh on 2/8/17.
 */
public class LogInTest extends BaseTestFixture{

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Override
    public void beforeEachTest()
    {
        start();
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void testLoginWithCredentials() {
        new LoginPage()
                .enterCredentials("test@mail.com", "fakepassword")
                .tapSignInButton();
    }

    @Test
    public void testLoginFailed()
    {
        new LoginPage().tapSignInButton();
    }
}