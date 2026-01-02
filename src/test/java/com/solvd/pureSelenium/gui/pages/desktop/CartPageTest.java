package com.solvd.pureSelenium.gui.pages.desktop;

import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest implements IAbstractTest {

    @Test
    public void testAddProductToCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page didn't open.");

        homePage.clickMenCategoryBtn();
        SearchResultsPage searchResultsPage = homePage.searchProduct("Puma caps");
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search results page didn't open.");

        ProductListPage productListPage = searchResultsPage.selectFirstProductItem();
        productListPage.closePopUpIfAppears();
        Assert.assertTrue(productListPage.isPageOpened(), "Product page didn't open.");
        productListPage.clickAddToCartButton();

        CartPage cartPage = productListPage.goToCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page didn't open.");
        Assert.assertTrue(cartPage.isItemPresentInCart(), "Item isn't present in the cart.");
    }

    @Test
    public void testRemoveProductFromCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page didn't open.");

        homePage.clickMenCategoryBtn();
        SearchResultsPage searchResultsPage = homePage.searchProduct("Puma caps");
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search results page didn't open.");

        ProductListPage productListPage = searchResultsPage.selectFirstProductItem();
        productListPage.closePopUpIfAppears();
        Assert.assertTrue(productListPage.isPageOpened(), "Product page didn't open.");
        productListPage.clickAddToCartButton();

        CartPage cartPage = productListPage.goToCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page didn't open.");
        Assert.assertTrue(cartPage.isItemPresentInCart(), "Item isn't present in the cart.");

        cartPage.removeItemFromCart();
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart isn't empty.");
    }
}
