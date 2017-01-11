package pages;

import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class FeedPage extends BasePage {
    public FeedPage () throws Exception {
        super(By.xpath("//android.view.View[@resource-id='com.sample.evolve:id/toolbar']//android.widget.TextView[@text='Evolve Feed']"),
                By.xpath("//UIANavigationBar//UIAStaticText[@label='Evolve Feed']"));
    }
}