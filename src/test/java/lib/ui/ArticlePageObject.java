package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
	protected static String
			TITLE,
			FOOTER_ELEMENT,
			OPTIONS_BUTTON,
			OPTIONS_ADD_TO_MY_LIST_BUTTON,
			OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
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

	@Step("Waiting for title on the article page")
	public WebElement waitForTitleElement() {
		return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
	}

	@Step("Get article title")
	public String getArticleTitle() {
		WebElement title_element = waitForTitleElement();
		screenshot(this.takeScreenShot("article_tile"));
		if (Platform.getInstance().isAndroid()) {
			return title_element.getAttribute("text");
		} else if (Platform.getInstance().isIOS()) {
			return title_element.getAttribute("name");
		} else {
			return title_element.getText();
		}
	}

	@Step("Swiping to footer on the article page")
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

	@Step("Adding the article to my list")
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

	@Step("Adding a second article to my list")
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

	@Step("Closing the article")
	public void closeArticle() {
		if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
			this.waitForElementAndClick(
					CLOSE_ARTICLE_BUTTON,
					"Cannot close article, cannot find X link",
					5
			);
		} else {
			System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
		}

	}

	@Step("Checking if the title exists")
	public void assertThereIsElementTitle() {
		this.assertElementPresent(
				TITLE,
				"resourceId",
				"Not found element 'Title'",
				15
		);
	}

	@Step("Adding the article to my saved articles")
	public void addArticleToMySaved() {
		if (Platform.getInstance().isMW()) {
			removeArticleFromSavedIfItAdded();
		}
		this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to add article to reading list", 5);
	}

	@Step("Removing the article from saved if it hs been added")
	public void removeArticleFromSavedIfItAdded() {
		if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
			this.waitForElementAndClick(
					OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
					"Cannot click button to remove an article from seved",
					2
			);
			this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON,
					"Cannot find button to add an article to save list after removing it from find X link",
					2
			);
		}
	}
}
