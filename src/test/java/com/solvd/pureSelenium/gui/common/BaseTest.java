package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public WebDriver getDriver() {
        return drivers.get();
    }

    @BeforeMethod
    @Parameters({"browser", "grid_url"})
    public void setUp(@Optional("chrome") String browser, String gridURL) throws MalformedURLException {
        MutableCapabilities capabilities = setBrowserOptions(browser);

        WebDriver driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        drivers.set(driver);
    }

    private MutableCapabilities setBrowserOptions(String browser) {
        return switch (browser.toLowerCase()) {
            case "firefox" -> new FirefoxOptions();
            case "edge" -> new EdgeOptions();
            default -> new ChromeOptions();
        };
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = drivers.get();
        if (driver != null) {
            driver.quit();
            drivers.remove();
        }
    }
}