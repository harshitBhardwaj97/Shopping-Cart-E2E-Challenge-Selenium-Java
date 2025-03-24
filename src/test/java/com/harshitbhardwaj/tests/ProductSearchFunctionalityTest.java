package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.driver.WebDriverManager;
import com.harshitbhardwaj.model.Item;
import com.harshitbhardwaj.pages.CheckoutPage;
import com.harshitbhardwaj.pages.ItemInfoPage;
import com.harshitbhardwaj.pages.ItemSelectionPage;
import com.harshitbhardwaj.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

/**
 * @author Harshit Bhardwaj
 */
public class ProductSearchFunctionalityTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductSearchFunctionalityTest.class);
    private final String randomCategoryText = Utils.getRandomMenuCategory();
    private ItemInfoPage itemInfoPage;
    private ItemSelectionPage itemSelectionPage;
    private CheckoutPage checkoutPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        WebDriverManager.getDriver().get(BASE_URL);
        itemInfoPage = new ItemInfoPage(pageInteractionHelper);
        itemSelectionPage = new ItemSelectionPage(pageInteractionHelper);
        checkoutPage = new CheckoutPage(pageInteractionHelper);
    }

    @Test
    public void selectedItemDetailsMatchesTest() throws InterruptedException {

        /*
        First of all, get a random menu category, and from that menu category, select a random item category
        */
        logger.info("*** Hovering on {} category ***", randomCategoryText);
        homePage.hoverOnRandomMenuCategory(randomCategoryText);

        List<String> subCategories = homePage.getListOfSubCategories(randomCategoryText);
        String randomItemCategory = Utils.getRandomItemFromList(subCategories);

        logger.info("*** Selecting '{}' item from '{}' category ***", randomItemCategory, randomCategoryText);
        homePage.clickOnSubCategoryItem(randomCategoryText, randomItemCategory);

        itemSelectionPage.selectFirstItemAndSwitchWindow();

        Item selectedItemInfo = itemInfoPage.getItemInfo();
        System.out.println("Selected item is :- ");
        System.out.println(selectedItemInfo);

        itemInfoPage.clickOnAddToBagButton();
        itemInfoPage.clickOnGoToBagButton();

        SoftAssert softAssert = new SoftAssert();

        // Perform the soft assertions
        softAssert.assertEquals(checkoutPage.getCheckoutPageItemName(), selectedItemInfo.getItemName());
        softAssert.assertEquals(checkoutPage.getCheckoutPageItemTitle(), selectedItemInfo.getItemTitle());
        softAssert.assertTrue(checkoutPage.getCheckoutPageItemSellerInfo().contains(selectedItemInfo.getItemSeller()));
        softAssert.assertTrue(checkoutPage.getCheckoutPageItemSize().contains(selectedItemInfo.getItemSize()));
        softAssert.assertEquals(checkoutPage.getCheckoutPageItemTotal(), checkoutPage.calculateTotal());

        softAssert.assertAll();

        // For demo recording
        Thread.sleep(3000);
    }
}
