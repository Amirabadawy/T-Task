package com.google.web.pages;

import com.google.web.driver.DriverHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public BasePage(){
        PageFactory.initElements(DriverHandler.getWebDriver(), this );
    }

    public static void waitUntilPageLoaded(){
        WebDriverWait wait = new WebDriverWait(DriverHandler.getWebDriver(),35);
        wait.until (webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public boolean elementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollTo(WebElement element) {
        Actions actionProvider = new Actions(DriverHandler.getWebDriver());
        final int xOffset = 5;
        final int yOffset = 0;
        do {
            if (elementIsDisplayed(element)) {
                actionProvider.moveToElement(element, xOffset, yOffset);
                actionProvider.perform();
                break;
            }
            actionProvider.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        while (true);
    }
}
