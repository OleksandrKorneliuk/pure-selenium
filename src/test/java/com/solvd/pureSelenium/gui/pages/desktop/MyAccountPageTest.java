package com.solvd.pureSelenium.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Objects;
import java.net.URL;

public class MyAccountPageTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setBrowserName(browser);

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testUserSignIn() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open.");

        homePage.clickMenCategoryBtn();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        Assert.assertTrue(myAccountPage.isPageOpened(), "My Account page doesn't open.");

        String email = getEnvVariable("TEST_EMAIL");
        String password = getEnvVariable("TEST_PASSWORD");
        myAccountPage.signIn(email, password);

        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.titleContains("Account data"));

        Assert.assertTrue(Objects.requireNonNull(driver.getTitle()).contains("Account data"),
                "User should be on Account data page.");
    }

    private String getEnvVariable(String variable) {
        String value = System.getenv(variable);
        if (value == null) {
            throw new IllegalStateException(variable + " not set");
        }
        return value;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
