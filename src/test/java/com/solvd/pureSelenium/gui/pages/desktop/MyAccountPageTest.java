package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractTest;
import com.solvd.pureSelenium.gui.common.HomePageBase;
import com.solvd.pureSelenium.gui.common.MyAccountPageBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Objects;

public class MyAccountPageTest extends AbstractTest {

    @Test
    public void testUserSignIn() {
        HomePageBase homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open.");

        homePage.clickMenCategoryBtn();
        MyAccountPageBase myAccountPage = homePage.goToMyAccountPage();
        Assert.assertTrue(myAccountPage.isPageOpened(), "My Account page doesn't open.");

        String email = getEnvVariable("TEST_EMAIL");
        String password = getEnvVariable("TEST_PASSWORD");
        myAccountPage.signIn(email, password);

        new WebDriverWait(getDriver(), Duration.ofSeconds(7)).until(ExpectedConditions.titleContains("Account data"));

        Assert.assertTrue(Objects.requireNonNull(getDriver().getTitle()).contains("Account data"),
                "User should be on Account data page.");
    }

    private String getEnvVariable(String variable) {
        String value = System.getenv(variable);
        if (value == null) {
            throw new IllegalStateException(variable + " not set");
        }
        return value;
    }
}
