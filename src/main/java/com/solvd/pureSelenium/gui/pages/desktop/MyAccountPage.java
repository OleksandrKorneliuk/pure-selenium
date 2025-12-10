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

public class MyAccountPage {

    private static final Logger LOGGER = LogManager.getLogger(MyAccountPage.class);

    private static final String BASE_URL = "my-account/login";

    protected WebDriver driver;

    private final WebDriverWait wait;

    @FindBy(css = "input[id='_username']")
    private WebElement emailInput;

    @FindBy(css = "input[id='_password']")
    private WebElement passwordInput;

    @FindBy(css = "button[class='btn btnPrimary btnFluid']")
    private WebElement submitButton;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        try {
            return wait.until(ExpectedConditions.urlContains(BASE_URL));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void signIn(String email, String password) {
        LOGGER.debug("Filling in user details...");
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitButton.click();

        switchToNewestWindow();
    }

    private void switchToNewestWindow() {
        List<String> handles = driver.getWindowHandles().stream().toList();
        if (!handles.isEmpty()) {
            driver.switchTo().window(handles.get(handles.size() - 1));
        }
    }
}