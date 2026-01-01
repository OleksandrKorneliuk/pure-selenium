package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.BasePage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountPage.class);

    private static final String PAGE_PATH = "/my-account/login";

    @FindBy(css = "input[id='_username']")
    private ExtendedWebElement signInEmailInput;

    @FindBy(css = "input[id='_password']")
    private ExtendedWebElement signInPasswordInput;

    @FindBy(css = "button[class='btn btnPrimary btnFluid']")
    private ExtendedWebElement signInButton;

    @FindBy(css = "input[data-test='email']")
    private ExtendedWebElement createAccountEmailInput;

    @FindBy(css = "input[id='plaintextPassword']")
    private ExtendedWebElement createAccountPasswordInput;

    @FindBy(css = "span[data-test='agreement.terms_radio_0']")
    private ExtendedWebElement acceptTermsAndConditionsCheckBox;

    @FindBy(css = "[data-test='registerStep'] button")
    private ExtendedWebElement createAccountButton;

    @FindBy(css = "[data-test='registerStep'] span.fieldError")
    private ExtendedWebElement emailAlreadyInUseErrorMessage;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
        setPageURL(PAGE_PATH);
    }

    @Override
    public void open() {
        openURL(getBaseURL());
        clickStayingChosenRegionBtn();
        clickRejectOptionalCookiesBtn();
    }

    public void signIn(String email, String password) {
        LOGGER.debug("Filling in user details...");
        signInEmailInput.type(email);
        signInPasswordInput.type(password);
        signInButton.click();

        switchToNewestWindow();
    }

    public void createNewAccount(String email, String password) {
        LOGGER.debug("Filling in new account details...");
        createAccountEmailInput.type(email);
        createAccountPasswordInput.type(password);
        acceptTermsAndConditionsCheckBox.click();
        createAccountButton.clickIfPresent();
    }

    public boolean isEmailAlreadyInUseMessageAppear() {
        return emailAlreadyInUseErrorMessage.isElementPresent(2000);
    }
}