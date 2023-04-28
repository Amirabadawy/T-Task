package com.google.web.tests.search;

import com.google.web.browser.Browser;
import com.google.web.pages.BasePage;
import com.google.web.tests.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import static com.google.web.driver.DriverHandler.webDriver;

public class SearchStepDefs extends BaseTest {

    @Given("website is opened")
    public void websiteIsOpened() {
        browser = new Browser();
        BasePage.waitUntilPageLoaded();
    }

    @When("type search text {string} in search input in google page")
    public void KSACountryShouldBeDisplayed(String searchText){
        browser.google.googleSearch.enterText(searchText);
    }

    @Then("wuzzuf link should be displayed in result set")
    public void wuzzufLinkShouldBeDisplayedInResultSet(){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertTrue(browser.google.googleSearch.getWuzzufLink().isDisplayed());
    }

    @When("click on wuzzuf link in google page")
    public void clickOnWuzzufLink(){
        browser.google.googleSearch.clickWuzzufLink();
    }

    @Then("number of jobs should be greater than zero")
    public void numberOfJobsShouldBeGreaterThanZero(){
        String text = browser.google.googleSearch.getNumberOfJobs().getText();
        text = text.replaceAll("[\\D]","");
        Assert.assertNotSame(text,0);
    }

}
