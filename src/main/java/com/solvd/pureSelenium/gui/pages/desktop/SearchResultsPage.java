package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.ProductPageBase;
import com.solvd.pureSelenium.gui.common.SearchResultsPageBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchResultsPage extends SearchResultsPageBase {

    private static final Logger LOGGER = LogManager.getLogger(SearchResultsPage.class);

    private static final String BASE_URL = "https://prm.com/us/k/";

    private static final int MAX_CHECKED_PRODUCTS_NUMBER = 10;

    private final By productsLocator = By.cssSelector("div[data-test='productCard']");

    private final By productNameLocator = By.cssSelector("[data-test='productCardDescription'] span");

    @FindBy(css = "div[data-test='productSortDropdown']")
    private WebElement productSortDropdown;

    @FindBy(css = "label[for='price_asc_radio_0']")
    private WebElement fromLowestPriceFilter;

    @FindBy(css = "button[data-test='selectSubmit']")
    private WebElement submitFilterButton;

    private final By productPriceLocator = By.cssSelector("div[data-test='priceSaleWithoutMinimalDesktop']");

    @FindBy(css = "a[data-test='productItem']")
    private List<WebElement> productItem;

    public SearchResultsPage(WebDriver driver) {
        super(driver, BASE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean doResultsContainsKeywords(List<String> keywords) {
        List<WebElement> products = getVisibleProductCards();

        if (products.isEmpty()) {
            LOGGER.debug("No products found on page.");
            return false;
        }

        return getProductsToCheck(products).stream()
                .map(this::getProductName)
                .allMatch(name -> containsAllKeywords(name, keywords));
    }

    private List<WebElement> getVisibleProductCards() {
        return getWait().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productsLocator)
        );
    }

    private List<WebElement> getProductsToCheck(List<WebElement> products) {
        int limit = Math.min(products.size(), MAX_CHECKED_PRODUCTS_NUMBER);
        return products.subList(0, limit);
    }

    private String getProductName(WebElement card) {
        return card.findElement(productNameLocator).getText().trim();
    }

    private boolean containsAllKeywords(String text, List<String> keywords) {
        String lowerText = text.toLowerCase();

        return keywords.stream()
                .allMatch(keyword -> lowerText.contains(keyword.toLowerCase()));
    }

    @Override
    public void selectFromLowestPriceFilter() {
        clickIfAppear(productSortDropdown);
        fromLowestPriceFilter.click();
        clickIfAppear(submitFilterButton);
    }

    @Override
    public boolean isSortedByLowestPrice() {
        List<WebElement> products = getProductsToCheck(getVisibleProductCards());

        if (products.isEmpty()) {
            LOGGER.debug("No products found on page.");
            return false;
        }

        double prevPrice = getProductPrice(products.get(0));
        for (int i = 1; i < products.size(); ++i) {
            double currentPrice = getProductPrice(products.get(i));

            if (prevPrice > currentPrice) {
                LOGGER.debug("Sorting violation: {} > {}", prevPrice, currentPrice);
                return false;
            }
            prevPrice = currentPrice;
        }

        return true;
    }

    private double getProductPrice(WebElement card) {
        String price = card.findElement(productPriceLocator).getText().trim();
        String numericPart = price.substring(0, price.indexOf(' '));

        try {
            return Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Unable to parse product price from text: " + price, e
            );
        }
    }

    @Override
    public ProductPageBase selectFirstProductItem() {
        WebElement firstProduct = getVisibleProductCards().get(0);
        clickIfAppear(firstProduct);
        return new ProductPage(getDriver());
    }
}