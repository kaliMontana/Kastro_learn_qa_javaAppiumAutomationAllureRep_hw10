package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase {

	protected RemoteWebDriver driver;


	@Before
	@Step("Run driver and session")
	public void setUp() throws Exception {
		driver = Platform.getInstance().getDriver();
		this.rotateScreenPortrait();
		this.skipWelcomePageForIOSApp();
		this.openWikiWebPageForMobileWeb();
	}

	@After
	@Step("Remove driver and session")
	public void tearDown() {
		driver.quit();
	}

	@Step("Rotate screen to portrait mode")
	protected void rotateScreenPortrait() {
		if (driver instanceof AppiumDriver) {
			AppiumDriver driver = (AppiumDriver) this.driver;
			driver.rotate(ScreenOrientation.PORTRAIT);
		} else {
			System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
		}
	}

	@Step("Rotate screen to landscape mode")
	protected void rotateScreenLandscape() {
		if (driver instanceof AppiumDriver) {
			AppiumDriver driver = (AppiumDriver) this.driver;
			driver.rotate(ScreenOrientation.LANDSCAPE);
		} else {
			System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
		}
	}

	@Step("Send mobile app to background (this method does nothing for mobile web)")
	protected void backGroundApp(int seconds) {
		if (driver instanceof AppiumDriver) {
			AppiumDriver driver = (AppiumDriver) this.driver;
			driver.runAppInBackground(Duration.ofSeconds(seconds));
		} else {
			System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
		}
	}

	@Step("Open Wikipedia URL for mobile Web (this method does nothing for Android and iOS)")
	protected void openWikiWebPageForMobileWeb() {
		if (Platform.getInstance().isMW()) {
			driver.get("https://en.m.wikipedia.org");
		} else {
			System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
		}
	}

	@Step("Skip welcome screen for iOS")
	private void skipWelcomePageForIOSApp() {
		if (Platform.getInstance().isIOS()) {
			AppiumDriver driver = (AppiumDriver) this.driver;
			WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
			welcomePageObject.clickSkip();
		}
	}
}
