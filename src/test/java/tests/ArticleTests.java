package tests;

import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
	@Test
	@DisplayName("Compare article with expected one")
	public void testCompareArticleTitle() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		String article_title = articlePageObject.getArticleTitle();

		Assert.assertEquals(
				"We see unexpected title",
				"Java (programming language)",
				article_title
		);
	}

	@Test
	@DisplayName("swipe article to the footer")
	public void testSwipeArticle() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		articlePageObject.waitForTitleElement();
		articlePageObject.swipeToFooter();
	}

	@Test
	public void testCheckElementPresent() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		searchPageObject.initSearchInput();
		String search_line = "Java";
		searchPageObject.typeSearchLine(search_line);
		searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		articlePageObject.assertThereIsElementTitle();
	}
}
