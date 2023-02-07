package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
	protected static String
			MY_LIST_LINK;

	public NavigationUI(RemoteWebDriver driver) {
		super(driver);
	}

	public void clickMyList() {
		this.waitForElementAndClick(
				MY_LIST_LINK,
				"Cannot find navigation button to my list",
				5
		);
	}
}
