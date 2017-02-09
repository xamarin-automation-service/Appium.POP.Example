package tests;

import global.*;
import org.junit.Test;
import pages.*;

/**
 * Created by matisseh on 2/8/17.
 */
public class LogInTest extends BaseTestFixture{

    @Override
    public void beforeEachTest()
    {
        start();
    }

    @Test
    public void testLogInWithFakeCredentials() {
        new LoginPage()
                .EnterCredentials("xtc@xamarin.com", "fake")
                .TapSignInButton();
    }
}
