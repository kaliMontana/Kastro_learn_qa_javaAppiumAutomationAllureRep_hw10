package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
	static {
		MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
	}

	public MWNavigationUI(RemoteWebDriver driver) {
		super(driver);
	}
}
