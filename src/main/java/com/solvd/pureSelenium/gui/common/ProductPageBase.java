package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPage {

    public ProductPageBase(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public abstract void clickAddToCartButton();

    public abstract CartPageBase goToCartPage();

    public abstract void closePopUpIfAppears();
}
