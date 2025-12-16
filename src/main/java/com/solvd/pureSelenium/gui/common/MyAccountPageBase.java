package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.WebDriver;

public abstract class MyAccountPageBase extends AbstractPage {

    public MyAccountPageBase(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public abstract void signIn(String email, String password);

    public abstract void createNewAccount(String email, String password);

    public abstract boolean isEmailAlreadyInUseMessageAppear();
}
