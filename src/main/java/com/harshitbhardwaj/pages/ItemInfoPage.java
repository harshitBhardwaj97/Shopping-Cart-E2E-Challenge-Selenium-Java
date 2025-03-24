package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.model.Item;
import com.harshitbhardwaj.support.PageInteractionHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.utils.LocatorUtils.createXpathFromSections;
import static com.harshitbhardwaj.utils.Utils.extractItemPrice;

/**
 * @author Harshit Bhardwaj
 */
public class ItemInfoPage {

    private final PageInteractionHelper pageInteractionHelper;

    private final By itemTitle = By.xpath("//h1[contains(@class,'title')]");
    private final By itemName = By.xpath("//h1[contains(@class,'name')]");
    private final By addToBagButton = By.xpath("//div[contains(@class,'add-to-bag') and .='ADD TO BAG']");
    private final By goToBagButton = By.xpath("//span[contains(.,'GO TO BAG')]");
    private final String itemSizeTemplate = createXpathFromSections("div[contains(@class,'size-buttons-size-buttons')]", "div[@class='size-buttons-tipAndBtnContainer']", "button[not(contains(@class,'disabled'))]", "p[@class='size-buttons-unified-size']");
    private final By itemSize = By.xpath("(" + itemSizeTemplate + ")[1]");
    private final By itemPrice = By.xpath("//p[@class='pdp-discount-container']//span[@class='pdp-price']");
    private final By itemMRP = By.xpath("//p[@class='pdp-discount-container']//span[@class='pdp-mrp']");
    private final By sellerInfo = By.xpath("//span[contains(@class,'sellerButton')]");

    public ItemInfoPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Selecting the size and returning its text")
    private String selectSizeAndReturnText() {
        pageInteractionHelper.clickOnElement(itemSize);
        return pageInteractionHelper.getText(itemSize);
    }

    @Step("Clicking on add to bag button")
    public void clickOnAddToBagButton() {
        pageInteractionHelper.clickOnElement(addToBagButton);
    }

    @Step("Clicking on go to bag button")
    public void clickOnGoToBagButton() {
        pageInteractionHelper.clickOnElement(goToBagButton);
    }

    @Step("Creating the item based on the details")
    public Item getItemInfo() {
        return new Item(getItemName(), getItemTitle(), selectSizeAndReturnText(),
                getItemSellerInfo(), getItemPrice(), getItemMRP());
    }

    @Step("Getting the item title")
    private String getItemTitle() {
        return pageInteractionHelper.getText(itemTitle);
    }

    @Step("Getting the item name")
    private String getItemName() {
        return pageInteractionHelper.getText(itemName);
    }

    @Step("Checking if item is discounted")
    private boolean isItemDiscounted() {
        // Item is discounted when MRP of item is shown
        return pageInteractionHelper.isElementDisplayed(itemMRP);
    }

    @Step("Getting item MRP")
    private int getItemMRP() {
        /*
            If MRP is shown, then return it back, else MRP = Item Price, so return back itemPrice only
        */
        if (isItemDiscounted()) {
            return extractItemPrice(pageInteractionHelper.getText(itemMRP));
        }
        return extractItemPrice(pageInteractionHelper.getText(itemPrice));
    }

    @Step("Getting item Price")
    private int getItemPrice() {
        return extractItemPrice(pageInteractionHelper.getText(itemPrice));
    }

    @Step("Getting the seller info")
    private String getItemSellerInfo() {
        return pageInteractionHelper.getText(sellerInfo);
    }
}
