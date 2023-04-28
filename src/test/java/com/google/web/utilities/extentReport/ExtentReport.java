package com.google.web.utilities.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class ExtentReport {
    private static final String extendReportPath = System.getProperty("user.dir") + "/resources/reports/";
    private static final String delegateFieldName = "delegate";
    private static final String testCaseFieldName = "testCase";
    public static int tcNumber = 1;
    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test;
    private static int currentStepDefIndex;
    private static Scenario scenario;
    private static List<PickleStepTestStep> stepDefs;
    private static boolean currentlyUsingReport = false;

    @BeforeTest
    @Parameters("ReportName")
    public void startReport(String reportName) {
        currentlyUsingReport = true;
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extendReportPath + reportName+ ".html");
        sparkReporter.config().thumbnailForBase64(true);
        extent.attachReporter(sparkReporter);
    }

    public static void setScenario(Scenario currentScenario){
        scenario = currentScenario;
    }

    public static void startTC() {
        String testCaseName = tcNumber++ + "-  " + scenario.getName();
        test = extent.createTest(testCaseName);
        currentStepDefIndex = 0;
    }

    public static void setStepDefs() throws NoSuchFieldException, IllegalAccessException {
        TestCase testCase = getTestCase();
        stepDefs = getTestStepsDefs(testCase);
    }

    private static TestCase getTestCase() throws NoSuchFieldException, IllegalAccessException {
        Field delegateField = scenario.getClass().getDeclaredField(delegateFieldName);
        delegateField.setAccessible(true);
        TestCaseState testCaseState = (TestCaseState) delegateField.get(scenario);

        Field testCaseField = testCaseState.getClass().getDeclaredField(testCaseFieldName);
        testCaseField.setAccessible(true);
        TestCase testCase = (TestCase) testCaseField.get(testCaseState);
        return testCase;
    }

    public static String getCurrentStepName(){
        String currentStepDescription;

        PickleStepTestStep currentStepDef = stepDefs.get(currentStepDefIndex);

        currentStepDescription = currentStepDef.getStep().getText();
        currentStepDefIndex += 1;

        return currentStepDescription ;
    }

    private static List<PickleStepTestStep> getTestStepsDefs(TestCase testCase) {
        return testCase.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static boolean isCurrentlyUsingReport() {
        return currentlyUsingReport;
    }
}
