package tests.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public SelenideElement search = $(AppiumBy.accessibilityId("Search Wikipedia"));
    public SelenideElement searchInput = $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));
    public ElementsCollection listTitleOfResults = $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));
    public SelenideElement titleOfResult = $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));
    public SelenideElement viewResults = $(AppiumBy.className("android.view.View"));
    public SelenideElement pageItem = $(AppiumBy.className("android.widget.TextView"));
    public SelenideElement buttonSearchLanguage = $(AppiumBy.id("org.wikipedia.alpha:id/search_lang_button"));
    public ElementsCollection listTitleLanguage = $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title"));
    public ElementsCollection listOfLanguages = $$(AppiumBy.id("org.wikipedia.alpha:id/localized_language_name"));
    public ElementsCollection page = $$(AppiumBy.className("android.widget.TextView"));

    @Step("вводим в строке поиска {}")
    public MainPage setSearchInput(String searchItem) {
        search.click();
        searchInput.setValue(searchItem);
        return this;
    }

    @Step("результатов поиска больше чем 1")
    public void checkSearchResults() {
        listTitleOfResults.shouldHave(CollectionCondition.sizeGreaterThan(1));
    }

    @Step("нажимаем кнопку назад")
    public MainPage back() {
        back();
        return this;
    }

    @Step("выбираем результат поиска")
    public void selectResult() {
        titleOfResult.click();
    }

    @Step("результат поиска содержит значение {testData}")
    public void checkItem(String testData) {
        pageItem.shouldHave(text(testData));
    }

    @Step("добавляем новый язык")
    public void addNewLanguage(String testData) {
        buttonSearchLanguage.click();
        listTitleLanguage.findBy(text("ADD LANGUAGE")).click();
        listOfLanguages.findBy(text(testData)).click();
    }

    @Step("провеярем, что язык добавлен")
    public void checkLanguage(String testData) {
        page.findBy(text(testData)).shouldBe(visible);
    }
}
