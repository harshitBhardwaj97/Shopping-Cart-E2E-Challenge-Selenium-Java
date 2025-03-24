package com.harshitbhardwaj.listener;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import static com.harshitbhardwaj.config.ConfigurationManager.configuration;
import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;
import static com.harshitbhardwaj.constants.Constants.Common.EXPLICIT_LONG_WAIT;

/**
 * This class is responsible for generating and setting environment information for Allure reports.
 * It gathers information such as the application under test URL, timeout duration, headless mode configuration,
 * and the browser used for execution, then writes this information into the Allure environment file.
 *
 * @author Harshit Bhardwaj
 */
public class AllureReportHelper {

    // Private constructor to prevent instantiation
    private AllureReportHelper() {
    }

    /**
     * Collects environment information such as the application URL, timeout duration, headless mode status,
     * and the local browser used for execution. This information is then written to the Allure report environment
     * file using AllureEnvironmentWriter.
     * <p>
     * The environment information includes:
     * - URL of Application Under Test
     * - Default Timeout Duration
     * - Headless mode status
     * - Local browser used for execution
     * </p>
     * <p>
     * This method is typically called before the test suite execution, to capture the environment details.
     */
    public static void setAllureEnvironmentInformation() {
        // Prepare the environment information map
        var allureEnvironmentInformation = new HashMap<>(Map.of(
                "URL of Application Under Test", BASE_URL,
                "Default Timeout Duration", String.valueOf(EXPLICIT_LONG_WAIT),
                "Headless mode", String.valueOf(configuration().headless()),
                "Local browser used for execution", configuration().browser()
        ));

        // Write the environment information to Allure report
        AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.copyOf(allureEnvironmentInformation));
    }
}