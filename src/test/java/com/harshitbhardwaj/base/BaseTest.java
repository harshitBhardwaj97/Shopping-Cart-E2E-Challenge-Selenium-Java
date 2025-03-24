package com.harshitbhardwaj.base;

import com.harshitbhardwaj.driver.DriverFactory;
import com.harshitbhardwaj.driver.WebDriverManager;
import com.harshitbhardwaj.listener.AllureReportHelper;
import com.harshitbhardwaj.pages.HomePage;
import com.harshitbhardwaj.support.CommonPageOperations;
import com.harshitbhardwaj.support.PageInteractionHelper;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.harshitbhardwaj.config.ConfigurationManager.configuration;

/**
 * BaseTest is the base class for all test classes. It provides common setup and tearDown methods
 * for managing WebDriver and initializing page object helpers.
 * <p>
 * The {@link #setup()} method initializes the WebDriver based on the browser configuration
 * defined in the `general.properties` file. It also creates instances of page object helpers
 * like {@link CommonPageOperations} and {@link PageInteractionHelper}.
 * <p>
 * The {@link #tearDown()} method is responsible for quitting the WebDriver session after each test.
 *
 * @author Harshit Bhardwaj
 */
public class BaseTest {

    // WebDriver instance for the test thread
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Page object helpers
    protected CommonPageOperations page;
    protected PageInteractionHelper pageInteractionHelper;
    protected HomePage homePage;

    /**
     * Initializes Allure environment information before the test suite starts.
     * This method is annotated with {@link BeforeSuite} and is executed once
     * before the suite of tests begins.
     *
     * @see AllureReportHelper#setAllureEnvironmentInformation()
     */
    @BeforeSuite
    public void beforeSuite() {
        AllureReportHelper.setAllureEnvironmentInformation();
    }

    /**
     * Sets up the WebDriver and page helpers before each test.
     * Initializes WebDriver based on the browser defined in the configuration file.
     * If the browser is not defined or invalid, the test will be skipped.
     *
     * @throws SkipException if the browser is not configured properly or is invalid.
     */
    @BeforeMethod
    public void setup() {
        var browser = configuration().browser();

        // Check if the browser is null, skip the test if so
        if (browser == null) {
            String errorMessage = """
                    Make sure that general.properties file exists and the browser name provided in
                    the file is valid.
                    Add browser=chrome or browser=firefox or browser=edge to the file.
                    The general.properties file should exist under src/main/resources directory.
                    """;
            System.err.println(errorMessage);
            throw new SkipException(errorMessage);
        }

        // Initialize the WebDriver and other page objects after validation
        WebDriver initializedDriver = DriverFactory.initLocalWebDriver(browser);
        WebDriverManager.setDriver(initializedDriver);
        page = new CommonPageOperations(WebDriverManager.getDriver());
        pageInteractionHelper = new PageInteractionHelper(WebDriverManager.getDriver());
        homePage = new HomePage(pageInteractionHelper);
    }

    /**
     * Tears down the WebDriver session after each test.
     * Quits the driver session if it's still running and removes the driver from the thread-local storage.
     */
    @AfterMethod
    public void tearDown() {
        if (WebDriverManager.getDriver() != null) {
            System.out.println("Quitting the session");
            WebDriverManager.quitDriver();
        }
    }
}
