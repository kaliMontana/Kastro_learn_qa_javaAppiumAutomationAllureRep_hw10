package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
	static {
		SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
		SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
		SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
		SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]";
		SEARCH_RESULT_BY_TITLE_SUBSTRING_AND_DESCRIPTION_SUBSTRING_TPL = "xpath://*[@class='android.widget.LinearLayout']/*[@text='{TITLE_SUBSTRING}']/../*[@text='{DESCRIPTION_SUBSTRING}']";
		SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
		SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
		SEARCH_ARTICLE_CONTAINER = "id:org.wikipedia:id/search_results_container";
	}

	public AndroidSearchPageObject(RemoteWebDriver driver) {
		super(driver);
	}
}
