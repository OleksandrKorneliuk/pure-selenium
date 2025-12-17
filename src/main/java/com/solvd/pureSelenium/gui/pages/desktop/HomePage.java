package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "button[data-test='cookiesAcceptMandatoryButton']")
    private WebElement rejectOptionalCookiesButton;

    @FindBy(css = "button[data-test='geolocationPopupStayButton']")
    private WebElement geolocationStayButton;

    @FindBy(css = "a[href=\"/us/c/men\"] button.btnPrimaryLight")
    private WebElement menCategoryButton;

    @FindBy(css = "a[data-test='my_account_icon']")
    private WebElement myAccountLink;

    @FindBy(css = "button[data-test='search_icon']")
    private WebElement searchIcon;

    @FindBy(css = "div.Header__innerTransparentChild__Hok1n input[data-test='search_input']")
    private WebElement searchInput;

    @FindBy(css = "div.Header__innerTransparentChild__Hok1n button[data-test='search_button']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver, "");
        PageFactory.initElements(driver, this);
    }

    public void open() {
        super.open();

        clickStayingChosenRegionBtn();
        clickRejectOptionalCookiesBtn();
    }

    private void clickStayingChosenRegionBtn() {
        LOGGER.info("Click on \"Stay in chosen region\" button");
        clickIfAppear(geolocationStayButton);
    }

    private void clickRejectOptionalCookiesBtn() {
        LOGGER.info("Rejection optional cookies...");
        clickIfAppear(rejectOptionalCookiesButton);
    }

    public void clickMenCategoryBtn() {
        LOGGER.debug("Click \"Men\" button");
        clickIfAppear(menCategoryButton);
    }

    public MyAccountPage goToMyAccountPage() {
        LOGGER.debug("Click \"My Account page\" link");
        clickIfAppear(myAccountLink);
        switchToNewestWindow();

        return new MyAccountPage(getDriver());
    }

    public SearchResultsPage searchProduct(String productName) {
        clickIfAppear(searchIcon);
        clickIfAppear(searchInput);

        searchInput.sendKeys(".");
        getWait().until(ExpectedConditions.attributeContains(searchInput, "value", "."));
        searchInput.sendKeys(productName);
        clickIfAppear(searchButton);

        return new SearchResultsPage(getDriver());
    }
}