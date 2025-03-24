package com.harshitbhardwaj.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Interface to define various wait operations that can be performed on WebElements and URLs.
 * Each method provides a way to wait for a specific condition to be met.
 * These methods are used to implement explicit waits for elements, text, URLs, and alerts.
 *
 * @author Harshit Bhardwaj
 */
public interface WaitHelper {

    /**
     * Waits for the specified element to be visible.
     *
     * @param locator the locator of the element to wait for
     * @return the WebElement once it becomes visible
     */
    WebElement waitForElementToBeVisible(By locator);

    /**
     * Waits for the specified element to be clickable.
     *
     * @param locator the locator of the element to wait for
     * @return the WebElement once it becomes clickable
     */
    WebElement waitForElementToBeClickable(By locator);

    /**
     * Waits for the specified element to be present in the DOM.
     *
     * @param locator the locator of the element to wait for
     * @return the WebElement once it is present in the DOM
     */
    WebElement waitForElementToBePresent(By locator);

    /**
     * Waits for the URL to exactly match the provided URL.
     *
     * @param url the exact URL to wait for
     * @return true if the current URL matches the provided URL
     */
    boolean waitForUrlToBe(String url);

    /**
     * Waits for the URL to contain the provided partial URL.
     *
     * @param partialUrl the partial URL to wait for
     * @return true if the current URL contains the provided partial URL
     */
    boolean waitForUrlToContain(String partialUrl);

    /**
     * Waits for an alert to be present on the page.
     */
    void waitForAlertToBePresent();

    /**
     * Waits for the specified element's text to become equal to the provided text.
     *
     * @param locator the locator of the element to wait for
     * @param text    the expected text of the element
     * @return true if the element's text becomes equal to the provided text
     */
    boolean waitForElementTextToBe(By locator, String text);

    /**
     * Waits for the specified element's text to become different from the provided text.
     *
     * @param locator the locator of the element to wait for
     * @param text    the text that the element's text should not be
     * @return true if the element's text becomes different from the provided text
     */
    boolean waitForElementTextNotToBe(By locator, String text);

    /**
     * Waits for the specified element to be visible within the given timeout.
     *
     * @param locator          the locator of the element to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become visible
     * @return the WebElement once it becomes visible
     */
    WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds);

    /**
     * Waits for the specified element to be clickable within the given timeout.
     *
     * @param locator          the locator of the element to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become clickable
     * @return the WebElement once it becomes clickable
     */
    WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds);

    /**
     * Waits for the specified element to be present in the DOM within the given timeout.
     *
     * @param locator          the locator of the element to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to be present in the DOM
     * @return the WebElement once it is present in the DOM
     */
    WebElement waitForElementToBePresent(By locator, int timeoutInSeconds);

    /**
     * Waits for the specified element to become invisible within the given timeout.
     *
     * @param locator          the locator of the element to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become invisible
     * @return true if the element becomes invisible within the given timeout
     */
    boolean waitForElementToBeInvisible(By locator, int timeoutInSeconds);

    /**
     * Waits for the specified element to become invisible after waiting for the given time.
     *
     * @param locator          the locator of the element to wait for
     * @param timeoutInSeconds the maximum time to wait for the element to become invisible
     * @param afterSeconds     the time to wait before checking for invisibility
     * @return true if the element becomes invisible after waiting the specified time
     */
    boolean waitForElementToBeInvisibleAfter(By locator, int timeoutInSeconds, int afterSeconds);

    /**
     * Waits for the URL to exactly match the provided URL within the given timeout.
     *
     * @param url              the exact URL to wait for
     * @param timeoutInSeconds the maximum time to wait for the URL to match
     * @return true if the current URL matches the provided URL
     */
    boolean waitForUrlToBe(String url, int timeoutInSeconds);

    /**
     * Waits for the URL to contain the provided partial URL within the given timeout.
     *
     * @param partialUrl       the partial URL to wait for
     * @param timeoutInSeconds the maximum time to wait for the URL to contain the partial URL
     * @return true if the current URL contains the provided partial URL
     */
    boolean waitForUrlToContain(String partialUrl, int timeoutInSeconds);

    /**
     * Waits for an alert to be present on the page within the given timeout.
     *
     * @param timeoutInSeconds the maximum time to wait for the alert to be present
     */
    void waitForAlertToBePresent(int timeoutInSeconds);

    /**
     * Waits for the specified element's text to become equal to the provided text within the given timeout.
     *
     * @param locator          the locator of the element to wait for
     * @param text             the expected text of the element
     * @param timeoutInSeconds the maximum time to wait for the element's text to become equal to the provided text
     * @return true if the element's text becomes equal to the provided text
     */
    boolean waitForElementTextToBe(By locator, String text, int timeoutInSeconds);

    /**
     * Waits for the specified element's text to become different from the provided text within the given timeout.
     *
     * @param locator          the locator of the element to wait for
     * @param text             the text that the element's text should not be
     * @param timeoutInSeconds the maximum time to wait for the element's text to become different from the provided text
     * @return true if the element's text becomes different from the provided text
     */
    boolean waitForElementTextNotToBe(By locator, String text, int timeoutInSeconds);
}