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

public class LoginWithInvalidEmail {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();

		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void TC_001_NavigateToLoginPage() {
		WebElement elementMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		elementMyAccount.click();
	}

	@Test(priority = 2)
	public void TC_002_LoginWithInvalidEmail() {
		WebElement elementEmail = driver.findElement(By.xpath(".//*[@id='email']"));
		elementEmail.clear();
		elementEmail.sendKeys("123434234@12312.123123");

		elementEmail.submit();
	}

	@Test(priority = 3)
	public void TC_003_VerifyErrorMessage() {
		WebElement elementErrorMessage = driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']"));
		String actualErrorMessage = elementErrorMessage.getText();
		Assert.assertEquals(actualErrorMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
