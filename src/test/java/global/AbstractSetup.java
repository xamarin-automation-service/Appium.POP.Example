package global;

import org.junit.After;
import org.junit.Before;

/**
 * Created by ethand on 1/10/17.
 */
public class AbstractSetup {

    @Before
    public void setUp() throws Exception {
        AppInitializer.instance.startApp();
        AppInitializer.instance.screenshot("App Launched");
    }

    @After
    public void tearDown() {
        AppInitializer.instance.screenshot("App Terminated");
        AppInitializer.instance.stopApp();
    }
}
