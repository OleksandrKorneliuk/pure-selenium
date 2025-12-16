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

import static com.solvd.pureSelenium.gui.utils.SystemUtils.getEnvVariable;

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

    @Test
    public void testAccountAlreadyInUse() {
        HomePageBase homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open.");

        homePage.clickMenCategoryBtn();
        MyAccountPageBase myAccountPage = homePage.goToMyAccountPage();
        Assert.assertTrue(myAccountPage.isPageOpened(), "My Account page doesn't open.");

        String email = getEnvVariable("TEST_EMAIL");
        String password = getEnvVariable("TEST_PASSWORD");
        myAccountPage.createNewAccount(email, password);
        Assert.assertTrue(myAccountPage.isEmailAlreadyInUseMessageAppear(),
                "\"Email already in use\" message didn't appear.");
    }
}
