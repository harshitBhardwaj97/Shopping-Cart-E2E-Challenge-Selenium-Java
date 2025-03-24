package com.harshitbhardwaj.config;

import org.aeonbits.owner.ConfigCache;

/**
 * This class is responsible for managing the configuration properties.
 * It provides a method to retrieve the configuration instance using the `ConfigCache`.
 * <p>
 * The `configuration()` method returns a singleton instance of the Configuration interface,
 * ensuring that the properties are loaded only once and can be accessed throughout the application.
 *
 * @author Harshit Bhardwaj
 */
public class ConfigurationManager {

    /**
     * Private constructor to prevent instantiation of the ConfigurationManager class.
     * This class is meant to be used statically.
     */
    private ConfigurationManager() {
        throw new AssertionError("Can't instantiate ConfigurationManager class");
    }

    /**
     * Retrieves the configuration instance using ConfigCache.
     *
     * @return the Configuration instance containing the application properties.
     */
    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}