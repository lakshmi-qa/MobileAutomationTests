package Tests;

import org.testng.Assert;
import org.testng.annotations.*;

import Pages.EventCreationPage;
import Pages.HomePage;
import Utils.TestBase;
import Utils.common;

public class ValidateEventCreation extends common {
	TestBase tb = new TestBase();
	HomePage hp = new HomePage();
	EventCreationPage ecp = new EventCreationPage();

	@BeforeMethod
	public void setup() throws Exception {
		tb.initiateDriver(readProperty("run.platform"));
	}

	@Test
	public void validateEventCreation() throws Exception {
		hp.tapOnPlus();
		hp.tapOnCreateEvent();
		ecp.createEvent();
		hp.gotoEventDate();
		Assert.assertTrue(hp.verifyCreatedEvent());
	}

	@AfterMethod
	public void teardown() {
		tb.close();
	}

}
