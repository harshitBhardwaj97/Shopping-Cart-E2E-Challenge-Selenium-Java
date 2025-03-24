package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.utils.LocatorUtils.createXpathFromSections;
import static com.harshitbhardwaj.utils.Utils.extractItemPrice;

/**
 * @author Harshit Bhardwaj
 */
public class CheckoutPage {

    private final PageInteractionHelper pageInteractionHelper;

    private final By checkoutPageItemTitle = By.xpath("//div[contains(@class,'itemContainer-base-brand')]");
    private final By checkoutPageItemName = By.xpath("//a[contains(@class,'itemContainer-base-itemLink')]");
    private final By checkoutPageItemSize = By.xpath("//div[contains(@class,'itemComponents-base-size')]");
    private final By checkoutPageItemSeller = By.xpath("//div[contains(@class,'itemComponents-base-sellerData')]");
    private final String checkoutPageItemMRPTemplate = createXpathFromSections("span[.='Total MRP']", "parent::div", "span[@class='priceDetail-base-value ']", "span[.='₹']", "parent::span");
    private final By checkoutPageItemMRP = By.xpath("(" + checkoutPageItemMRPTemplate + ")[1]");
    private final By checkoutPageItemDiscountRow = By.xpath("//span[.='Discount on MRP']");
    private final String checkoutPageItemDiscountTemplate = createXpathFromSections("span[.='Discount on MRP']", "following::span[@class='priceDetail-base-value priceDetail-base-discount']", "span");
    private final By checkoutPageItemDiscount = By.xpath("(" + checkoutPageItemDiscountTemplate + ")[2]");
    private final String checkoutPagePlatformFeeTemplate = createXpathFromSections("span[contains(.,'Platform Fee')]",
            "following::span[@class='priceDetail-base-value']");
    private final By checkoutPagePlatformFee = By.xpath("(" + checkoutPagePlatformFeeTemplate + ")[1]");
    private final String checkoutPageItemTotalTemplate = createXpathFromSections("span[contains(.,'Total Amount')]",
            "following::span[contains(@class,'priceDetail-base-redesignRupeeTotalIcon')]", "parent::span");
    private final By checkoutPageItemTotal = By.xpath("(" + checkoutPageItemTotalTemplate + ")[1]");

    public CheckoutPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Getting the item title on checkout page")
    public String getCheckoutPageItemTitle() {
        return pageInteractionHelper.getText(checkoutPageItemTitle);
    }

    @Step("Getting the item name on checkout page")
    public String getCheckoutPageItemName() {
        return pageInteractionHelper.getText(checkoutPageItemName);
    }

    @Step("Getting the item size info on checkout page")
    public String getCheckoutPageItemSize() {
        return pageInteractionHelper.getText(checkoutPageItemSize);
    }

    @Step("Getting the item seller info on checkout page")
    public String getCheckoutPageItemSellerInfo() {
        return pageInteractionHelper.getText(checkoutPageItemSeller);
    }

    @Step("Getting the item total on checkout page")
    public int getCheckoutPageItemTotal() {
        return extractItemPrice(pageInteractionHelper.getText(checkoutPageItemTotal));
    }

    @Step("Getting the item MRP on checkout page")
    private int getCheckoutPageItemMRP() {
        return extractItemPrice(pageInteractionHelper.getText(checkoutPageItemMRP));
    }

    @Step("Getting the item discount on checkout page")
    private int getCheckoutPageItemDiscount() {
        if (pageInteractionHelper.isElementDisplayed(checkoutPageItemDiscountRow)) {
            return extractItemPrice(pageInteractionHelper.getText(checkoutPageItemDiscount));
        }
        return 0;
    }

    @Step("Getting the platform fee on checkout page")
    private int getCheckoutPagePlatformFee() {
        return extractItemPrice(pageInteractionHelper.getText(checkoutPagePlatformFee));
    }

    @Step("Calculating the total cost")
    public int calculateTotal() {
        // Total = MRP - Discount + Platform Fee
        System.out.println("MRP :" + getCheckoutPageItemMRP());
        System.out.println("Discount :" + getCheckoutPageItemDiscount());
        System.out.println("Fee :" + getCheckoutPagePlatformFee());
        System.out.println("Total :" + getCheckoutPageItemTotal());
        return getCheckoutPageItemMRP() - getCheckoutPageItemDiscount() + getCheckoutPagePlatformFee();
    }
}
