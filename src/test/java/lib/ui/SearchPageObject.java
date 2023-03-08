package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {
	protected static String
			SEARCH_INIT_ELEMENT,
			SEARCH_INPUT,
			SEARCH_CANCEL_BUTTON,
			SEARCH_RESULT_BY_SUBSTRING_TPL,
			SEARCH_RESULT_BY_TITLE_SUBSTRING_AND_DESCRIPTION_SUBSTRING_TPL,
			SEARCH_RESULT_ELEMENT,
			SEARCH_EMPTY_RESULT_ELEMENT,
			SEARCH_ARTICLE_CONTAINER;


	public SearchPageObject(RemoteWebDriver driver) {
		super(driver);
	}

	/*TEMPLATE METHODS*/
	private static String getResultSearchElement(String substring) {
		return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
	}

	private static String getResultSearchElement(String title_substring, String description_substring) {
		return SEARCH_RESULT_BY_TITLE_SUBSTRING_AND_DESCRIPTION_SUBSTRING_TPL.replace("{TITLE_SUBSTRING}", title_substring).replace("{DESCRIPTION_SUBSTRING}", description_substring);
	}
	/*TEMPLATE METHODS*/

	@Step("Initializing the search field")
	public void initSearchInput() {
		this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input before clicking search init element", 5);
		this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
	}

	@Step("Waiting for button to cancel search result")
	public void waitForCancelButtonToAppear() {
		this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find cancel button", 5);
	}

	@Step("Waiting for search cancel button to disappear")
	public void waitForCancelButtonToDisappear() {
		this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button still present!", 5);
	}

	@Step("Click button to cancel search result")
	public void clickCancelSearch() {
		this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
	}

	@Step("Typing test to the search line")
	public void typeSearchLine(String search_line) {
		this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
	}

	@Step("Waiting for search result")
	public void waitForSearchResult(String substring) {
		String search_result_xpath = getResultSearchElement(substring);
		this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 15);
	}

	@Step("Waiting for article title and description")
	public void waitForElementByTitleAndDescription(String title_substring, String description_substring) {
		String search_result_xpath = getResultSearchElement(title_substring, description_substring);
		this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + title_substring + " and " + description_substring, 15);
	}

	@Step("Waiting for search result and select an article by substring in article title")
	public void clickByArticleWithSubstring(String substring) {
		String search_result_xpath = getResultSearchElement(substring);
		this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 15);
	}

	@Step("Getting amount of found article")
	public int getAmountOfFoundArticles() {
		this.waitForElementPresent(
				SEARCH_RESULT_ELEMENT,
				"Cannot find anythings by the request",
				15
		);
		return this.getAmountOfElements(
				SEARCH_RESULT_ELEMENT);
	}

	@Step("Waiting for empty result label")
	public void waitForEmptyResultsLabel() {
		this.waitForElementPresent(
				SEARCH_EMPTY_RESULT_ELEMENT,
				"Cannot find empty result element",
				15
		);
	}

	@Step("Making sure there are not results for the search")
	public void assertThereIsNotResultsOfSearch() {
		this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We suppose not to find any results.");
	}

	public void waitForArticlesDisappear() {
		this.waitForElementNotPresent(
				SEARCH_ARTICLE_CONTAINER,
				"Results are still present on the page",
				5
		);
	}

	public void assertThereAreMoreThanThreeArticles() {
		this.assertElementPresentsLessThanThree(SEARCH_RESULT_ELEMENT, "There are very few articles", 3);
	}
}
