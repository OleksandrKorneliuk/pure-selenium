package com.solvd.pureSelenium.gui.common;

import com.solvd.pureSelenium.gui.pages.desktop.MyAccountPage;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends BasePage {

    public HomePageBase(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public abstract void open();

    public abstract void clickMenCategoryBtn();

    public abstract MyAccountPage goToMyAccountPage();
}
