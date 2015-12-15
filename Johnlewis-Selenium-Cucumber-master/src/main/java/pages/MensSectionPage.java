package pages;

import Utils.VerifyUtils;
import org.openqa.selenium.By;

/**
 * Created by shop on 15/09/2015.
 */
public class MensSectionPage extends BasePage {

    public void MenssectionpageOpened(){
        VerifyUtils.True("Men",driver.findElement(By.cssSelector("h1.category_title")).isDisplayed());
    }
}
