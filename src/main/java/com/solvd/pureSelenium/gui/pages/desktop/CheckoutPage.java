package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.BasePage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    private static final String PAGE_PATH = "/checkout";

    @FindBy(css = "div[data-test='addressStep']")
    private ExtendedWebElement addressStepSection;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        setPageURL(PAGE_PATH);
    }

    public boolean isCustomerDetailsPresent() {
        addressStepSection.isVisible(1500);
        return addressStepSection.isDisplayed();
    }
}