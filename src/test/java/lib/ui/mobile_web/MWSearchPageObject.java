package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
	static {
		SEARCH_INIT_ELEMENT = "css:button#searchIcon";
		SEARCH_INPUT = "css:form>input[type='search']";
		SEARCH_CANCEL_BUTTON = "css:div>button.cancel";
		SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
		//TODO проверить этот локатор
		SEARCH_RESULT_BY_TITLE_SUBSTRING_AND_DESCRIPTION_SUBSTRING_TPL = "xpath://*[@class='android.widget.LinearLayout']/*[@text='{TITLE_SUBSTRING}']/../*[@text='{DESCRIPTION_SUBSTRING}']";
		SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
		SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
		//TODO проверить этот локатор
		SEARCH_ARTICLE_CONTAINER = "css:org.wikipedia:id/search_results_container";
	}

	public MWSearchPageObject(RemoteWebDriver driver) {
		super(driver);
	}
}
