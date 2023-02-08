package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
	static {
		FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
		ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
		TITLE = "id:org.wikipedia:id/page_list_item_title";
	}

	public MWMyListsPageObject(RemoteWebDriver driver) {
		super(driver);
	}
}
