package com.harshitbhardwaj.driver;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;

/**
 * A factory class to initialize WebDriver instances based on the browser name provided.
 * This class allows creating a WebDriver instance for supported browsers (Chrome, Firefox, Edge).
 * <p>
 * It prevents instantiation to ensure that the class is used as a utility for WebDriver initialization.
 *
 * @author Harshit Bhardwaj
 */
public class DriverFactory {

    /**
     * Private constructor to prevent instantiation of the class.
     * Throws an {@link AssertionError} if someone tries to instantiate the class.
     */
    private DriverFactory() {
        throw new AssertionError("Can't instantiate DriverFactory class.");
    }

    /**
     * Initializes a local WebDriver instance for a specified browser.
     * The method will throw a {@link SkipException} if the provided browser name is invalid.
     *
     * @param browser The browser name (e.g., "chrome", "firefox", "edge").
     * @return WebDriver instance for the specified browser.
     * @throws SkipException If an invalid browser name is provided.
     */
    public static WebDriver initLocalWebDriver(String browser) {
        String browserName = browser.trim().toUpperCase();
        try {
            return BrowserFactory.valueOf(browserName).createLocalDriver();
        } catch (IllegalArgumentException e) {
            // If browser is invalid, throw a SkipException with a meaningful message
            String errorMessage = """
                    Invalid browser name provided: %s.
                    Only the following browser names are allowed: 'chrome', 'firefox', 'edge'.
                    """.formatted(browserName);
            System.err.println(errorMessage);
            throw new SkipException(errorMessage, e);
        }
    }
}