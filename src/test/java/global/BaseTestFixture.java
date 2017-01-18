package global;

import org.junit.After;
import org.junit.Before;

/**
 * Created by ethand on 1/10/17.
 */
public class BaseTestFixture {

    @Before
    public void setUp() throws Exception {
        AppManager.instance.startApp();
        AppManager.instance.screenshot("App Launched");
    }

    @After
    public void tearDown() throws Exception {
        AppManager.instance.screenshot("App Terminated");
        AppManager.instance.stopApp();
    }
}
