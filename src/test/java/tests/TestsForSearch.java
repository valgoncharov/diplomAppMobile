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
                .back()
                .setSearchInput("Java");
        mainPage
                .checkSearchResults();
    }

    @ValueSource(strings = {"TestCase"})
    @ParameterizedTest(name = "Проверка для значения {0}")
    public void mainSearch(String testData) {
        mainPage
                .back()
                .setSearchInput(testData);
        mainPage
                .checkItem(testData);
    }

    @ValueSource(strings = {"Deutsch"})
    @ParameterizedTest(name = "Проверка для значения {0}")
    public void addNewLanguage(String testData) {
        mainPage
                .addNewLanguage(testData);
        mainPage
                .checkLanguage(testData);
    }
}
