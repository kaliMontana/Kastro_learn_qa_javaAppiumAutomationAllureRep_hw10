package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
	protected static String
			TITLE,
			FOOTER_ELEMENT,
			OPTIONS_BUTTON,
			OPTIONS_ADD_TO_MY_LIST_BUTTON,
			ADD_TO_MY_LIST_OVERLAY,
			MY_LIST_NAME_INPUT,
			MY_LIST_OK_BUTTON,
			CLOSE_ARTICLE_BUTTON,
			SEARCH_NAME_FOLDER_BY_SUBSTRING_TPL;


	public ArticlePageObject(RemoteWebDriver driver) {
		super(driver);
	}

	/*TEMPLATE METHODS*/
	private static String getResultSearchElement(String substring) {
		return SEARCH_NAME_FOLDER_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
	}
	/*TEMPLATE METHODS*/

	public WebElement waitForTitleElement() {
		return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
	}

	public String getArticleTitle() {
		WebElement title_element = waitForTitleElement();
		if (Platform.getInstance().isAndroid()) {
			return title_element.getAttribute("text");
		} else if (Platform.getInstance().isIOS()) {
			return title_element.getAttribute("name");
		} else {
			return title_element.getText();
		}
	}

	public void swipeToFooter() {
		if (Platform.getInstance().isAndroid()) {
			this.swipeUpToFindElement(
					FOOTER_ELEMENT,
					"Cannot find the end of the article",
					40
			);
		} else if (Platform.getInstance().isIOS()) {
			this.swipeUpTillElementAppear(FOOTER_ELEMENT,
					"Cannot find the end of the article",
					40);
		} else {
			this.scrollWebPageTillElementNoVisible(
					FOOTER_ELEMENT,
					"Cannot find the end of the article",
					40
			);
		}
	}

	public void addArticleToMyList(String name_of_folder) {
		this.waitForElementAndClick(
				OPTIONS_BUTTON,
				"Cannot find button to open article options",
				5
		);

		this.waitForElementPresent(
				OPTIONS_ADD_TO_MY_LIST_BUTTON,
				"Cannot find option to add article to reading list!",
				15);

		this.waitForElementAndClick(
				OPTIONS_ADD_TO_MY_LIST_BUTTON,
				"Cannot find option to add article to reading list",
				5
		);

		this.waitForElementAndClick(
				ADD_TO_MY_LIST_OVERLAY,
				"Cannot find 'Got it' tip overlay",
				5
		);

		this.waitForElementAndClear(
				MY_LIST_NAME_INPUT,
				"Cannot find input to set name of articles folder",
				5
		);

		this.waitForElementAndSendKeys(
				MY_LIST_NAME_INPUT,
				name_of_folder,
				"Cannot put text into articles folder input",
				5
		);

		this.waitForElementAndClick(
				MY_LIST_OK_BUTTON,
				"Cannot press 'OK' button",
				5
		);
	}

	public void addSecondArticleToMyList(String name_of_folder) {
		this.waitForElementAndClick(
				OPTIONS_BUTTON,
				"Cannot find button to open article options",
				5
		);

		this.waitForElementPresent(
				OPTIONS_ADD_TO_MY_LIST_BUTTON,
				"Cannot find option to add article to reading list!",
				15);

		this.waitForElementAndClick(
				OPTIONS_ADD_TO_MY_LIST_BUTTON,
				"Cannot click on option add article to reading list",
				5
		);
		String search_result_xpath = getResultSearchElement(name_of_folder);
		waitForElementAndClick(
				search_result_xpath,
				"Cannot find option to add article to reading list",
				15
		);
	}

	public void closeArticle() {
		this.waitForElementAndClick(
				CLOSE_ARTICLE_BUTTON,
				"Cannot close article, cannot find X link",
				5
		);
	}

	public void assertThereIsElementTitle() {
		this.assertElementPresent(
				TITLE,
				"resourceId",
				"Not found element 'Title'",
				15
		);
	}

	public void addArticleToMySaved() {
		this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to add article to reading list", 5);
	}
}
