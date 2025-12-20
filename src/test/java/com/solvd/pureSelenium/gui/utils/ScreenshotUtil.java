package com.solvd.pureSelenium.gui.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            File dest = new File("screenshots/" + testName + "_" + timestamp + ".png");
            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
