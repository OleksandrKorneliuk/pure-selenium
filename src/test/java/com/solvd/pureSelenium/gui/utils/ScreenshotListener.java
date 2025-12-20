package com.solvd.pureSelenium.gui.utils;

import com.solvd.pureSelenium.gui.common.AbstractTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof AbstractTest test) {
            ScreenshotUtil.takeScreenshot(test.getDriver(), result.getName());
        }
    }
}
