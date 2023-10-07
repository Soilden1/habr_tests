package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.NavigationTableComponent;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    private final NavigationTableComponent navigationTable = new NavigationTableComponent();

    private final SelenideElement searchInput = $(".tm-input-text-decorated__input");

    public SearchPage openPage() {
        open("/search");
        return this;
    }

    public SearchPage setSearchQuery(String query) {
        searchInput.setValue(query).pressEnter();
        return this;
    }

    public SearchPage checkResultTableVisible() {
        navigationTable.checkVisible();
        return this;
    }

    public SearchPage checkTitleFirstResultArticleContainsValue(String value) {
        navigationTable.checkTitleArticleContainsText(0, value);
        return this;
    }
}
