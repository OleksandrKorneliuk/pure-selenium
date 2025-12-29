package com.solvd.pureSelenium.gui.pages.desktop;

import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SearchResultsPageTest implements IAbstractTest {

    @DataProvider
    public Object[][] getQueriesAndResults() {
        return new Object[][]{
                {"New Balance shoes", List.of("new balance")},
                {"Kenzo jacket", List.of("kenzo", "jacket")},
                {"Puma caps", List.of("puma", "cap")}
        };
    }

    @Test(dataProvider = "getQueriesAndResults")
    public void testSearchProducts(String searchQuery, List<String> expectedKeywords) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page didn't open.");

        homePage.clickMenCategoryBtn();
        SearchResultsPage searchResultsPage = homePage.searchProduct(searchQuery);
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search results page didn't open");

        Assert.assertTrue(searchResultsPage.doResultsContainsKeywords(expectedKeywords),
                "Search results are not relevant to the query.");
    }

    @Test
    public void testProductSortInAscendingOrder() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page didn't open.");

        homePage.clickMenCategoryBtn();
        SearchResultsPage searchResultsPage = homePage.searchProduct("Kenzo jacket");
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search results page didn't open");

        searchResultsPage.selectFromLowestPriceFilter();
        Assert.assertTrue(searchResultsPage.isSortedByLowestPrice(), "Sort filter didn't apply.");
    }
}