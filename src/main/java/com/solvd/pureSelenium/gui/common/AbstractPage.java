package com.solvd.pureSelenium.gui.common;

import com.solvd.pureSelenium.utils.WaitUtil;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {

    private final WebDriver DRIVER;

    private final String BASE_URL;

    public final WaitUtil WAIT;

    public AbstractPage(WebDriver driver, String baseURL) {
        this.DRIVER = driver;
        this.BASE_URL = baseURL;
        WAIT = new WaitUtil(driver);
    }

    public boolean isPageOpened() {
        try {
            return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains(BASE_URL));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void switchToNewestWindow() {
        List<String> handles = DRIVER.getWindowHandles().stream().toList();
        if (!handles.isEmpty()) {
            DRIVER.switchTo().window(handles.get(handles.size() - 1));
        }
    }

    public WebDriver getDriver() {
        return DRIVER;
    }
}
