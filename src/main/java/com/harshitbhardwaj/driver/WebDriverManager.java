package com.harshitbhardwaj.driver;

import org.openqa.selenium.WebDriver;

/**
 * A utility class for managing WebDriver instances in a thread-safe manner.
 * This class ensures that a single WebDriver instance is used within the current thread of execution.
 * The WebDriver instance is stored in a {@link ThreadLocal} to make it thread-safe for parallel execution.
 *
 * @author Harshit Bhardwaj
 */
public class WebDriverManager {

    // ThreadLocal to store WebDriver instances specific to the current thread
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Private constructor to prevent instantiation of the class.
     * Throws an {@link AssertionError} if someone tries to instantiate the class.
     */
    private WebDriverManager() {
        throw new AssertionError("Can't instantiate DriverManager class.");
    }

    /**
     * Gets the WebDriver instance associated with the current thread.
     *
     * @return The WebDriver instance associated with the current thread.
     * @throws IllegalArgumentException If the WebDriver has not been set using {@link #setDriver(WebDriver)}.
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalArgumentException("Set appropriate driver first, using setDriver");
        }
        return driver.get();
    }

    /**
     * Sets the WebDriver instance for the current thread.
     * This method associates the provided WebDriver with the current thread of execution.
     *
     * @param driver The WebDriver instance to be associated with the current thread.
     * @throws IllegalArgumentException If the provided driver is null.
     */
    public static void setDriver(WebDriver driver) {
        System.out.printf("*** Setting WebDriver: %s *** \n", driver);
        if (driver == null) {
            throw new IllegalArgumentException("driver cannot be null");
        }
        WebDriverManager.driver.set(driver);
    }

    /**
     * Quits the WebDriver instance associated with the current thread and removes it from the thread-local storage.
     * This method should be called to clean up the WebDriver instance once the test is complete.
     */
    public static void quitDriver() {
        WebDriverManager.driver.get().quit();
        driver.remove();
    }
}