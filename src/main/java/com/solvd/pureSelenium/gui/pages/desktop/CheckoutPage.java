package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends AbstractPage {

    @FindBy(css = "div[data-test='addressStep']")
    private ExtendedWebElement customerDetails;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCustomerDetailsPresent() {
        customerDetails.isVisible(1500);
        return customerDetails.isDisplayed();
    }
}