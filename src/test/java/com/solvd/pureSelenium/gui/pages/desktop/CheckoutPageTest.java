package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.solvd.pureSelenium.gui.utils.SystemUtils.getEnvVariable;

public class CheckoutPageTest extends AbstractTest {

    @Test
    public void testDeliveryDetailsIsAvailable() {
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

        CheckoutPageBase checkoutPage = cartPage.goToCheckout();
        MyAccountPageBase myAccountPage = new MyAccountPage(getDriver());

        String email = getEnvVariable("TEST_EMAIL");
        String password = getEnvVariable("TEST_PASSWORD");
        myAccountPage.signIn(email, password);

        Assert.assertTrue(checkoutPage.isPageOpened(), "Checkout page didn't open.");
        Assert.assertTrue(checkoutPage.isCustomerDetailsPresent(), "Customer details isn't present.");
    }
}
