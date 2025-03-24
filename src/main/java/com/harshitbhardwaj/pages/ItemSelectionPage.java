package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.utils.LocatorUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Set;

/**
 * @author Harshit Bhardwaj
 */
public class ItemSelectionPage {

    private final PageInteractionHelper pageInteractionHelper;
    private final String itemTemplate = LocatorUtils
            .createXpathFromSections("div[contains(@class,'search-searchProductsContainer')]", "ul", "li");
    private final By currentItem = By.xpath("(" + itemTemplate + ")[1]");

    public ItemSelectionPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Selecting the first item on item selection page, and switching to new window")
    public void selectFirstItemAndSwitchWindow() {
        pageInteractionHelper.clickOnElement(currentItem);

        String currentWindowHandle = pageInteractionHelper.getWindowHandle();
        Set<String> allWindowHandles = pageInteractionHelper.getAllWindowHandles();

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                pageInteractionHelper.switchToWindow(windowHandle);
                break;
            }
        }
    }
}
