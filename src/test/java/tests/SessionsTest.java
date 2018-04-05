package tests;

import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import global.*;
import org.junit.Test;
import pages.*;

/**
 * Created by ethand on 1/10/17.
 */
public class SessionsTest extends BaseTestFixture {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();
    
    @Test
    public void testNavigateToSessions() {
        new FeedPage()
                .selectMenuItem("Sessions");

        new SessionsPage();
    }

    @After
    public void TearDown(){
        driver.quit();
    }

    @Test
    public void testSearchForSession() {
        new FeedPage()
                .selectMenuItem("Sessions");

        new SessionsPage()
            .search("xaml")
            .verifyTopResult("Mastering XAML in Xamarin.Forms");
    }
}
