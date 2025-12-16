package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.CheckoutPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends CheckoutPageBase {

    private static final String BASE_URL = "https://prm.com/us/checkout";

    @FindBy(css = "div[data-test='addressStep']")
    private WebElement customerDetails;

    public CheckoutPage(WebDriver driver) {
        super(driver, BASE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isCustomerDetailsPresent() {
        getWait().until(ExpectedConditions.visibilityOf(customerDetails));
        return customerDetails.isDisplayed();
    }
}
