package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Harshit Bhardwaj
 */
public class HomePage {

    private final PageInteractionHelper pageInteractionHelper;

    public HomePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Hovering on random menu category")
    public void hoverOnRandomMenuCategory(String menuCategoryText) {
        String menuCategoryTemplate = "//a[.='%s']";
        By menuCategory = By.xpath(menuCategoryTemplate.formatted(menuCategoryText));
        pageInteractionHelper.moveToElement(menuCategory);
    }

    @Step("Getting list of sub categories")
    public List<String> getListOfSubCategories(String menuCategoryText) {
        String subCategoriesItemTemplate = "//a[.='%s']//following-sibling::div//ul//li//a[not(contains(@class,'categoryName'))]";

        List<WebElement> subCategoryElements = pageInteractionHelper
                .getListOfWebElements(By.xpath(subCategoriesItemTemplate.formatted(menuCategoryText)));

        return subCategoryElements.stream().map(WebElement::getText).filter(Predicate.not(String::isEmpty)).toList();
    }

    @Step("Clicking on random sub category item")
    public void clickOnSubCategoryItem(String menuCategoryText, String subMenuCategoryText) {
        String subCategoryMenuItemTemplate = "//a[.='%s']//following-sibling::div//ul//li//a[.='%s']";
        By subCategoryMenuItem = By.xpath(subCategoryMenuItemTemplate.formatted(menuCategoryText, subMenuCategoryText));
        pageInteractionHelper.clickOnElement(subCategoryMenuItem);
    }
}
