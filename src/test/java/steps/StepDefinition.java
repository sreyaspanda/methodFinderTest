package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import pageObjects.BaseSetup;
import pageObjects.LoginPageSteps;

import java.io.IOException;

public class StepDefinition {

    public WebDriver driver;

    BaseSetup baseSetup = new BaseSetup();

    LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Before
    public void setUp() {
        driver = baseSetup.setUp();
    }

    @Given("Test starts")
    public void testStarts() {
        baseSetup.startTestRun();
    }

    @Given("User lands on login page")
    public void userLandsOnLoginPage() throws InterruptedException {
        loginPageSteps.loadPage(driver);
    }

    @When("User enters {string} and {string}")
    public void userEntersAnd(String username, String password) throws InterruptedException {
        loginPageSteps.enterLoginCredentials(driver, username, password);
    }

    @Then("User should login to home page")
    public void userShouldLoginToHomePage() throws InterruptedException {
        loginPageSteps.verifyLogin(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Then("Test Record is saved with fileName {string}")
    public void testRecordIsSavedWithFileName(String fileName) throws ParseException, IOException {
        baseSetup.saveRecord(fileName);
    }
}
