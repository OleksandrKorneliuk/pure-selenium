package com.solvd.pureSelenium.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;


public class HomePageTest {

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
    public void testNavigateToMenCategory() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open.");

        homePage.clickMenCategoryBtn();

        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/men"),
                "User should be on the men category page.");
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
