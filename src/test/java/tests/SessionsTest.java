package tests;

import global.*;
import org.junit.Test;
import pages.*;

/**
 * Created by ethand on 1/10/17.
 */
public class SessionsTest extends BaseTestFixture {

    @Test
    public void testNavigateToSessions() {
        new FeedPage()
                .selectMenuItem("Sessions");

        new SessionsPage();
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
