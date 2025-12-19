package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends AbstractPage {

    private static final String PAGE_PATH = "/checkout";

    @FindBy(css = "div[data-test='addressStep']")
    private WebElement customerDetails;

    public CheckoutPage(WebDriver driver) {
        super(driver, PAGE_PATH);
        PageFactory.initElements(driver, this);
    }

    public boolean isCustomerDetailsPresent() {
        getWait().until(ExpectedConditions.visibilityOf(customerDetails));
        return customerDetails.isDisplayed();
    }
}
