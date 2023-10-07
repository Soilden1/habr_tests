package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.ArticlesPage;
import pages.SearchPage;

import java.util.List;
import java.util.stream.Stream;

public class HabrTests extends BaseTest {

    private final ArticlesPage articlesPage = new ArticlesPage();
    private final SearchPage searchPage = new SearchPage();

    @ValueSource(strings = {"Java", "Junit", "Selenide"})
    @ParameterizedTest(name = "Первая статья, найденная по запросу '{0}' содержит слово '{0}' в заголовке")
    @Tags({@Tag("CRITICAL"), @Tag("SMOKE")})
    public void successfulSearchTest(String value) {
        searchPage.openPage()
                .setSearchQuery(value);

        searchPage.checkResultTableVisible()
                .checkTitleFirstResultArticleContainsValue(value);
    }

    @CsvSource(value = {
            "Простой, Зеленый",
            "Средний, Синий",
            "Сложный, Красный"
    })
    @ParameterizedTest(name = "Статьи фильтруются по уровню сложности '{0}', уровень отображается '{1}' цветом")
    @Tags({@Tag("HIGH"), @Tag("SMOKE")})
    public void successfulArticleDifficultyLevelFilterTest(String level, String color) {
        articlesPage.openPage()
                .setDifficultyLevelFilter(level);

        articlesPage.checkMatchingDifficultyLevel(level)
                .checkMatchingDifficultyLevelColor(color);
    }

    @ValueSource(strings = {"Все", "0", "10", "25", "50", "100"})
    @ParameterizedTest(name = "При фильтрации по порогу рейтинга '{0}', отображаются только статьи с рейтингом '{0}' и выше")
    @Tags({@Tag("HIGH"), @Tag("SMOKE")})
    public void successfulArticleRatingThresholdFilterTest(String rating) {
        articlesPage.openPage()
                .setRatingThresholdFilter(rating);

        articlesPage.checkRatingThresholdComplianceOfFirstArticle(rating);
    }

    @CsvFileSource(resources = "/habrTests/testData/successfulAllFilterTest.csv")
    @ParameterizedTest(name = "При фильтрации по порогу рейтинга {0}, уровню сложности {1}, " +
            "отображаются подходящие условию статьи, уровень отображается {2} цветом")
    @Tags({@Tag("HIGH"), @Tag("SMOKE")})
    public void successfulArticleAllFilterTest(String rating, String level, String color) {
        articlesPage.openPage()
                .setRatingThresholdFilter(rating)
                .setDifficultyLevelFilter(level);

        articlesPage.checkRatingThresholdComplianceOfFirstArticle(rating)
                .checkMatchingDifficultyLevel(level)
                .checkMatchingDifficultyLevelColor(color);
    }

    static Stream<Arguments> checkContainsButtonsAtTopArticlePage() {
        return Stream.of(
                Arguments.of(List.of("Статьи", "Посты", "Новости", "Хабы", "Авторы", "Компании"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "В начале страницы 'Статьи' отображаестя список кнопок перехода на другие страницы")
    @Tags({@Tag("BLOCKER"), @Tag("SMOKE")})
    public void checkContainsButtonsAtTopArticlePage(List<String> expectedButtons) {
        articlesPage.openPage()
                .checkContainsButtonsAtTop(expectedButtons);
    }
}
