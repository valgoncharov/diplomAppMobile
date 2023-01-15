package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.pages.MainPage;

public class TestsForSearch extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    public void checkResultsHasItems() {
        mainPage
                .setSearchInput("Java");
        mainPage
                .checkSearchResults();
    }

    @ValueSource(strings = {"Java", "Jira"})
    @ParameterizedTest(name = "Проверка для значения {0}")
    public void mainSearch(String testData) {
        mainPage
                .setSearchInput(testData);
        mainPage
                .checkItem(testData);
    }

    @Test
    public void changeLanguageAndCheckResults() {
        mainPage
                .setSearchInput("Slovakia")
                .changeLanguage("Svenska");
        mainPage
                .checkItem("Slovakien");
    }
}
