package Utils;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;


public class TestBase extends common {
	public static AppiumDriver driver;
	public Properties prop;
	public  long PAGE_LOAD_TIMEOUT = 30;
	public  long IMPLICIT_WAIT = 10;
	
	
	public  void initiateDriver(String platform) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

	String completeURL = "http://" + readProperty("run.ip") + ":" + readProperty("run.port") + "/wd/hub";

	 switch (platform.toLowerCase()) {

		case "ios":
			//Setting up required desired capabilities
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, readProperty("ios.device.name"));
            capabilities.setCapability(MobileCapabilityType.UDID,readProperty("ios.device.name"));
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
            capabilities.setCapability(MobileCapabilityType.APP,readProperty("ipa.path"));
            
            driver = new IOSDriver(new URL(completeURL), capabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
		break;

		case "android":
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, readProperty("android.device.name"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability("appPackage",  readProperty("apppackage"));
			capabilities.setCapability("appActivity",  readProperty("appactivity"));
			
			driver = new AndroidDriver(new URL(completeURL), capabilities);
			
			break;

		
		default:
			throw new Exception("Platform not supported");
		}

	}
	
	public List<WebElement> findElements(By byloc) throws Exception {
	
		return driver.findElements(byloc);
	}
	
	public WebElement findElement(By byloc) throws Exception {
		return driver.findElement(byloc);
	}

	public boolean isElementPresent(By byloc) {
		try {
			if (driver.findElement(byloc).isDisplayed()) return true;
			else return false;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean isElementPresent(By loc, int wait) {
		try {
			for (int i=0; i<wait; i++) {
				if (isElementPresent(loc)) return true;
				sleep(1);
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void waitForElement(By loc, int wait) throws Exception {
		if (!isElementPresent(loc, wait)) throw new NoSuchFieldException("Locator " + loc + " not found.");
	}
	public void type(By byloc, String text) throws Exception {
		driver.findElement(byloc).clear();
		driver.findElement(byloc).sendKeys(text);
	}
	
	public void type(By loc, String text, int wait) throws Exception {
		if (!isElementPresent(loc, wait)) throw new NoSuchFieldException("Locator " + loc + " not found.");
		type(loc, text);
	}
	public void moveToElement(By loc) {
		Actions act= new Actions(driver);
		try {
			act.moveToElement(findElement(loc)).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	public String getText(By byloc) throws Exception {
		 return driver.findElement(byloc).getText();
	}
	
	public void sleep(int sec) throws Exception {
		Thread.sleep(sec * 1000L);
	}
	
	public void click(By byloc) throws Exception {
		driver.findElement(byloc).click();
	}
	public void clickByIndex(By byloc,int index) throws Exception {
		((WebElement)driver.findElements(byloc).get(index)).click();
	}
	 public static void swipeLeft() {
	    	
	        Dimension size = driver.manage().window().getSize();
	        int startx = (int)((double)size.width * 0.90000000000000002D);
	        int endx = (int)((double)size.width * 0.10000000000000001D);
	        int starty = size.height / 4;
	        int endy = size.height / 4;
	   
	        TouchAction action = (new TouchAction(driver)).tap(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
	        driver.performTouchAction(action); 
	    }
	    


	public void click(By loc, int wait) throws Exception {
		if (!isElementPresent(loc, wait)) throw new NoSuchFieldException("Locator " + loc + " not found.");
		click(loc);
	}
	
	public void open(String url) {
		driver.get(url);
	}
	public void close() {
		driver.quit();
	}
}
