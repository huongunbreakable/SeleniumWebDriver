package api.example;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyUrlAndTitle {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();

		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void TC_001_VerifyPageTitle() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Home page");
		System.out.println("The page title is: " + actualTitle);
	}

	@Test(priority = 2)
	public void TC_002_NavigateToLoginPage() {
		WebElement elementMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		elementMyAccount.click();
	}

	@Test(priority = 3)
	public void TC_003_NavigateToRegisterPage() {
		WebElement elementCreateAnAccount = driver.findElement(By.xpath("//.[@title='Create an Account']"));
		String createAnAccountText = elementCreateAnAccount.getText();
		System.out.println(createAnAccountText);
		elementCreateAnAccount.click();

		// Verify the url
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://live.guru99.com/index.php/customer/account/create/";
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test(priority = 4)
	public void TC_004_BackToLoginPage() {
		driver.navigate().back();
	}

	@Test(priority = 5)
	public void TC_005_VerifyLoginPageUrl() {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://live.guru99.com/index.php/customer/account/";
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test(priority = 6)
	public void TC_006_ForwardToRegisterPage() {
		driver.navigate().forward();
	}

	@Test(priority = 7)
	public void TC_007_VerifyRegisterPageUrl() {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://live.guru99.com/index.php/customer/account/create/";
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@AfterTest
	public void afterTest() {
		// driver.quit();
	}

}
