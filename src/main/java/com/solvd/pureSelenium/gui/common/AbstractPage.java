package com.solvd.pureSelenium.gui.common;

import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage extends com.zebrunner.carina.webdriver.gui.AbstractPage {

    public final WebDriverWait wait;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

    @FindBy(css = "button[data-test='cookiesAcceptMandatoryButton']")
    private ExtendedWebElement rejectOptionalCookiesButton;

    @FindBy(css = "button[data-test='geolocationPopupStayButton']")
    private ExtendedWebElement geolocationStayButton;

    public AbstractPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void clickStayingChosenRegionBtn() {
        LOGGER.info("Click on \"Stay in chosen region\" button");
        geolocationStayButton.clickIfPresent();
    }

    protected void clickRejectOptionalCookiesBtn() {
        LOGGER.info("Rejection optional cookies...");
        rejectOptionalCookiesButton.clickIfPresent();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return wait.until(ExpectedConditions.urlContains(getBaseURL()));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void switchToNewestWindow() {
        List<String> handles = getDriver().getWindowHandles().stream().toList();
        if (!handles.isEmpty()) {
            getDriver().switchTo().window(handles.getLast());
        }
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public String getBaseURL() {
        return R.CONFIG.get("url");
    }
}