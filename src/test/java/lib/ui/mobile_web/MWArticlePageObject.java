package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
	static {
		TITLE = "css:#content h1";
		FOOTER_ELEMENT = "css:footer";
		//Todo искать этот локатор если надо
		OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
		OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#page-actions-watch";
		//Todo искать этот локатор если надо
		ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
		//Todo искать этот локатор если надо
		MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
		//Todo искать этот локатор если надо
		MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
		//CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
		//Todo искать этот локатор если надо
		SEARCH_NAME_FOLDER_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
	}

	public MWArticlePageObject(RemoteWebDriver driver) {
		super(driver);
	}
}
