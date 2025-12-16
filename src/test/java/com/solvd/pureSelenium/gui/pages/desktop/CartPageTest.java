package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractTest;
import com.solvd.pureSelenium.gui.common.CartPageBase;
import com.solvd.pureSelenium.gui.common.ProductPageBase;
import com.solvd.pureSelenium.gui.common.SearchResultsPageBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends AbstractTest {

    @Test
    public void testAddProductToCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page didn't open.");

        homePage.clickMenCategoryBtn();
        SearchResultsPageBase searchResultsPage = homePage.searchProduct("Carhartt caps");
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search results page didn't open.");

        ProductPageBase productPage = searchResultsPage.selectFirstProductItem();
        Assert.assertTrue(productPage.isPageOpened(), "Product page didn't open.");
        productPage.clickAddToCartButton();

        CartPageBase cartPage = productPage.goToCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page didn't open.");
        Assert.assertTrue(cartPage.isItemPresentInCart(), "Item isn't present in the cart.");
    }

    @Test
    public void testRemoveProductFromCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page didn't open.");

        homePage.clickMenCategoryBtn();
        SearchResultsPageBase searchResultsPage = homePage.searchProduct("Carhartt caps");
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search results page didn't open.");

        ProductPageBase productPage = searchResultsPage.selectFirstProductItem();
        Assert.assertTrue(productPage.isPageOpened(), "Product page didn't open.");
        productPage.clickAddToCartButton();

        CartPageBase cartPage = productPage.goToCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page didn't open.");
        Assert.assertTrue(cartPage.isItemPresentInCart(), "Item isn't present in the cart.");

        cartPage.removeItemFromCart();
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart isn't empty.");
    }
}
