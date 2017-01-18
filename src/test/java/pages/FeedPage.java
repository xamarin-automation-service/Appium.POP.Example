package pages;

import global.BasePage;
import global.Trait;
import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class FeedPage extends BasePage {

    @Override
    protected void setTrait() {
        Trait pageTrait = new Trait();
        pageTrait.setiOS(By.xpath("//UIANavigationBar//UIAStaticText[@label='Evolve Feed']"));
        pageTrait.setAndroid(By.xpath("//android.view.View[@resource-id='com.sample.evolve:id/toolbar']//android.widget.TextView[@text='Evolve Feed']"));

        trait = pageTrait;
    }

    public FeedPage () throws Exception {
    }
}