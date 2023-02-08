package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
	private static final String name_of_folder = "Learning programming";
	private static final String
			login = "KaliTesting",
			password = "commandoX12";


	@Test
	public void testSaveFirstArticleToMyList() {
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		articlePageObject.waitForTitleElement();
		String article_title = articlePageObject.getArticleTitle();

		if (Platform.getInstance().isAndroid()) {
			articlePageObject.addArticleToMyList(name_of_folder);
		} else {
			articlePageObject.addArticleToMySaved();
		}
		if (Platform.getInstance().isMW()) {
			AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
			Auth.clickAuthButton();
			Auth.enterLoginData(login, password);
			Auth.submitFrom();

			articlePageObject.waitForTitleElement();
			assertEquals("We are not on the same page after login",
					article_title,
					articlePageObject.getArticleTitle()
			);

			articlePageObject.addArticleToMySaved();
		}
		articlePageObject.closeArticle();

		NavigationUI navigationUI = NavigationUIFactory.get(driver);
		navigationUI.openNavigation();
		navigationUI.clickMyList();

		MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

		if (Platform.getInstance().isAndroid()) {
			myListsPageObject.openFolderByName(name_of_folder);
		}
		myListsPageObject.swipeArticleToDelete(article_title);
	}

	@Test
	public void testSaveTwoArticlesToMyList() {
		String searched_word_Barcelona = "Barcelona";
		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(searched_word_Barcelona);
		searchPageObject.clickByArticleWithSubstring("City in Catalonia, Spain");

		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		articlePageObject.waitForTitleElement();
		String name_of_folder = "Doing the homework";
		articlePageObject.addArticleToMyList(name_of_folder);
		articlePageObject.closeArticle();

		//Is this began steps to second article
		searchPageObject.initSearchInput();
		String searched_word_Moscow = "Moscow";
		searchPageObject.typeSearchLine(searched_word_Moscow);
		searchPageObject.clickByArticleWithSubstring("Capital and most populous city of Russia");

		articlePageObject.waitForTitleElement();
		articlePageObject.addSecondArticleToMyList(name_of_folder);
		articlePageObject.closeArticle();

		NavigationUI navigationUI = NavigationUIFactory.get(driver);
		navigationUI.clickMyList();

		MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
		myListsPageObject.openFolderByName(name_of_folder);
		myListsPageObject.swipeArticleToDelete(searched_word_Barcelona);

		String article_title = myListsPageObject.getArticleTitle();
		Assert.assertEquals(
				"Titles are not the same",
				searched_word_Moscow,
				article_title
		);
	}
}
