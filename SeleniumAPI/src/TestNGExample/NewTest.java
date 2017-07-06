package TestNGExample;

/**
 * @author macos
 *
 */

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class NewTest {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", "/Users/macos/Documents/AutoDev/Libs/geckodriver");
		driver = new FirefoxDriver();

	}

	@Test
	public void TC_01() {
		driver.get("http://live.guru99.com/");
	}

	@AfterTest
	public void afterTest() {

		// driver.quit();
	}
}
