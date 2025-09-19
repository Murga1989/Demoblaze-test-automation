package com.demoblaze.listeners;

import com.aventstack.extentreports.Status;
import com.demoblaze.utils.ExtentReportManager;
import com.demoblaze.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();

        if (testDescription == null || testDescription.isEmpty()) {
            testDescription = "Test execution for: " + testName;
        }

        ExtentReportManager.createTest(testName, testDescription);
        ExtentReportManager.getTest().log(Status.INFO, "Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + testName);
        ExtentReportManager.getTest().log(Status.FAIL, "Failure reason: " + result.getThrowable().getMessage());

        // Take screenshot on failure
        String screenshotPath = ScreenshotUtils.takeScreenshotForReport(testName + "_FAILED");
        if (screenshotPath != null) {
            try {
                ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } catch (Exception e) {
                ExtentReportManager.getTest().log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        }

        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.createTest(testName, "Skipped test: " + testName);
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + testName);

        if (result.getThrowable() != null) {
            ExtentReportManager.getTest().log(Status.SKIP, "Skip reason: " + result.getThrowable().getMessage());
        }

        ExtentReportManager.removeTest();
    }
}