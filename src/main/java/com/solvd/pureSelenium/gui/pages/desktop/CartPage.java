package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartPage.class);

    private static final String PAGE_PATH = "/cart";

    @FindBy(css = "div[data-test='cartItem']")
    private WebElement cartItem;

    @FindBy(css = "button[data-test='cartRemoveItem']")
    private WebElement removeItemButton;

    @FindBy(css = "span[class*='emptyCartHeaderText']")
    private WebElement cartIsEmptyLabel;

    @FindBy(css = "[data-test='cartEmpty'] .btn.btnPrimary")
    private WebElement homePageButton;

    @FindBy(css = "a[class='btn btnPrimary btnFluid']")
    private WebElement goToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver, PAGE_PATH);
        PageFactory.initElements(driver, this);
    }

    public boolean isItemPresentInCart() {
        getWait().until(ExpectedConditions.visibilityOf(cartItem));
        return cartItem.isDisplayed();
    }

    public void removeItemFromCart() {
        LOGGER.info("Click on \"Remove item\" button");
        clickIfAppear(removeItemButton);
    }

    public boolean isCartEmpty() {
        getWait().until(ExpectedConditions.visibilityOf(cartIsEmptyLabel));
        return cartIsEmptyLabel.isDisplayed() && homePageButton.isDisplayed();
    }

    public CheckoutPage goToCheckout() {
        LOGGER.info("Click on \"Checkout\" button");
        clickIfAppear(goToCheckoutButton);
        return new CheckoutPage(getDriver());
    }
}
