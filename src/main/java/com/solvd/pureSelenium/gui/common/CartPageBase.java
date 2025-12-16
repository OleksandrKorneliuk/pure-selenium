package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.WebDriver;

public abstract class CartPageBase extends AbstractPage {

    public CartPageBase(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public abstract boolean isItemPresentInCart();

    public abstract boolean isCartEmpty();

    public abstract void removeItemFromCart();

    public abstract CheckoutPageBase goToCheckout();
}
