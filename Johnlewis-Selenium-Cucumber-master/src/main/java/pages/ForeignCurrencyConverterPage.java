package pages;

import Utils.Utils;
import Utils.VerifyUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by shop on 14/09/2015.
 */
public class ForeignCurrencyConverterPage extends BasePage {

    @FindBy(how = How.LINK_TEXT,using = "Foreign currency")
    public  WebElement Foreign_currency_link;

    public  ForeignCurrencyConverterPage(){
        PageFactory.initElements(driver,this);
    }

    //public  void CurrencyConverterPageOpened(){
         // VerifyUtils.sleep(4);
          //Foreign_Currency.click();
        //  VerifyUtils.True(driver.findElement(By.linkText("Foreign Currency")).getText().contains("Foreign Currency"));
        //Assert.assertEquals("Foreign Currency", driver.findElement(By.linkText("Foreign Currency")).getText());

          //VerifyUtils.True(driver.findElement(By.cssSelector("h2")).getText().contains("Currency converter"));
        //VerifyUtils.ContainsTrue("Checking the Foreign currency Text " , Utils.getVisibleText(), "Foreign Currency");

    //}
    public void currencySwitchWindow(){
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
        // Perform the click operation that opens new window
        Foreign_currency_link.click();
        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        // Perform the actions on new window
        VerifyUtils.ContainsTrue("Checking if right Currency Page is opened", Utils.getVisibleText(), "John Lewis");
        VerifyUtils.ContainsTrue("Checking if right Currency Page is opened", Utils.getVisibleText(), "Foreign Currency");
        // Close the new window, if that window is no more required
        driver.close();
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
    }

}
