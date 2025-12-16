package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.WebDriver;

public abstract class CheckoutPageBase extends AbstractPage {

    public CheckoutPageBase(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public abstract boolean isCustomerDetailsPresent();
}
