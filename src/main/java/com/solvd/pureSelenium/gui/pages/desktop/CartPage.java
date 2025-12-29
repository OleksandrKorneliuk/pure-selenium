package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartPage.class);

    @FindBy(css = "div[data-test='cartItem']")
    private ExtendedWebElement cartItem;

    @FindBy(css = "button[data-test='cartRemoveItem']")
    private ExtendedWebElement removeItemButton;

    @FindBy(css = "span[class*='emptyCartHeaderText']")
    private ExtendedWebElement cartIsEmptyLabel;

    @FindBy(css = "[data-test='cartEmpty'] .btn.btnPrimary")
    private ExtendedWebElement homePageButton;

    @FindBy(css = "a[class='btn btnPrimary btnFluid']")
    private ExtendedWebElement goToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemPresentInCart() {
        return cartItem.isPresent();
    }

    public void removeItemFromCart() {
        LOGGER.info("Click on \"Remove item\" button");
        removeItemButton.clickIfPresent();
    }

    public boolean isCartEmpty() {
        cartIsEmptyLabel.isVisible(1500);
        return cartIsEmptyLabel.isDisplayed() && homePageButton.isDisplayed();
    }

    public CheckoutPage goToCheckout() {
        LOGGER.info("Click on \"Checkout\" button");
        goToCheckoutButton.clickIfPresent();
        return new CheckoutPage(getDriver());
    }
}