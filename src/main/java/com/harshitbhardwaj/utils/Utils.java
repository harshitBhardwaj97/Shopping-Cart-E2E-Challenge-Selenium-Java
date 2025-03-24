package com.harshitbhardwaj.utils;

import io.qameta.allure.Step;

import java.util.List;

/**
 * @author Harshit Bhardwaj
 */
public final class Utils {
    private static final String[] menuCategories = {"Men", "Women", "Kids", "Home & Living", "Beauty"};

    private Utils() {
        throw new AssertionError("Can't instantiate Utils.class");
    }

    private static <T> T getRandomItemFromArray(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        return array[(int) (Math.random() * array.length)];
    }

    public static <T> T getRandomItemFromList(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }
        return list.get((int) (Math.random() * list.size()));
    }

    public static String getRandomMenuCategory() {
        return getRandomItemFromArray(menuCategories);
    }

    @Step("Extracting the item price")
    public static int extractItemPrice(String itemPriceText) {
        return Integer.parseInt(itemPriceText.replaceAll("[^0-9.]", ""));
    }
}
