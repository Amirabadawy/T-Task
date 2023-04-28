package com.google.web.tests.search;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "C:\\Users\\Amira\\IdeaProjects\\Task\\src\\test\\java\\com\\google\\web\\tests\\search\\Search.feature",
        glue = {"com/google/web/tests"},
        plugin = {"pretty","html:reports/search-Report.html"},
        monochrome = true
)
public class searchTestRunner extends AbstractTestNGCucumberTests {
}
