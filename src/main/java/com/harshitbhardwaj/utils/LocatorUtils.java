package com.harshitbhardwaj.utils;

/**
 * @author Harshit Bhardwaj
 */
public final class LocatorUtils {

    private LocatorUtils() {
        throw new AssertionError("Cannot instantiate LocatorUtils.class");
    }

    public static String createXpathFromSections(String... sections) {
        if (sections == null || sections.length == 0) {
            throw new IllegalArgumentException("Sections cannot be null or empty");
        }

        StringBuilder result = new StringBuilder("//");
        for (int i = 0; i < sections.length; i++) {
            if (sections[i].isEmpty()) {
                continue;
            }
            if (i != sections.length - 1) {
                result.append(sections[i]).append("//");
            } else {
                result.append(sections[i]);
            }
        }
        return result.toString();
    }
}
