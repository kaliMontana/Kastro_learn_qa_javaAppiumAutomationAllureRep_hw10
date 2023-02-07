package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * В этом классе надо поменять локаторы, которые имеет wikipedia для
 * система iOS. Посмотреть видео из занятия VII - 05. Рефакторинг тестов на поискГиперссылка
 **/
public class iOSNavigationUI extends NavigationUI {
	static {
		MY_LIST_LINK = "id:saved";
	}

	public iOSNavigationUI(RemoteWebDriver driver) {
		super(driver);
	}
}
