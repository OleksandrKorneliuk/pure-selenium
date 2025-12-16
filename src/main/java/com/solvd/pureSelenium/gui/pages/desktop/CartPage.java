package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.CartPageBase;
import com.solvd.pureSelenium.gui.common.CheckoutPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends CartPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartPage.class);

    private static final String BASE_URL = "https://prm.com/us/cart";

    @FindBy(css = "div[data-test='cartItem']")
    private WebElement cartItem;

    @FindBy(css = "button[data-test='cartRemoveItem']")
    private WebElement removeItemButton;

    @FindBy(css = "span[class='EmptyCart__emptyCartHeaderText__ncH4y']")
    private WebElement cartIsEmptyLabel;

    @FindBy(css = "a[class='btn btnPrimary EmptyCart__emptyCartButton__8cAr7']")
    private WebElement homePageButton;

    @FindBy(css = "a[class='btn btnPrimary btnFluid']")
    private WebElement goToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver, BASE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isItemPresentInCart() {
        getWait().until(ExpectedConditions.visibilityOf(cartItem));
        return cartItem.isDisplayed();
    }

    @Override
    public void removeItemFromCart() {
        LOGGER.info("Click on \"Remove item\" button");
        clickIfAppear(removeItemButton);
    }

    @Override
    public boolean isCartEmpty() {
        getWait().until(ExpectedConditions.visibilityOf(cartIsEmptyLabel));
        return cartIsEmptyLabel.isDisplayed() && homePageButton.isDisplayed();
    }

    @Override
    public CheckoutPageBase goToCheckout() {
        LOGGER.info("Click on \"Checkout\" button");
        clickIfAppear(goToCheckoutButton);
        return new CheckoutPage(getDriver());
    }
}
