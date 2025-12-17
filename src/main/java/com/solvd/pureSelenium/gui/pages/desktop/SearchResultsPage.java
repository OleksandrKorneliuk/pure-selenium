package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.AbstractPage;
import com.solvd.pureSelenium.gui.components.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class SearchResultsPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResultsPage.class);

    private static final String PAGE_PATH = "/k";

    private static final int MAX_CHECKED_PRODUCTS_NUMBER = 10;

    private final By productsLocator = By.cssSelector("div[data-test='productCard']");

    @FindBy(css = "div[data-test='productSortDropdown']")
    private WebElement productSortDropdown;

    @FindBy(css = "label[for='price_asc_radio_0']")
    private WebElement fromLowestPriceFilter;

    @FindBy(css = "button[data-test='selectSubmit']")
    private WebElement submitFilterButton;

    public SearchResultsPage(WebDriver driver) {
        super(driver, PAGE_PATH);
        PageFactory.initElements(driver, this);
    }

    private List<Product> getProducts() {
        List<WebElement> cards = getWait().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productsLocator)
        );

        return cards.stream()
                .map(Product::new)
                .limit(MAX_CHECKED_PRODUCTS_NUMBER)
                .toList();
    }

    public boolean doResultsContainsKeywords(List<String> keywords) {
        List<Product> products = getProducts();

        if (products.isEmpty()) {
            LOGGER.debug("No products found on page.");
            return false;
        }

        return products.stream()
                .map(Product::getName)
                .allMatch(name -> containsAllKeywords(name, keywords));
    }

    private boolean containsAllKeywords(String text, List<String> keywords) {
        String lowerText = text.toLowerCase();

        return keywords.stream()
                .allMatch(keyword -> lowerText.contains(keyword.toLowerCase()));
    }

    public void selectFromLowestPriceFilter() {
        clickIfAppear(productSortDropdown);
        fromLowestPriceFilter.click();
        clickIfAppear(submitFilterButton);
    }

    public boolean isSortedByLowestPrice() {
        List<Product> products = getProducts();

        if (products.isEmpty()) {
            LOGGER.debug("No products found on page.");
            return false;
        }

        BigDecimal prevPrice = products.get(0).getPrice();
        for (int i = 1; i < products.size(); ++i) {
            BigDecimal currentPrice = products.get(i).getPrice();

            if (prevPrice.compareTo(currentPrice) > 0) {
                LOGGER.debug("Sorting violation: {} > {}", prevPrice, currentPrice);
                return false;
            }
            prevPrice = currentPrice;
        }

        return true;
    }

    public ProductListPage selectFirstProductItem() {
        Product firstProduct = getProducts().get(0);
        firstProduct.click();
        return new ProductListPage(getDriver());
    }
}