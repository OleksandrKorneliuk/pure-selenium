package com.solvd.pureSelenium.gui.pages.desktop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    protected WebDriver driver;

    private final WebDriverWait wait;

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
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(BASE_URL);
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

    private void clickIfAppear(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException ignored) {
        }
    }

    private void clickStayingChosenRegionBtn() {
        LOGGER.info("Click on \"Stay in chosen region\" button");
        clickIfAppear(geolocationStayButton);
    }

    private void clickRejectOptionalCookiesBtn() {
        LOGGER.info("Rejection optional cookies...");
        clickIfAppear(rejectOptionalCookiesButton);
    }

    public boolean isPageOpened() {
        try {
            return wait.until(ExpectedConditions.urlContains(BASE_URL));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickMenCategoryBtn() {
        LOGGER.debug("Click \"Men\" button");
        clickIfAppear(menCategoryButton);
    }

    public MyAccountPage goToMyAccountPage() {
        LOGGER.debug("Click \"My Account page\" link");
        clickIfAppear(myAccountLink);
        switchToNewestWindow();

        return new MyAccountPage(driver);
    }

    private void switchToNewestWindow() {
        List<String> handles = driver.getWindowHandles().stream().toList();
        if (!handles.isEmpty()) {
            driver.switchTo().window(handles.get(handles.size() - 1));
        }
    }
}
