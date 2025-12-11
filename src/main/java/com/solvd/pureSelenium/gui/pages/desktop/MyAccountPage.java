package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.MyAccountPageBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends MyAccountPageBase {

    private static final Logger LOGGER = LogManager.getLogger(MyAccountPage.class);

    private static final String BASE_URL = "my-account/login";

    @FindBy(css = "input[id='_username']")
    private WebElement emailInput;

    @FindBy(css = "input[id='_password']")
    private WebElement passwordInput;

    @FindBy(css = "button[class='btn btnPrimary btnFluid']")
    private WebElement submitButton;

    public MyAccountPage(WebDriver driver) {
        super(driver, BASE_URL);
        PageFactory.initElements(driver, this);
    }

    public void signIn(String email, String password) {
        LOGGER.debug("Filling in user details...");
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitButton.click();

        switchToNewestWindow();
    }
}