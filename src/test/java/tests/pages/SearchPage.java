package tests.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    public SelenideElement search = $(AppiumBy.accessibilityId("Search Wikipedia"));
    public SelenideElement searchInput = $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));
    public ElementsCollection listTitleOfResults = $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));
    public SelenideElement titleOfResult = $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));
    public SelenideElement buttonSearchLanguage = $(AppiumBy.id("org.wikipedia.alpha:id/search_lang_button"));
    public ElementsCollection listOfLanguages = $$(AppiumBy.id("org.wikipedia.alpha:id/localized_language_name"));

    @Step("Указать в строке поиска {searchItem}")
    public SearchPage setSearchInput(String searchItem) {
        search.click();
        searchInput.sendKeys(searchItem);
        return this;
    }

    @Step("Результатов поиска больше чем 1")
    public void checkSearchResults() {
        listTitleOfResults.shouldHave(CollectionCondition.sizeGreaterThan(1));
    }

    @Step("Результат поиска содержит значение {testData}")
    public void checkItem(String testData) {
        titleOfResult.shouldHave(text(testData));
    }

    @Step("Изменить язык на {testData}")
    public void changeLanguage(String testData) {
        buttonSearchLanguage.click();
        listOfLanguages.findBy(text(testData)).click();
    }
}