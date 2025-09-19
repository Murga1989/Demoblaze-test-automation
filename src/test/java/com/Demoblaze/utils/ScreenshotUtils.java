package com.demoblaze.utils;

import com.demoblaze.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/Screenshots/";

    static {
        // Create screenshots directory if it doesn't exist
        File screenshotDir = new File(SCREENSHOT_DIR);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
    }

    public static String takeScreenshot(String testName) {
        WebDriver driver = BaseTest.getDriver();

        if (driver == null) {
            System.err.println("Driver is null. Cannot take screenshot.");
            return null;
        }

        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";
            String destinationPath = SCREENSHOT_DIR + fileName;

            File destinationFile = new File(destinationPath);
            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println("Screenshot saved: " + destinationPath);
            return destinationPath;

        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String takeScreenshotForReport(String testName) {
        String screenshotPath = takeScreenshot(testName);

        // Return relative path for extent reports
        if (screenshotPath != null) {
            return screenshotPath.replace(System.getProperty("user.dir"), ".");
        }

        return null;
    }

    public static void attachScreenshotToReport(String testName) {
        String screenshotPath = takeScreenshotForReport(testName);

        if (screenshotPath != null && ExtentReportManager.getTest() != null) {
            try {
                ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                System.err.println("Failed to attach screenshot to report: " + e.getMessage());
            }
        }
    }
}