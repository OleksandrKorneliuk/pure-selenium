package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.CartPageBase;
import com.solvd.pureSelenium.gui.common.ProductPageBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends ProductPageBase {

    private static final Logger LOGGER = LogManager.getLogger(ProductPage.class);

    private static final String BASE_URL = "https://prm.com/us/p/";

    @FindBy(css = "button[data-test='add_to_cart']")
    private WebElement addToCartButton;

    @FindBy(css = "a[data-test='cart_icon']")
    private WebElement cartIcon;

    @FindBy(css = "span[data-test='cart_count']")
    private WebElement cartItemCountIcon;

    @FindBy(css = "div[data-test='modal-close-button']")
    private WebElement closePopUpIcon;

    public ProductPage(WebDriver driver) {
        super(driver, BASE_URL);
        PageFactory.initElements(driver, this);
        closePopUpIfAppears();
    }

    @Override
    public void clickAddToCartButton() {
        LOGGER.info("Click on \"Add to cart\" button");
        clickIfAppear(addToCartButton);
    }

    @Override
    public CartPageBase goToCartPage() {
        getWait().until(ExpectedConditions.visibilityOf(cartItemCountIcon));
        LOGGER.info("Click on cart icon");
        clickIfAppear(cartIcon);
        return new CartPage(getDriver());
    }

    @Override
    public void closePopUpIfAppears() {
        LOGGER.info("Closing pop-up");
        clickIfAppear(closePopUpIcon);
    }
}
