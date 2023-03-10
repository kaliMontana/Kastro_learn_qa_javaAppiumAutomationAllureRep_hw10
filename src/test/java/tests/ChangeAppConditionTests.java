package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

	@Test
	@Features(value = {@Feature(value = "Search"), @Feature(value = "rotate")})
	@DisplayName("Change screen orientation")
	@Description("Change screen orientation when we are searching a article")
	@Severity(value = SeverityLevel.BLOCKER)
	public void testChangeScreenOrientationOnSearchResults() {
		if (Platform.getInstance().isMW()) {
			return;
		}
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		String title_before_Rotation = articlePageObject.getArticleTitle();
		this.rotateScreenLandscape();
		String title_after_Rotation = articlePageObject.getArticleTitle();

		Assert.assertEquals(
				"Article title have been changed after screen rotation",
				title_before_Rotation,
				title_after_Rotation
		);

		this.rotateScreenPortrait();
		String title_after_second_Rotation = articlePageObject.getArticleTitle();

		Assert.assertEquals(
				"Article title have been changed after screen rotation",
				title_before_Rotation,
				title_after_second_Rotation
		);
	}

	@Test
	@Features(value = {@Feature(value = "Search"), @Feature(value = "Background")})
	@DisplayName("Check the apps's background work mode")
	@Description("Check if the app maintains the search result after it goes to background work mode")
	@Severity(value = SeverityLevel.CRITICAL)
	public void testCheckSearchArticleInBackground() {
		if (Platform.getInstance().isMW()) {
			return;
		}
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.waitForSearchResult("Object-oriented programming language");
		this.backGroundApp(3);
		searchPageObject.waitForSearchResult("Object-oriented programming language");
	}
}
