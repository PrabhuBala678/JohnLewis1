package features;

import Utils.AutomationConstants;
import Utils.BrowserFactory;
import Utils.VerifyUtils;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.*;

import java.util.Map;

/**
 * Created by sriramangajala on 02/07/15.
 */
public class FuncStepDef {

    public WebDriver driver;
    public LoginPage loginPage;// = new LoginPage();
    private HomePage homePage;
    private SearchResultPage searchResultPage;
    private Basketpage basketpage;
    private ForeignCurrencyConverterPage foreignCurrencyConverterPage;
    private MensSectionPage mensSectionPage;



    @Before
    public void start()
    {
        driver = BrowserFactory.getDriver();
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
//            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }



    }


    @Given("^I am logged in user$")
    public void i_am_logged_in_user() throws Throwable {
        loginPage = new LoginPage();
        homePage = loginPage.login(AutomationConstants.USERNAME,AutomationConstants.PASSWORD);

    }


    @Then("^the Subject should be \"(.*?)\"$")
    public void the_Subject_should_be(String arg1) throws Throwable {
        Assert.assertTrue(homePage.checkHeader(arg1));
    }

    @Then("^the Subject should be \"(.*?)\" and fail$")
    public void the_Subject_should_be_fail(String arg1) throws Throwable {
        Assert.assertFalse(homePage.checkHeader(arg1));
    }


    @Given("^user open a browser$")
    public void user_open_a_browser() throws Throwable {

    }

    @And("^opens the url for \"([^\"]*)\" brand$")
    public void opens_the_url_for_brand(String brand) throws Throwable {

        if(brand.equalsIgnoreCase("Armstrong Auto"))
        {
            driver.get(AutomationConstants.ArmstrongAutoURL);
        }
    }

    @Then("^the page should be opened$")
    public void the_page_should_be_opened() throws Throwable {

        homePage = new HomePage();
    }

    @And("^the brand name should be shown as \"([^\"]*)\"$")
    public void the_brand_name_should_be_shown_brand_name_in_header_as(String header) throws Throwable {

        VerifyUtils.True("Checking if user can see header", homePage.checkHeader(header));
    }

    @And("^show the below elements$")
    public void show_the_below_elements(DataTable dataTable) throws Throwable {
        Map<String, String> data = dataTable.asMap(String.class,String.class);
        homePage.checkElements(data);

    }

    @Then("^a header with name \"(.*?)\"$")
    public void a_header_with_name(String header) throws Throwable {
        VerifyUtils.True("Checking the header is "+header,homePage.getHeader(header));
    }

    @Then("^a button with name \"(.*?)\" is shown$")
    public void a_button_with_name_is_shown(String buttontext) throws Throwable {

        VerifyUtils.True("Checking the button with text is "+buttontext,homePage.getButton(buttontext));
    }

    @When("^(?:open|close) the ham burger menu$")
    public void open_the_ham_burger_menu() throws Throwable {
        homePage = new HomePage();
        homePage.openHamBurgerMenu();

    }

    @Then("^I should see the following option$")
    public void I_should_see_the_following_option(DataTable dataTable) throws Throwable {
        Map<String, String> data = dataTable.asMap(String.class,String.class);
        homePage.checkElements(data);
    }

    @When("^he open an all the shops$")
    public void he_open_an_all_the_shops() throws Throwable {
        homePage = new HomePage();
        homePage.openAllShops();

    }

    String branch;
    @Then("^the branch \"(.*?)\" should be shown$")
    public void he_the_branch_should_be_shown(String branch) throws Throwable {

        homePage.checkBranchIsShown(branch);
        this.branch = branch;
    }

    @Then("^he opens the branch$")
    public void he_opens_the_branch() throws Throwable {
        homePage.openTheBranch(branch);

    }

    @Then("^the details of the branch should be shown$")
    public void the_details_of_the_branch_should_be_shown() throws Throwable {

        homePage.checkBranchText(branch);
    }

    @Given("^user is in home page$")
    public void user_is_in_home_page() throws Throwable {

    }

    @When("^I search for \"([^\"]*)\"$")
    public void I_search_for(String searchKeyword) throws Throwable {
        homePage = new HomePage();
        homePage.searchWithKeyword(searchKeyword);

        Assert.assertFalse(driver.findElement(By.tagName("body")).getText().contains("Sorry, we couldn't find any results matching"));



    }


    @And("^added an item to the basket with title \"([^\"]*)\"$")
    public void added_an_item_to_the_basket_with_title(String title) throws Throwable {
        searchResultPage = new SearchResultPage();
        searchResultPage.addFirstItemIntoTheBasket();
    }

    @Then("^an item should be available in basket$")
    public void an_item_should_be_available_in_basket() throws Throwable {
        basketpage = new Basketpage();
        basketpage.checkItemIsAdded();


    }

    //Foreign currency converter
    @Given("^user is in the home page$")
    public void user_is_in_the_home_page() throws Throwable {
        homePage = new HomePage();
        foreignCurrencyConverterPage = new ForeignCurrencyConverterPage();

    }

    @When("^he click on the foreign currency link$")
    public void he_click_on_the_foreign_currency_link() throws Throwable {
        foreignCurrencyConverterPage.Foreign_currency_link.click();
    }

    @When("^it should navigate to the Foreign currency page$")
    public void it_should_navigate_to_the_Foreign_currency_page() throws Throwable {
        foreignCurrencyConverterPage.currencySwitchWindow();
    }

    @Then("^user should see the currency converter$")
    public void user_should_see_the_currency_converter() throws Throwable {

    }

    //User should goto the men's section in department

    @Given("^user should be on the home page$")
    public void user_should_be_on_the_home_page() throws Throwable {
        homePage =new HomePage();
        mensSectionPage = new MensSectionPage();

    }

    @When("^user clicks the men's section image link$")
    public void user_clicks_the_men_s_section_image_link() throws Throwable {
        //driver.findElement(By.xpath("html/body/div[8]/div/section/section[2]/div[2]/div[5]/a/span[1]")).click();
        homePage.Open_Mens_Section.click();

    }

    @And("^he should see the men's shopping section page$")
    public void he_should_see_the_men_s_shopping_section_page() throws Throwable {
        //Assert.assertTrue(driver.findElement(By.cssSelector("h1.category_title")).getText().contains("Men"));
        mensSectionPage.MenssectionpageOpened();

    }

    @Then("^he should see the information is appeared on the page$")
    public void he_should_see_the_information_is_appeard_on_the_page() throws Throwable {

    }


}
