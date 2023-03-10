package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

	@Test
	@Feature(value = "Welcome")
	@DisplayName("Pass through 'Welcome windows'")
	@Description("We pass through 'Welcome windows' in the iOS version")
	@Severity(value = SeverityLevel.MINOR)
	public void testPassThroughWelcome() {
		if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
			return;
		}
		WelcomePageObject welcomePage = new WelcomePageObject(driver);

		welcomePage.waitForLearnMoreLink();
		welcomePage.clickNextButton();

		welcomePage.waitForNewWayToExplorerText();
		welcomePage.clickNextButton();

		welcomePage.waitForAddOrEditPreferredLangText();
		welcomePage.clickNextButton();

		welcomePage.waitForLearnMoreAboutDataCollectedText();
		welcomePage.clickGetStartedButton();
	}
}
