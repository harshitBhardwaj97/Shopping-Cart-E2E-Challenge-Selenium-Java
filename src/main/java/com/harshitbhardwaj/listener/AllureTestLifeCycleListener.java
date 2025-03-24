package com.harshitbhardwaj.listener;

import com.harshitbhardwaj.driver.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Listens to the test lifecycle events and attaches relevant data (like screenshots and logs)
 * to Allure reports.
 * This listener is invoked at different stages of the test execution (start, success, failure, skip).
 *
 * @author Harshit Bhardwaj
 */
public class AllureTestLifeCycleListener implements ITestListener {

    /**
     * Default constructor for AllureTestLifeCycleListener.
     */
    public AllureTestLifeCycleListener() {
    }

    /**
     * Attaches a log message as plain text to the Allure report.
     *
     * @param message The log message to attach.
     * @return The message itself, returned as a plain text attachment.
     */
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    /**
     * Takes a screenshot and attaches it to the Allure report as a PNG image.
     *
     * @param driver The WebDriver instance used to take the screenshot.
     * @return The screenshot as a byte array.
     */
    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * This method is called when the test fails or is skipped.
     * It captures a screenshot and attaches it, along with the failure message, to Allure.
     *
     * @param result The test result object containing details of the test execution.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            WebDriver driver = WebDriverManager.getDriver();
            if (driver != null) {
                saveScreenshot(driver); // Attach the screenshot to Allure
            }
            saveTextLog(result.getName() + " failed"); // Attach log of failure
            Allure.addAttachment("Test Failure", "Test " + result.getName() + " failed.");
        }
    }

    /**
     * This method is called when a test is skipped.
     * It attaches the skip message to Allure.
     *
     * @param result The test result object containing details of the skipped test.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            Allure.addAttachment("Test Skipped", "Test " + result.getName() + " was skipped.");
        }
    }

    /**
     * This method is called when a test passes successfully.
     * It captures a screenshot and attaches it, along with the success message, to Allure.
     *
     * @param result The test result object containing details of the successful test.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            WebDriver driver = WebDriverManager.getDriver();
            if (driver != null) {
                saveScreenshot(driver); // Attach the screenshot to Allure
            }
            Allure.addAttachment("Test Success", "Test " + result.getName() + " completed successfully.");
        }
    }

    /**
     * This method is called when a test starts.
     * It adds an attachment indicating the start of the test in the Allure report.
     *
     * @param result The test result object containing details of the test start.
     */
    @Override
    public void onTestStart(ITestResult result) {
        Allure.addAttachment("Test Started", "Test " + result.getName() + " started.");
    }
}
