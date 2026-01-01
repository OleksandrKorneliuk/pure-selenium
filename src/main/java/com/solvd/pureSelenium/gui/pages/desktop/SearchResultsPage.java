package com.solvd.pureSelenium.gui.pages.desktop;

import com.solvd.pureSelenium.gui.common.BasePage;
import com.solvd.pureSelenium.gui.components.ProductListItemComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class SearchResultsPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResultsPage.class);

    private static final String PAGE_PATH = "/k";

    private static final int MAX_CHECKED_PRODUCTS_NUMBER = 10;

    private final By productsLocator = By.cssSelector("div[data-test='productCard']");

    @FindBy(css = "div[data-test='productCard']")
    private List<ProductListItemComponent> products;

    @FindBy(css = "div[data-test='productSortDropdown']")
    private ExtendedWebElement productSortDropdown;

    @FindBy(css = "label[for='price_asc_radio_0']")
    private ExtendedWebElement fromLowestPriceFilter;

    @FindBy(css = "button[data-test='selectSubmit']")
    private ExtendedWebElement submitFilterButton;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
        setPageURL(PAGE_PATH);
    }

    private List<ProductListItemComponent> getProducts() {
        List<WebElement> cards = getWait().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productsLocator)
        );

        return cards.stream()
                .map(ProductListItemComponent::new)
                .limit(MAX_CHECKED_PRODUCTS_NUMBER)
                .toList();
    }

    public boolean doResultsContainsKeywords(List<String> keywords) {
        List<ProductListItemComponent> products = getProducts();

        if (products.isEmpty()) {
            LOGGER.debug("No products found on page.");
            return false;
        }

        return products.stream()
                .map(ProductListItemComponent::getName)
                .allMatch(name -> containsAllKeywords(name, keywords));
    }

    private boolean containsAllKeywords(String text, List<String> keywords) {
        String lowerText = text.toLowerCase();

        return keywords.stream()
                .allMatch(keyword -> lowerText.contains(keyword.toLowerCase()));
    }

    public void selectFromLowestPriceFilter() {
        productSortDropdown.clickIfPresent();
        fromLowestPriceFilter.click();
        submitFilterButton.clickIfPresent();
    }

    public boolean isSortedByLowestPrice() {
        List<ProductListItemComponent> products = getProducts();

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
        ProductListItemComponent firstProduct = getProducts().getFirst();
        firstProduct.click();
        return new ProductListPage(getDriver());
    }
}