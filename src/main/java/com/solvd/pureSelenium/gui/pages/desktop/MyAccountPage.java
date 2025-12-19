package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountPage.class);

    private static final String PAGE_PATH = "/my-account/login";

    @FindBy(css = "input[id='_username']")
    private WebElement signInEmailInput;

    @FindBy(css = "input[id='_password']")
    private WebElement signInPasswordInput;

    @FindBy(css = "button[class='btn btnPrimary btnFluid']")
    private WebElement signInButton;

    @FindBy(css = "input[data-test='email']")
    private WebElement createAccountEmailInput;

    @FindBy(css = "input[id='plaintextPassword']")
    private WebElement createAccountPasswordInput;

    @FindBy(css = "span[data-test='agreement.terms_radio_0']")
    private WebElement acceptTermsAndConditionsCheckBox;

    @FindBy(css = "[data-test='registerStep'] button")
    private WebElement createAccountButton;

    @FindBy(css = "[data-test='registerStep'] span.fieldError")
    private WebElement emailAlreadyInUseError;

    public MyAccountPage(WebDriver driver) {
        super(driver, PAGE_PATH);
        PageFactory.initElements(driver, this);
    }

    public void signIn(String email, String password) {
        LOGGER.debug("Filling in user details...");
        getWait().until(ExpectedConditions.visibilityOf(signInEmailInput));
        signInEmailInput.sendKeys(email);
        signInPasswordInput.sendKeys(password);
        clickIfAppear(signInButton);

        switchToNewestWindow();
    }

    public void createNewAccount(String email, String password) {
        LOGGER.debug("Filling in new account details...");

        getWait().until(ExpectedConditions.visibilityOf(createAccountEmailInput));
        createAccountEmailInput.sendKeys(email);
        createAccountPasswordInput.sendKeys(password);

        clickIfAppear(acceptTermsAndConditionsCheckBox);
        clickIfAppear(createAccountButton);
    }

    public boolean isEmailAlreadyInUseMessageAppear() {
        getWait().until(ExpectedConditions.visibilityOf(emailAlreadyInUseError));
        return emailAlreadyInUseError.isDisplayed();
    }
}