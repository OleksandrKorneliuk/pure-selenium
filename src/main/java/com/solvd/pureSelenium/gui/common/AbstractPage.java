package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {

    private final WebDriver driver;

    private final String BASE_URL;

    public final WebDriverWait wait;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.BASE_URL = baseURL;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean clickIfAppear(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            return true;
        } catch (TimeoutException ignored) {
            LOGGER.warn("Element is not clickable within the timeout: {}", element);
            return false;
        }
    }

    public boolean isPageOpened() {
        try {
            return wait.until(ExpectedConditions.urlContains(BASE_URL));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void switchToNewestWindow() {
        List<String> handles = driver.getWindowHandles().stream().toList();
        if (!handles.isEmpty()) {
            driver.switchTo().window(handles.get(handles.size() - 1));
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}