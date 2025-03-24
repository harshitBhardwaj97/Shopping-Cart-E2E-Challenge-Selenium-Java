package com.harshitbhardwaj.constants;

/**
 * A utility class that holds various constant values used throughout the framework.
 * <p>
 * This class cannot be instantiated.
 * </p>
 *
 * @author Harshit Bhardwaj
 */
public final class Constants {

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Can't instantiate Constants.class");
    }

    /**
     * Contains common constants used across the framework, such as URLs and wait times.
     * <p>
     * This class cannot be instantiated.
     * </p>
     */
    public static class Common {

        /**
         * The base URL for the application under test.
         */
        public static final String BASE_URL = "https://www.myntra.com/";

        /**
         * The explicit wait time for longer waits (in seconds).
         */
        public static final int EXPLICIT_LONG_WAIT = 10;

        /**
         * The explicit wait time for shorter waits (in seconds).
         */
        public static final int EXPLICIT_SHORT_WAIT = 5;


        // Private constructor to prevent instantiation
        private Common() {
            throw new AssertionError("Can't instantiate Constants.Common.class");
        }
    }
}