package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductListPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListPage.class);

    private static final String PAGE_PATH = "/p";

    @FindBy(css = "button[data-test='add_to_cart']")
    private WebElement addToCartButton;

    @FindBy(css = "a[data-test='cart_icon']")
    private WebElement cartIcon;

    @FindBy(css = "span[data-test='cart_count']")
    private WebElement cartItemCountIcon;

    @FindBy(css = "div[data-test='modal-close-button']")
    private WebElement closePopUpIcon;

    public ProductListPage(WebDriver driver) {
        super(driver, PAGE_PATH);
        PageFactory.initElements(driver, this);
    }

    public void clickAddToCartButton() {
        LOGGER.info("Click on \"Add to cart\" button");
        clickIfAppear(addToCartButton);
    }

    public CartPage goToCartPage() {
        getWait().until(ExpectedConditions.visibilityOf(cartItemCountIcon));
        LOGGER.info("Click on cart icon");
        clickIfAppear(cartIcon);
        return new CartPage(getDriver());
    }

    public void closePopUpIfAppears() {
        LOGGER.info("Closing pop-up");
        clickIfAppear(closePopUpIcon);
    }
}
