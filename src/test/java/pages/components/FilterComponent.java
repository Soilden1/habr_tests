package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class FilterComponent {

    private final SelenideElement filter = $(".tm-navigation-filter"),
            filterButton = $(".tm-navigation-filters-spoiler__button"),
            applyButton = $(".tm-navigation-filters__apply");

    public void setRatingThreshold(String rating) {
        openFilter();
        String ratingValue = rating.equals("Все") ? rating : "≥" + rating;
        filter.$(byText("Порог рейтинга")).parent().$(byText(ratingValue)).click();
        applyButton.click();
    }

    public void setDifficultyLevel(String level) {
        openFilter();
        filter.$(byText("Уровень сложности")).sibling(0).$(byText(level)).click();
        applyButton.click();
    }

    private void openFilter() {
        sleep(1000);
        filterButton.click();
    }
}
