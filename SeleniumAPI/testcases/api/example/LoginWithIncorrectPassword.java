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

public class LoginWithIncorrectPassword {
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
	public void TC_002_LoginWithIncorrectPass() {
		WebElement elementEmail = driver.findElement(By.xpath(".//*[@id='email']"));
		elementEmail.clear();
		elementEmail.sendKeys("automation@gmail.com");
		WebElement elementPass = driver.findElement(By.xpath(".//*[@id='pass']"));
		elementPass.clear();
		elementPass.sendKeys("123");

		elementEmail.submit();
	}

	@Test(priority = 3)
	public void TC_003_VerifyErrorMessage() {
		WebElement elementErrorMessage = driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']"));
		String actualErrorMessage = elementErrorMessage.getText();
		Assert.assertEquals(actualErrorMessage,
				"Please enter 6 or more characters. Leading or trailing spaces will be ignored.");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
