package global;

import org.openqa.selenium.By;

/**
 * Created by ethand on 1/18/17.
 */
public class Trait {
    protected By iOS;
    protected By Android;

    public void setiOS(By value) {
        iOS = value;
    }

    public void setAndroid(By value) {
        Android = value;
    }

    public By getCurrent() throws Exception {
        switch (AppManager.platform) {
            case IOS:
                return iOS;
            case ANDROID:
                return Android;
            default:
                throw new Exception("Platform is not supported");
        }
    }
}
