package com.harshitbhardwaj.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

/**
 * This interface defines the configuration properties for the application.
 * The properties are loaded from system properties and a properties file (general.properties).
 * <p>
 * The @LoadPolicy(MERGE) annotation ensures that values from both sources (system properties
 * and classpath properties) are merged, with system properties taking precedence.
 * <p>
 * The properties include the browser configuration, headless mode, and the base URL for the application.
 *
 * @author Harshit Bhardwaj
 */
@LoadPolicy(LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:general.properties"})
public interface Configuration extends Config {

    /**
     * Retrieves the browser type for the application.
     *
     * @return the browser type (e.g., "chrome", "firefox", etc.)
     */
    @Key("browser")
    String browser();

    /**
     * Retrieves the headless mode setting for the browser.
     *
     * @return true if headless mode is enabled, false otherwise. Defaults to false.
     */
    @Key("headless")
    @DefaultValue("false")
    Boolean headless();
}