package tests;

import com.codeborne.selenide.Condition;
import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class MobileTests extends TestBase {

    TestData testData = new TestData();

    @Test
    @DisplayName("Поиск на английском языке в приложении Wikipedia")
    void searchTestEn() {

        step("Открываем строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step("Вводим поисковое слово", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(testData.enSearchWord);
        });

        step("Проверяем, что в результатах поиска есть значения", () -> {
            $$(byClassName("android.widget.TextView")).shouldHave(sizeGreaterThan(testData.stringNumber));
        });
    }

    @Test
    @DisplayName("Переключение поиска на русский язык и поиск в приложении Wikipedia")
    void searchTestRu() {

       step("Открываем меню Settings", () -> {
            $(id("org.wikipedia.alpha:id/menu_overflow_button")).click();
            $(id("org.wikipedia.alpha:id/explore_overflow_settings")).click();
        });

        step("Изменяем язык поиска на русский", () -> {
            $(id("android:id/title")).click();
            $(id("org.wikipedia.alpha:id/preference_languages_filter")).sendKeys(testData.russian);
            $(id("org.wikipedia.alpha:id/language_subtitle")).click();
        });

        step("Возвращаемся на экран со строкой поиска", () -> {
            $(byClassName("android.widget.ImageButton")).click();
        });

        step("Открываем строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step("Убеждаемся, что язык переключен на русский", () -> {
            $(id("org.wikipedia.alpha:id/search_lang_button")).shouldHave(Condition.text(testData.language));
        });

        step("Вводим поисковое слово", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(testData.ruSearchWord);
        });

        step("Проверяем, что в результатах поиска есть значения", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(testData.stringNumber));
        });

        step("Находим заголовок первой статьи и открываем ее", () -> {
            $$(id("org.wikipedia.alpha:id/view_card_header_title")).first().click();
        });

        step("Проверяем, что отображается сообщение об ошибке", () -> {
        $(byClassName("android.widget.TextView")).shouldHave(Condition.text(testData.errorMessage));
        });
    }
}
