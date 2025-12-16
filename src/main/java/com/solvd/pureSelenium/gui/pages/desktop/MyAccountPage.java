package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.HomePageBase;
import com.solvd.pureSelenium.gui.common.MyAccountPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPage extends MyAccountPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountPage.class);

    private static final String BASE_URL = "https://prm.com/us/my-account/login";

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

    @FindBy(css = "button[class='btn btnPrimary btnFluid RegisterStepForm__submitButton__Ct1wx']")
    private WebElement createAccountButton;

    @FindBy(css = "span[class='fieldError FieldError__error__eoDKL']")
    private WebElement emailAlreadyInUseMessage;

    public MyAccountPage(WebDriver driver) {
        super(driver, BASE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void signIn(String email, String password) {
        LOGGER.debug("Filling in user details...");
        getWait().until(ExpectedConditions.visibilityOf(signInEmailInput));
        signInEmailInput.sendKeys(email);
        signInPasswordInput.sendKeys(password);
        clickIfAppear(signInButton);

        switchToNewestWindow();
    }

    @Override
    public void createNewAccount(String email, String password) {
        LOGGER.debug("Filling in new account details...");

        getWait().until(ExpectedConditions.visibilityOf(createAccountEmailInput));
        createAccountEmailInput.sendKeys(email);
        createAccountPasswordInput.sendKeys(password);

        clickIfAppear(acceptTermsAndConditionsCheckBox);
        clickIfAppear(createAccountButton);
    }

    @Override
    public boolean isEmailAlreadyInUseMessageAppear() {
        getWait().until(ExpectedConditions.visibilityOf(emailAlreadyInUseMessage));
        return emailAlreadyInUseMessage.isDisplayed();
    }
}