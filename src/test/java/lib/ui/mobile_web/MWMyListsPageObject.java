package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
	static {
		//FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
		ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
		REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/../../a[contains(@class, 'watched')]";
		//TITLE = "id:org.wikipedia:id/page_list_item_title";
		LABEL_ARTICLE_REMOVED = "xpath://div[@role='status']//label";
	}

	public MWMyListsPageObject(RemoteWebDriver driver) {
		super(driver);
	}
}
