package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.HomePageBase;
import com.solvd.pureSelenium.gui.common.MyAccountPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends HomePageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    private static final String BASE_URL = "https://prm.com/";

    @FindBy(css = "div > img[alt='dropdown button']")
    private WebElement countrySelector;

    @FindBy(css = "a[href='/us']")
    private WebElement usRegionLink;

    @FindBy(css = "button.DropdownList_dropdownSubmitButton__FNidW")
    private WebElement submitRegionButton;

    @FindBy(css = "button[data-test='cookiesAcceptMandatoryButton']")
    private WebElement rejectOptionalCookiesButton;

    @FindBy(css = "button[data-test='geolocationPopupStayButton']")
    private WebElement geolocationStayButton;

    @FindBy(css = "a[href=\"/us/c/men\"] button.btnPrimaryLight")
    private WebElement menCategoryButton;

    @FindBy(css = "a[data-test='my_account_icon']")
    private WebElement myAccountLink;

    public HomePage(WebDriver driver) {
        super(driver, BASE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void open() {
        getDriver().get(BASE_URL);
        LOGGER.info("Opening PRM home page...");

        selectRegion();
        clickStayingChosenRegionBtn();
        clickRejectOptionalCookiesBtn();
    }

    private void selectRegion() {
        LOGGER.info("Choosing region...");
        clickIfAppear(countrySelector);
        clickIfAppear(usRegionLink);
        clickIfAppear(submitRegionButton);
    }

    private void clickStayingChosenRegionBtn() {
        LOGGER.info("Click on \"Stay in chosen region\" button");
        clickIfAppear(geolocationStayButton);
    }

    private void clickRejectOptionalCookiesBtn() {
        LOGGER.info("Rejection optional cookies...");
        clickIfAppear(rejectOptionalCookiesButton);
    }

    @Override
    public void clickMenCategoryBtn() {
        LOGGER.debug("Click \"Men\" button");
        clickIfAppear(menCategoryButton);
    }

    @Override
    public MyAccountPageBase goToMyAccountPage() {
        LOGGER.debug("Click \"My Account page\" link");
        clickIfAppear(myAccountLink);
        switchToNewestWindow();

        return new MyAccountPage(getDriver());
    }
}