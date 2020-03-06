package Pages;

import org.openqa.selenium.By;

import Utils.TestBase;
import io.appium.java_client.MobileBy;

public class EventCreationPage extends TestBase {
	String day;

    /**
     * Creates Event
     * @throws Exception
     */
	public void createEvent() throws Exception {
		// Enter title
		type(eventTitle, readProperty("EventTitle"));
		// Select event date
		selectEventDate();
		// Select event start time
		selectEventTime(0, eventStartHour, eventStartMinute, "EventStartTime");
		// Select event end time
		selectEventTime(1, eventStartHour, eventStartMinute, "EventEndTime");
		click(btnEventSave);
	}
	
	/**
     * Selects event date from calendar
     * @throws Exception
     */
	public void selectEventDate() throws Exception {
		// Tap on event date
		click(startDay, 0);
		// Navigates to required month
		String getMonthTxt = getText(calHeader).substring(4, 7);
		if (getMonthDiff(getMonthTxt) > 0) {
			for (int i = 0; i < getMonthDiff(getMonthTxt); i++) {
				click(nextMonthArrow);
			}
		}
		day = readProperty("EventDate").substring(0, 2);
		// Selects required day
		click(dayText);
		click(btnOk);
	}
	/**
     * Selects event date from calendar
     * @param takes WebElement index
     * @param takes hour to schedule event
     * @param takes min to schedule event
     *  @param takes am/pm to schedule event
     * @throws Exception
     */
	public void selectEventTime(int indexEle, By hour, By min, String time) throws Exception {
		// Time selection
		click(startTime, indexEle);
		click(hour);
		click(min);
		if (isAM(time)) {
			click(eventAM);
		} else
			click(eventPM);
		click(btnOk);
	}

	By eventTitle = MobileBy.id("com.google.android.calendar:id/title_edit_text");
	By startDay = MobileBy.id("com.google.android.calendar:id/first_line_text");
	By calHeader = MobileBy.id("android:id/date_picker_header_date");
	By nextMonthArrow = MobileBy.id("android:id/next");
	By dayText = MobileBy.name(day);
	By btnOk = MobileBy.id("android:id/button1");
	By startTime = MobileBy.id("com.google.android.calendar:id/right_action");
	By eventStartHour = MobileBy.AccessibilityId(convertToText("EventStartTime", 0, 2));
	By eventStartMinute = MobileBy.AccessibilityId(convertToText("EventStartTime", 3, 5));
	By eventEndHour = MobileBy.AccessibilityId(convertToText("EventEndTime", 0, 2));
	By eventEndMinute = MobileBy.AccessibilityId(convertToText("EventEndTime", 3, 5));
	By eventPM = MobileBy.id("android:id/pm_label");
	By eventAM = MobileBy.id("android:id/am_label");
	By btnEventSave = MobileBy.id("com.google.android.calendar:id/save");

}
