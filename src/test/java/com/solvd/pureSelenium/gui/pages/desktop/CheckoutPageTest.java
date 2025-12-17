package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.solvd.pureSelenium.gui.utils.SystemUtils.*;

public class CheckoutPageTest extends AbstractTest {

    @Test
    public void testDeliveryDetailsIsAvailable() {
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
        cartPage.goToCheckout();

        String email = getTestEmail();
        String password = getTestPassword();

        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        myAccountPage.signIn(email, password);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        Assert.assertTrue(checkoutPage.isPageOpened(), "Checkout page didn't open.");
        Assert.assertTrue(checkoutPage.isCustomerDetailsPresent(), "Customer details isn't present.");
    }
}
