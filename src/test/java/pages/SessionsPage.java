package pages;

import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import global.*;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class SessionsPage extends EvolveBasePage {

    private By searchBar;
    private By firstSearchResult;

    public SessionsPage () {

        if (onAndroid) {
            searchBar = By.id("search_bar");
            firstSearchResult = By.id("SessionItem");
        }

        if (oniOS) {
            searchBar = By.className("UIASearchBar");
            firstSearchResult = By.xpath("//*/UIAStaticText[@name='SessionItem']");
        }
    }

    @Override
    public PlatformQuery trait() {
        return new PlatformQuery()
                .setAndroid(By.id("search_bar"))
                .setiOS(By.className("UIASearchBar"));
    }

    public SessionsPage search(String text) {
        driver.findElement(searchBar).click();
        driver.findElement(searchBar).sendKeys(text);
        label("Searching for: " + text);

        if (onAndroid) {
            ((EnhancedAndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        }

        return this;
    }

    public SessionsPage verifyTopResult(String text) {
        Assert.assertEquals(text, driver.findElement(firstSearchResult).getText());
        label("Verified top result is: " + text);

        return this;
    }
}
