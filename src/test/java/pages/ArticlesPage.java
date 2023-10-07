package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import pages.components.FilterComponent;
import pages.components.NavigationTableComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ArticlesPage {

    private final NavigationTableComponent navigationTable = new NavigationTableComponent();
    private final FilterComponent filter = new FilterComponent();

    private final SelenideElement pageTop = $(".tm-page__top");

    public ArticlesPage openPage() {
        open("/articles");
        return this;
    }

    public ArticlesPage setDifficultyLevelFilter(String level) {
        filter.setDifficultyLevel(level);
        return this;
    }

    public ArticlesPage setRatingThresholdFilter(String rating) {
        filter.setRatingThreshold(rating);
        return this;
    }

    public ArticlesPage checkMatchingDifficultyLevel(String level) {
        navigationTable.checkDifficultyLevelFirstArticle(level);
        return this;
    }

    public ArticlesPage checkMatchingDifficultyLevelColor(String color) {
        navigationTable.checkColorOfDifficultyLevelFirstArticle(color);
        return this;
    }

    public ArticlesPage checkRatingThresholdComplianceOfFirstArticle(String ratingThreshold) {
        navigationTable.checkRatingThresholdCompliance(0, ratingThreshold);
        return this;
    }

    public ArticlesPage checkContainsButtonsAtTop(List<String> expectedButtons) {
        pageTop.$(".tm-tabs__scroll-area").$$(".tm-tabs__tab-item a").filter(visible)
                .shouldHave(CollectionCondition.texts(expectedButtons));
        return this;
    }
}
