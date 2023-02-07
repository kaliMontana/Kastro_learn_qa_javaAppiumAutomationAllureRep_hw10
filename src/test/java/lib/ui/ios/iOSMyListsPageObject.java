package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * В этом классе надо поменять локаторы, которые имеет wikipedia для
 * система iOS. Посмотреть видео из занятия VII - 05. Рефакторинг тестов на поискГиперссылка
 **/
public class iOSMyListsPageObject extends MyListsPageObject {
	static {
		ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
		TITLE = "id:org.wikipedia:id/page_list_item_title";
	}

	public iOSMyListsPageObject(RemoteWebDriver driver) {
		super(driver);
	}
}
