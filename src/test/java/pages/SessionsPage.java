package pages;

import org.openqa.selenium.By;

/**
 * Created by ethand on 1/10/17.
 */
public class SessionsPage extends BasePage {
    public SessionsPage () throws Exception {
        super(By.xpath("//android.view.View[@resource-id='com.sample.evolve:id/toolbar']//android.widget.TextView[@text='Sessions']"),
                By.xpath("//UIANavigationBar//UIAStaticText[@label='Sessions']"));
    }
}
