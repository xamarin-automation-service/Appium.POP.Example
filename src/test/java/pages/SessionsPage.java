package pages;

import global.BasePage;
import global.Trait;
import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class SessionsPage extends BasePage {

    @Override
    protected void setTrait() {
        Trait pageTrait = new Trait();
        pageTrait.setAndroid(By.xpath("//android.view.View[@resource-id='com.sample.evolve:id/toolbar']//android.widget.TextView[@text='Sessions']"));
        pageTrait.setiOS(By.xpath("//UIANavigationBar//UIAStaticText[@label='Sessions']"));

        trait = pageTrait;
    }

    public SessionsPage () throws Exception {
    }
}
