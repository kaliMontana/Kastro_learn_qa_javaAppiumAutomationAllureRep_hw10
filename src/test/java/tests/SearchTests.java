package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

	@Test
	@Feature(value = "Search")
	@DisplayName("Search one article")
	@Description("We use the search field to find an article")
	@Severity(value = SeverityLevel.BLOCKER)
	public void testSearch() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.waitForSearchResult("bject-oriented programming language");
	}

	@Test
	@Features(value = {@Feature(value = "Search"), @Feature(value = "Cancel search")})
	@DisplayName("Cancel a search")
	@Description("We use the icon 'X' to cancel a search")
	@Severity(value = SeverityLevel.NORMAL)
	public void testCancelSearch() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.waitForCancelButtonToAppear();
		searchPageObject.clickCancelSearch();
		searchPageObject.waitForCancelButtonToDisappear();
	}

	@Test
	@Features(value = {@Feature(value = "Search"), @Feature(value = "Result list")})
	@DisplayName("Check the amount of not empty search")
	@Description("We check that the results list of one search is not empty")
	@Severity(value = SeverityLevel.CRITICAL)
	public void testAmountOfNotEmptySearch() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		searchPageObject.initSearchInput();
		String search_line = "Linking park discography";
		searchPageObject.typeSearchLine(search_line);
		int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

		Assert.assertTrue("We found too few results!",
				amount_of_search_results > 0
		);
	}

	@Test
	@Features(value = {@Feature(value = "Search"), @Feature(value = "Result list")})
	@DisplayName("Check the amount of not empty search")
	@Description("We check that the results list of one search is not empty")
	@Severity(value = SeverityLevel.CRITICAL)
	public void testAmountOfEmptySearch() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		searchPageObject.initSearchInput();
		String search_line = "dsgffgdfgfd";
		searchPageObject.typeSearchLine(search_line);
		searchPageObject.waitForEmptyResultsLabel();
		searchPageObject.assertThereIsNotResultsOfSearch();
	}

	@Test
	@Features(value = {@Feature(value = "Search"), @Feature(value = "Cancel search"), @Feature(value = "Result list")})
	@DisplayName("Cancel a search")
	@Description("We do a search, check the amount of articles in the result list an then use the icon 'X' to cancel a search")
	@Severity(value = SeverityLevel.NORMAL)
	public void testCancelSearchEx3() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		searchPageObject.initSearchInput();
		String search_line = "Encarta";
		searchPageObject.typeSearchLine(search_line);
		searchPageObject.getAmountOfFoundArticles();
		searchPageObject.waitForCancelButtonToAppear();
		searchPageObject.clickCancelSearch();
		searchPageObject.waitForArticlesDisappear();
	}

	@Test
	@Features(value = {@Feature(value = "Search"), @Feature(value = "Result list")})
	@DisplayName("Check the result amount of  oarticle ByTitleAndDescription")
	@Description("We do a search, check the amount of articles in the result list an then use the icon 'X' to cancel a search")
	@Severity(value = SeverityLevel.NORMAL)
	public void testCheckResultAmountAndArticleByTitleAndDescription() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.assertThereAreMoreThanThreeArticles();
		searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
	}
}
