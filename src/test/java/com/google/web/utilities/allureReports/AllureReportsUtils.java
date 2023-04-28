package com.google.web.utilities.allureReports;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AllureReportsUtils {
    public void clearOldReports() {
        try {
            File allureReportDir = new File("allure-results");
            if (allureReportDir.exists()) {
                FileUtils.deleteDirectory(allureReportDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
