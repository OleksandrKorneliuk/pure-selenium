package com.solvd.pureSelenium.gui.pages.desktop;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
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
    @Parameters({"browser", "grid_url"})
    public void setUp(@Optional("chrome") String browser, String gridURL) throws MalformedURLException {
        MutableCapabilities capabilities = setBrowserOptions(browser);

        driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private MutableCapabilities setBrowserOptions(String browser) {
        return switch (browser.toLowerCase()) {
            case "firefox" -> new FirefoxOptions();
            case "edge" -> new EdgeOptions();
            default -> new ChromeOptions();
        };
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
