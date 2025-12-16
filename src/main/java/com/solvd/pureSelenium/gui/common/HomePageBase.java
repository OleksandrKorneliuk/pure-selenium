package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage {

    public HomePageBase(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public abstract void open();

    public abstract void clickMenCategoryBtn();

    public abstract MyAccountPageBase goToMyAccountPage();

    public abstract SearchResultsPageBase searchProduct(String productName);
}
