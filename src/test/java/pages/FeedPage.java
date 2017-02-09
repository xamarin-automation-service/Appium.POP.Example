package pages;

import global.*;
import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class FeedPage extends EvolveBasePage {

    public FeedPage () {
    }

    @Override
    public PlatformQuery trait() {
        return new PlatformQuery()
                .setAndroid(By.id("AnnouncementItem"))
                .setiOS(By.id("AnnouncementItem"));
    }
}