package global;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public abstract class BasePage {

    protected static boolean onAndroid;
    protected static boolean oniOS;

    protected Trait trait;

    abstract protected void setTrait();

    protected BasePage() throws Exception {
        onAndroid = AppManager.platform == AppManager.Platform.ANDROID;
        oniOS = !onAndroid;

        if (trait.getCurrent() == null)
            throw new Exception("Page trait is not set");

        assertOnPage();

        initializeCommonQueries();
    }

    protected void assertOnPage() throws Exception {
        getDriver().findElement(trait.getCurrent());
        screenshot(String.format("On Page: %s", this.getClass().getSimpleName()));
    }

    protected AppiumDriver getDriver() throws Exception {
        return AppManager.getDriver();
    }

    private By hamburgerButton;
    private void initializeCommonQueries() {
        if (onAndroid) {
            hamburgerButton = By.xpath("//android.widget.ImageButton[@content-desc='OK']");
        } else {

        }
    }

    private By getMenuItem(String title) {
        if (onAndroid) {
            return By.xpath(String.format("//android.widget.CheckedTextView[@text='%s']", title));
        }
        else {
            return By.xpath(String.format("//UIATabBar//UIAButton[@name='%s']", title));
        }
    }

    public void selectMenuItem(String marked) throws Exception {
        if (onAndroid) {
            getDriver().findElement(hamburgerButton).click();
            screenshot("Menu Opened");
        }
        getDriver().findElement(getMenuItem(marked)).click();
        screenshot(String.format("Tapped menu item: %s", marked));
    }

    public void screenshot(String label) {
        AppManager.screenshot(label);
    }
}
