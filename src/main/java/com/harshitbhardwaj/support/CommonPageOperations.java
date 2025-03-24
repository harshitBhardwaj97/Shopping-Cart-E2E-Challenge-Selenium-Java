package com.harshitbhardwaj.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.harshitbhardwaj.constants.Constants.Common.EXPLICIT_LONG_WAIT;

/**
 * @author Harshit Bhardwaj
 */
public class CommonPageOperations implements WaitHelper {

    private final WebDriver driver;

    public CommonPageOperations(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebElement waitForElementToBeVisible(By locator) {
        return waitForElementToBeVisible(locator, EXPLICIT_LONG_WAIT);
    }

    @Override
    public WebElement waitForElementToBeClickable(By locator) {
        return waitForElementToBeClickable(locator, EXPLICIT_LONG_WAIT);
    }

    @Override
    public WebElement waitForElementToBePresent(By locator) {
        return waitForElementToBePresent(locator, EXPLICIT_LONG_WAIT);
    }

    @Override
    public boolean waitForUrlToBe(String url) {
        return waitForUrlToBe(url, EXPLICIT_LONG_WAIT);
    }

    @Override
    public boolean waitForUrlToContain(String partialUrl) {
        return waitForUrlToContain(partialUrl, EXPLICIT_LONG_WAIT);
    }

    @Override
    public void waitForAlertToBePresent() {
        waitForAlertToBePresent(EXPLICIT_LONG_WAIT);
    }

    @Override
    public boolean waitForElementTextToBe(By locator, String text) {
        return waitForElementTextToBe(locator, text, EXPLICIT_LONG_WAIT);
    }

    @Override
    public boolean waitForElementTextNotToBe(By locator, String text) {
        return waitForElementTextNotToBe(locator, text, EXPLICIT_LONG_WAIT);
    }

    @Override
    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Override
    public WebElement waitForElementToBePresent(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public boolean waitForElementToBeInvisible(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Override
    public boolean waitForElementToBeInvisibleAfter(By locator, int timeoutInSeconds, int afterSeconds) {
        try {
            Thread.sleep(afterSeconds);
            return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean waitForUrlToBe(String url, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.urlToBe(url));
        return driver.getCurrentUrl().equals(url);
    }

    @Override
    public boolean waitForUrlToContain(String partialUrl, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.urlContains(partialUrl));
        return driver.getCurrentUrl().contains(partialUrl);
    }

    @Override
    public void waitForAlertToBePresent(int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.alertIsPresent());
    }

    @Override
    public boolean waitForElementTextToBe(By locator, String text, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.textToBe(locator, text));
    }

    @Override
    public boolean waitForElementTextNotToBe(By locator, String text, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }
}