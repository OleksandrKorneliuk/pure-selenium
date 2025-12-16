package com.solvd.pureSelenium.gui.common;

import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class SearchResultsPageBase extends AbstractPage {

    public SearchResultsPageBase(WebDriver driver, String baseURL) {
        super(driver, baseURL);
    }

    public abstract boolean doResultsContainsKeywords(List<String> keyWords);

    public abstract void selectFromLowestPriceFilter();

    public abstract boolean isSortedByLowestPrice();

    public abstract ProductPageBase selectFirstProductItem();
}
