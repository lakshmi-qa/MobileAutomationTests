package Pages;

import org.openqa.selenium.By;

import Utils.TestBase;
import io.appium.java_client.MobileBy;

public class HomePage extends TestBase {

	/**
	 * Taps on create event icon
	 * 
	 * @throws Exception
	 */
	public void tapOnPlus() throws Exception {
		click(iconPlus);
	}

	/**
	 * Taps on event option
	 * 
	 * @throws Exception
	 */
	public void tapOnCreateEvent() throws Exception {
		click(iconPlus);
	}

	/**
	 * Selects the event creation date
	 * 
	 * @throws Exception
	 */
	public void gotoEventDate() throws Exception {
		// Navigates to required month
		int diff = getMonthDiff(getText(getTextOfMonth));
		if (diff > 0) {
			for (int i = 0; i < diff; i++) {
				swipeLeft();
			}
		}
		// Selects the event day
		click(eventContentSelection);

	}

	/**
	 * Verify created event available or not
	 * 
	 * @throws Exception
	 */
	public boolean verifyCreatedEvent() throws Exception {
		if (isElementPresent(eventContent)) {
			return true;
		} else
			return false;
	}

	/**
	 * Move over if start up pages exist
	 * 
	 * @throws Exception
	 */
	public void landOnCalendarDashboard() {
		if (isElementPresent(screen1Arrow)) {
			try {
				click(screen2Arrow);
				click(screen3Arrow);
			} catch (Exception e) {
				return;
			}

		}
	}

	By screen1Arrow = MobileBy.id("com.google.android.calendar:id/next_arrow_touch");
	By screen2Arrow = MobileBy.id("com.google.android.calendar:id/next_arrow");
	By screen3Arrow = MobileBy.id("com.google.android.calendar:id/done_button");
	By iconPlus = MobileBy.id("com.google.android.calendar:id/floating_action_button");
	By eventContent = MobileBy
			.xpath("//android.view.View[contains(@content-desc,'" + readProperty("EventTitle") + "')]");
	By getTextOfMonth = MobileBy.id("com.google.android.calendar:id/date_picker_text_view");
	String date = convertStringToDateFormat();
	By eventContentSelection = MobileBy.xpath("//android.view.View[contains(@content-desc,'" + date + "')]");

}
