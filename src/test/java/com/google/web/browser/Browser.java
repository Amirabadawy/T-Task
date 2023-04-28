package com.google.web.browser;

import com.google.web.driver.DriverHandler;
import com.google.web.pages.GoogleSearch;
import com.google.web.website.GoogleWebsite;
import org.openqa.selenium.WebDriver;

public class Browser {

    private final WebDriver webDriver = DriverHandler.getWebDriver();
    public GoogleWebsite google;

    public Browser(){
        google = new GoogleWebsite ();
    }
}
