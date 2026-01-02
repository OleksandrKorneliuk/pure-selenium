package com.solvd.pureSelenium.gui.pages.desktop;

import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Objects;

public class HomePageTest implements IAbstractTest {

    @Test
    public void testNavigateToMenCategory() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open.");

        homePage.clickMenCategoryBtn();

        Assert.assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/men"),
                "User should be on the men category page.");
    }
}
