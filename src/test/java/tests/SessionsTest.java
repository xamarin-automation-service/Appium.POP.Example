package tests;

import global.AbstractSetup;
import com.xamarin.testcloud.appium.Factory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import pages.FeedPage;
import pages.LoginPage;
import pages.SessionsPage;

/**
 * Created by ethand on 1/10/17.
 */
public class SessionsTest extends AbstractSetup {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Test
    public void testNavigateToSessions() throws Exception {
        new LoginPage()
                .EnterCredentials("xtc@xamarin.com", "fake")
                .TapSignInButton();

        new FeedPage()
                .selectMenuItem("Sessions");

        new SessionsPage();
    }
}
