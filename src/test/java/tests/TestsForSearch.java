package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.pages.SearchPage;

public class TestsForSearch extends TestBase {

    SearchPage searchPage = new SearchPage();

    @Test
    public void checkResultsHasItems() {
        searchPage
                .setSearchInput("Java");
        searchPage
                .checkSearchResults();
    }

    @ValueSource(strings = {"Java", "Jira"})
    @ParameterizedTest(name = "Проверка для значения {0}")
    public void mainSearch(String testData) {
        searchPage
                .setSearchInput(testData);
        searchPage
                .checkItem(testData);
    }

    @Test
    public void changeLanguageAndCheckResults() {
        searchPage
                .setSearchInput("Slovakia")
                .changeLanguage("Svenska");
        searchPage
                .checkItem("Slovakien");
    }
}
