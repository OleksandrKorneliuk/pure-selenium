package com.solvd.pureSelenium.gui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product extends BaseComponent {

    private static final Pattern PRICE_PATTERN = Pattern.compile("(\\d+(?:\\.\\d+)?)");

    private final By productDescription = By.cssSelector("[data-test='productCardDescription'] span");

    private final By productPrice = By.cssSelector("div[data-test='priceSaleWithoutMinimalDesktop']");

    public Product(WebElement root) {
        super(root);
    }

    public String getName() {
        return root.findElement(productDescription).getText().trim();
    }

    public BigDecimal getPrice() {
        String price = root.findElement(productPrice).getText().trim();

        Matcher matcher = PRICE_PATTERN.matcher(price);

        if (matcher.find()) {
            return BigDecimal.valueOf(Double.parseDouble(matcher.group(1)));
        }

        throw new IllegalStateException("Unable to parse product price from text: " + price);
    }

    public void click() {
        root.click();
    }
}
