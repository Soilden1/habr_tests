package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class NavigationTableComponent {

    private final SelenideElement articlesList = $(".tm-articles-list"),
            difficultyLevel = $(".tm-article-complexity__label");
    private final ElementsCollection articlesListElements = articlesList.$$(".tm-articles-list__item");
    private final Map<String, String> difficultyColors = new HashMap<>();

    {
        difficultyColors.put("Зеленый", "low");
        difficultyColors.put("Синий", "medium");
        difficultyColors.put("Красный", "high");
    }

    public void checkVisible() {
        articlesList.shouldHave(visible);
    }

    public void checkTitleArticleContainsText(int index, String text) {
        articlesListElements.get(index).$(".tm-title").shouldHave(text(text));
    }

    public void checkDifficultyLevelFirstArticle(String level) {
        difficultyLevel.shouldHave(text(level));
    }

    public void checkColorOfDifficultyLevelFirstArticle(String color) {
        String colorValue = difficultyColors.get(color);
        difficultyLevel.parent().shouldHave(cssClass("tm-article-complexity_complexity-" + colorValue));
    }

    public void checkRatingThresholdCompliance(int index, String ratingThreshold) {
        int expectedRatingThreshold = ratingThreshold.equals("Все") ? Integer.MIN_VALUE : Integer.parseInt(ratingThreshold);
        int actualRating = Integer.parseInt(articlesListElements.get(index).$(".tm-votes-meter__value_rating").getText());
        Assertions.assertTrue(actualRating >= expectedRatingThreshold);
    }
}
