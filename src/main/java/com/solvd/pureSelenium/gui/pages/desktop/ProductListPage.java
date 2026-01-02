package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.BasePage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductListPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListPage.class);

    private static final String PAGE_PATH = "/p";

    @FindBy(css = "button[data-test='add_to_cart']")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = "a[data-test='cart_icon']")
    private ExtendedWebElement cartIcon;

    @FindBy(css = "span[data-test='cart_count']")
    private ExtendedWebElement cartItemCountIcon;

    @FindBy(css = "div[data-test='modal-close-button']")
    private ExtendedWebElement closePopUpIcon;

    public ProductListPage(WebDriver driver) {
        super(driver);
        setPageURL(PAGE_PATH);
    }

    public void clickAddToCartButton() {
        LOGGER.info("Click on \"Add to cart\" button");
        addToCartButton.clickIfPresent();
    }

    public CartPage goToCartPage() {
        cartItemCountIcon.isVisible(1000);
        LOGGER.info("Click on cart icon");
        cartIcon.clickIfPresent();
        return new CartPage(getDriver());
    }

    public void closePopUpIfAppears() {
        LOGGER.info("Closing pop-up");
        closePopUpIcon.clickIfPresent();
    }
}