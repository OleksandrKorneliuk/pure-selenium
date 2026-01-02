package com.solvd.pureSelenium.gui.pages.desktop;

import com.zebrunner.carina.core.IAbstractTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Objects;

import static com.solvd.pureSelenium.gui.utils.SystemUtils.*;

public class MyAccountPageTest implements IAbstractTest {

    @Test
    public void testUserSignIn() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open.");

        homePage.clickMenCategoryBtn();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        Assert.assertTrue(myAccountPage.isPageOpened(), "My Account page doesn't open.");

        String email = getTestEmail();
        String password = getTestPassword();
        myAccountPage.signIn(email, password);

        new WebDriverWait(getDriver(), Duration.ofSeconds(7)).until(ExpectedConditions.titleContains("Account data"));

        Assert.assertTrue(Objects.requireNonNull(getDriver().getTitle()).contains("Account data"),
                "User should be on Account data page.");
    }

    @Test
    public void testAccountAlreadyInUse() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open.");

        homePage.clickMenCategoryBtn();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        Assert.assertTrue(myAccountPage.isPageOpened(), "My Account page doesn't open.");

        String email = getTestEmail();
        String password = getTestPassword();
        myAccountPage.createNewAccount(email, password);
        Assert.assertTrue(myAccountPage.isEmailAlreadyInUseMessageAppear(),
                "\"Email already in use\" message didn't appear.");
    }
}