package com.google.web.tests;

import com.google.web.driver.DriverHandler;
import com.google.web.utilities.allureReports.AllureReportsUtils;
import com.google.web.utilities.extentReport.ExtentReport;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class HooksHandler extends BaseTest{
    @BeforeSuite
    public void deleteOldAllureReports(){
        AllureReportsUtils allureReportUtils = new AllureReportsUtils();
        allureReportUtils.clearOldReports();
    }

    @Before(order = 3)
    public void setUp(){
        driverHandler = new DriverHandler();
        driverHandler.navigate("https://www.google.com/");
    }

    @Before(order = 1)
    public void startTCHooks(Scenario scenario) {
        ExtentReport.setScenario(scenario);
        ExtentReport.startTC();
    }

    @Before(order = 2)
    public void setStepDefs() throws NoSuchFieldException, IllegalAccessException {
        ExtentReport.setStepDefs();
    }

    @AfterStep
    public void screenshotOnFailure(Scenario scenario) throws InterruptedException, IOException, IllegalMonitorStateException
    {
        if(scenario.isFailed())
        {
            Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) driverHandler).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @After
    public void tearDown(){
        driverHandler.close();
    }

    @After(order = 1)
    public void endTC() {
        if (ExtentReport.isCurrentlyUsingReport()) {
            ExtentReport.getExtent().flush();
        }
    }
}
