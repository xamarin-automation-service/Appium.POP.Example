package global;

import org.openqa.selenium.By;

/**
 * Created by ethand on 1/18/17.
 */
public class PlatformQuery {

    private By current;

    public PlatformQuery setAndroid(By query) {
        if(AppManager.getPlatform() == Platform.ANDROID)
            current = query;

        return this;
    }

    public PlatformQuery setiOS(By query) {
        if(AppManager.getPlatform() == Platform.IOS)
            current = query;

        return this;
    }

    public By getCurrent() {
        if (current == null)
            throw new NullPointerException("PlatformQuery not set for current platform");

        return current;
    }
}
