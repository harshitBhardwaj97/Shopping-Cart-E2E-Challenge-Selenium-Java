package com.harshitbhardwaj.support;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

/**
 * Helper class for interacting with web elements on a page.
 * This class provides methods for common actions such as clicking elements, entering text, and switching
 * between windows or frames.
 * It uses the WaitHelper to handle waiting for elements and conditions.
 *
 * @author Harshit Bhardwaj
 */
public class PageInteractionHelper {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    public PageInteractionHelper(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new CommonPageOperations(driver);
    }

    /**
     * Returns the WebDriver instance associated with the current PageInteractionHelper.
     *
     * @return the WebDriver instance for interacting with the browser.
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url the URL to navigate to
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Gets the current URL of the page.
     *
     * @return the current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Gets the current title of the page.
     *
     * @return the current title
     */
    public String getCurrentTitle() {
        return driver.getTitle();
    }

    /**
     * Clears the text field specified by the locator.
     *
     * @param locator the locator of the field to clear
     */
    public void clearField(By locator) {
        WebElement element = waitHelper.waitForElementToBeVisible(locator);
        element.clear();
    }

    /**
     * Checks if the element specified by the locator is displayed on the page.
     *
     * @param locator the locator of the element to check
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitHelper.waitForElementToBeVisible(locator);
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if the element specified by the locator is displayed on the page within a given timeout.
     *
     * @param locator          the locator of the element to check
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be displayed
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator, int timeoutInSeconds) {
        try {
            WebElement element = waitHelper.waitForElementToBeVisible(locator, timeoutInSeconds);
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if the text of the element specified by the locator matches the provided text.
     *
     * @param locator the locator of the element to check
     * @param text    the text to match
     * @return true if the text matches, false otherwise
     */
    public boolean elementTextMatches(By locator, String text) {
        try {
            WebElement element = waitHelper.waitForElementToBeVisible(locator);
            return element.getText().equals(text);
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if the text of the element specified by the locator matches the provided text within a given timeout.
     *
     * @param locator          the locator of the element to check
     * @param text             the text to match
     * @param timeoutInSeconds the timeout in seconds to wait for the element's text to match
     * @return true if the text matches, false otherwise
     */
    public boolean elementTextMatches(By locator, String text, int timeoutInSeconds) {
        try {
            WebElement element = waitHelper.waitForElementToBeVisible(locator, timeoutInSeconds);
            return element.getText().equals(text);
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Clicks on the element specified by the locator.
     *
     * @param locator the locator of the element to click
     */
    public void clickOnElement(By locator) {
        WebElement element = waitHelper.waitForElementToBeClickable(locator);
        highlightElement(element);
        element.click();
    }

    /**
     * Clicks on the element specified by the locator within a given timeout.
     *
     * @param locator          the locator of the element to click
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be clickable
     */
    public void clickOnElement(By locator, int timeoutInSeconds) {
        WebElement element = waitHelper.waitForElementToBeClickable(locator, timeoutInSeconds);
        highlightElement(element);
        element.click();
    }

    /**
     * Clicks on the element specified by the locator using JavaScript.
     *
     * @param locator the locator of the element to click
     */
    public void clickOnElementUsingJavascript(By locator) {
        WebElement element = waitHelper.waitForElementToBeClickable(locator);
        highlightElement(element);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    /**
     * Clicks on the element specified by the locator using JavaScript within a given timeout.
     *
     * @param locator          the locator of the element to click
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be clickable
     */
    public void clickOnElementUsingJavascript(By locator, int timeoutInSeconds) {
        WebElement element = waitHelper.waitForElementToBeClickable(locator, timeoutInSeconds);
        highlightElement(element);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    /**
     * Clicks on the element specified by the locator at a given offset using Actions.
     *
     * @param locator the locator of the element to click
     * @param offsetX the horizontal offset from the left edge of the element (in pixels)
     * @param offsetY the vertical offset from the top edge of the element (in pixels)
     */
    public void clickOnElementWithOffset(By locator, int offsetX, int offsetY) {
        WebElement element = waitHelper.waitForElementToBeClickable(locator);
        // Get the element size (height and width)
        var size = element.getSize();
        int width = size.getWidth();
        int height = size.getHeight();

        // Calculate the position based on the offsets provided
        int xCoordinate = Math.min(offsetX, width);  // Ensure offset is within element bounds
        int yCoordinate = Math.min(offsetY, height);  // Ensure offset is within element bounds

        // Move to the element with the specified offset and click
        Actions actions = new Actions(driver);
        highlightElement(element);
        actions.moveToElement(element, xCoordinate, yCoordinate).click().perform();
    }

    /**
     * Clicks on the element specified by the locator at a given offset using Actions within a given timeout.
     *
     * @param locator          the locator of the element to click
     * @param offsetX          the horizontal offset from the left edge of the element (in pixels)
     * @param offsetY          the vertical offset from the top edge of the element (in pixels)
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be clickable
     */
    public void clickOnElementWithOffset(By locator, int offsetX, int offsetY, int timeoutInSeconds) {
        WebElement element = waitHelper.waitForElementToBeClickable(locator, timeoutInSeconds);
        // Get the element size (height and width)
        var size = element.getSize();
        int width = size.getWidth();
        int height = size.getHeight();

        // Calculate the position based on the offsets provided
        int xCoordinate = Math.min(offsetX, width);  // Ensure offset is within element bounds
        int yCoordinate = Math.min(offsetY, height);  // Ensure offset is within element bounds

        // Move to the element with the specified offset and click
        Actions actions = new Actions(driver);
        highlightElement(element);
        actions.moveToElement(element, xCoordinate, yCoordinate).click().perform();
    }

    /**
     * Performs the specified key action on the element identified by the locator.
     *
     * @param locator the locator of the element to perform the action on
     * @param key     the key event (e.g., ARROW_LEFT, ARROW_RIGHT, ENTER, etc.) to be performed
     */
    public void performKeyActionOn(By locator, Keys key) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        Actions actions = new Actions(driver);
        highlightElement(element);
        actions.moveToElement(element).sendKeys(key).build().perform();
    }

    /**
     * Performs the specified key action on the element identified by the locator within a given timeout.
     *
     * @param locator          the locator of the element to perform the action on
     * @param key              the key event (e.g., ARROW_LEFT, ARROW_RIGHT, ENTER, etc.) to be performed
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present
     */
    public void performKeyActionOn(By locator, Keys key, int timeoutInSeconds) {
        WebElement element = waitHelper.waitForElementToBePresent(locator, timeoutInSeconds);
        Actions actions = new Actions(driver);
        highlightElement(element);
        actions.moveToElement(element).sendKeys(key).build().perform();
    }

    /**
     * Performs the specified key action on the currently focused element.
     *
     * @param key the key event (e.g., ARROW_LEFT, ARROW_RIGHT, ENTER, etc.) to be performed
     */
    public void performKeyAction(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();
    }

    /**
     * Performs the specified key action on the currently focused element within a given timeout.
     *
     * @param key              the key event (e.g., ARROW_LEFT, ARROW_RIGHT, ENTER, etc.) to be performed
     * @param timeoutInSeconds the timeout in seconds to wait before performing the action
     */
    public void performKeyAction(Keys key, int timeoutInSeconds) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();
    }

    /**
     * Enters the specified text into the element identified by the locator.
     *
     * @param locator the locator of the element where text should be entered
     * @param text    the text to enter into the element
     */
    public void enterText(By locator, String text) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        highlightElement(element);
        element.sendKeys(text);
    }

    /**
     * Enters the specified text into the element identified by the locator within a given timeout.
     *
     * @param locator          the locator of the element where text should be entered
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present
     * @param text             the text to enter into the element
     */
    public void enterText(By locator, int timeoutInSeconds, String text) {
        WebElement element = waitHelper.waitForElementToBePresent(locator, timeoutInSeconds);
        highlightElement(element);
        element.sendKeys(text);
    }

    /**
     * Gets the text from the element specified by the locator.
     *
     * @param locator the locator of the element to get text from
     * @return the text of the element
     */
    public String getText(By locator) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        highlightElement(element);
        return element.getText();
    }

    /**
     * Gets the text from the element specified by the locator within a given timeout.
     *
     * @param locator          the locator of the element to get text from
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present
     * @return the text of the element
     */
    public String getText(By locator, int timeoutInSeconds) {
        WebElement element = waitHelper.waitForElementToBePresent(locator, timeoutInSeconds);
        highlightElement(element);
        return element.getText();
    }

    /**
     * Gets the window handle of the current window.
     *
     * @return the window handle of the current window
     */
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * Gets all window handles of the current session.
     *
     * @return a set of window handles
     */
    public Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    /**
     * Switches to a window identified by its name or handle.
     *
     * @param windowNameOrHandle the name or handle of the window to switch to
     */
    public void switchToWindow(String windowNameOrHandle) {
        driver.switchTo().window(windowNameOrHandle);
    }

    /**
     * Selects an option from a dropdown by visible text that contains the given text.
     *
     * @param locator     the locator of the dropdown element
     * @param visibleText the partial visible text of the option to select (contains)
     */
    public void selectByVisibleTextContains(By locator, String visibleText) {
        waitHelper.waitForElementToBePresent(locator);
        Select select = new Select(driver.findElement(locator));
        System.out.println("Selecting value containing text: " + visibleText);
        select.selectByContainsVisibleText(visibleText);
    }

    /**
     * Selects multiple options from a dropdown by visible text that contains the given texts.
     *
     * @param locator      the locator of the dropdown element
     * @param visibleTexts the partial visible texts of the options to select (contains)
     */
    public void multiSelectByVisibleTextContains(By locator, String... visibleTexts) {
        waitHelper.waitForElementToBePresent(locator);
        Select select = new Select(driver.findElement(locator));
        for (var visibleText : visibleTexts) {
            System.out.println("Selecting value containing text: " + visibleText);
            select.selectByContainsVisibleText(visibleText);
        }
    }

    /**
     * Drags and drops an element from one location to another.
     *
     * @param source the locator of the source element
     * @param target the locator of the target element
     */
    public void dragAndDrop(By source, By target) {
        WebElement from = waitHelper.waitForElementToBePresent(source);
        WebElement to = waitHelper.waitForElementToBePresent(target);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(from, to).perform();
    }

    /**
     * Gets the size of the elements list identified by the locator.
     *
     * @param locator the locator of the elements to count
     * @return the size of the elements list
     */
    public long getSizeOfElementsList(By locator) {
        return driver.findElements(locator).size();
    }

    /**
     * Moves the mouse pointer to the element specified by the locator.
     *
     * @param locator the locator of the element to move to
     */
    public void moveToElement(By locator) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        highlightElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Moves the mouse pointer to the element specified by the locator within a given timeout.
     *
     * @param locator          the locator of the element to move to
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present
     */
    public void moveToElement(By locator, int timeoutInSeconds) {
        WebElement element = waitHelper.waitForElementToBePresent(locator, timeoutInSeconds);
        highlightElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Gets the value of an element's attribute identified by the locator.
     *
     * @param locator   the locator of the element
     * @param attribute the attribute whose value is to be retrieved
     * @return the value of the attribute
     */
    public String getElementAttribute(By locator, String attribute) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        highlightElement(element);
        return element.getAttribute(attribute);
    }

    /**
     * Gets the value of an element's value property.
     *
     * @param locator the locator of the element
     * @return the value of the element's value property
     */
    public String getElementValueProperty(By locator) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        highlightElement(element);
        return element.getDomProperty("value");
    }

    /**
     * Scrolls the page to bring the element identified by the locator into view.
     *
     * @param locator the locator of the element to scroll to
     */
    public void scrollElementIntoView(By locator) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        highlightElement(element);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Switches to the iframe identified by its name or ID.
     *
     * @param iFrameNameOrId the name or ID of the iframe to switch to
     */
    public void switchToIframe(String iFrameNameOrId) {
        driver.switchTo().frame(iFrameNameOrId);
    }

    /**
     * Switches back to the default content from an iframe.
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Closes the current window.
     */
    public void closeWindow() {
        driver.close();
    }

    /**
     * Checks if the element specified by the locator is enabled.
     *
     * @param locator the locator of the element
     * @return true if the element is enabled, false otherwise
     */
    public boolean isElementEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    /**
     * Retrieves a list of web elements located by the given locator.
     *
     * @param locator the locator used to find the elements
     * @return a list of WebElements that match the locator
     */
    public List<WebElement> getListOfWebElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Retrieves a single web element located by the given locator, waiting for it to be present.
     *
     * @param locator the locator used to find the element
     * @return the WebElement that matches the locator
     */
    public WebElement getWebElement(By locator) {
        WebElement element = waitHelper.waitForElementToBePresent(locator);
        return element;
    }

    /**
     * Retrieves a single web element located by the given locator, waiting for it to be present within the specified timeout.
     *
     * @param locator          the locator used to find the element
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present
     * @return the WebElement that matches the locator
     */
    public WebElement getWebElement(By locator, int timeoutInSeconds) {
        WebElement element = waitHelper.waitForElementToBePresent(locator, timeoutInSeconds);
        return element;
    }

    /**
     * Highlights the specified element by adding a red dotted border and a yellow background.
     *
     * @param element the WebElement to be highlighted with a red dotted border and yellow background
     */
    public void highlightElement(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].setAttribute('style', " +
                "'border: 1px dotted red; background: yellow')", element);
    }
}