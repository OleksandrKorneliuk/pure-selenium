package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.BasePage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "a[href=\"/us/c/men\"] button.btnPrimaryLight")
    private ExtendedWebElement menCategoryButton;

    @FindBy(css = "a[data-test='my_account_icon']")
    private ExtendedWebElement myAccountLink;

    @FindBy(css = "button[data-test='search_icon']")
    private ExtendedWebElement searchIcon;

    @FindBy(css = "div.Header__innerTransparentChild__Hok1n input[data-test='search_input']")
    private ExtendedWebElement searchInput;

    @FindBy(css = "div.Header__innerTransparentChild__Hok1n button[data-test='search_button']")
    private ExtendedWebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
    }

    @Override
    public void open() {
        openURL(getBaseURL());
        clickStayingChosenRegionBtn();
        clickRejectOptionalCookiesBtn();
    }

    public void clickMenCategoryBtn() {
        LOGGER.debug("Click \"Men\" button");
        menCategoryButton.clickIfPresent();
    }

    public MyAccountPage goToMyAccountPage() {
        LOGGER.debug("Click \"My Account page\" link");
        myAccountLink.clickIfPresent();
        switchToNewestWindow();

        return new MyAccountPage(getDriver());
    }

    public SearchResultsPage searchProduct(String productName) {
        searchIcon.clickIfPresent();
        searchInput.clickIfPresent();
        searchInput.type(".");
        getWait().until(ExpectedConditions.attributeContains(searchInput.getElement(), "value", "."));
        searchInput.type(productName);
        searchButton.clickIfPresent();

        return new SearchResultsPage(getDriver());
    }
}