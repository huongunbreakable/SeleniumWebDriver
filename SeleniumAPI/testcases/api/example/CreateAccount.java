package api.example;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateAccount {
	WebDriver driver;

	String password = "Abc123";

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
	public void TC_002_CreateAnAccount() {
		WebElement elementCreateAccountButton = driver
				.findElement(By.xpath("//span[contains(text(),'Create an Account')]"));
		elementCreateAccountButton.click();

		WebElement elementFirstName = driver.findElement(By.xpath(".//*[@id='firstname']"));
		elementFirstName.clear();
		elementFirstName.sendKeys("Huong");

		WebElement elementLastName = driver.findElement(By.xpath(".//*[@id='lastname']"));
		elementLastName.clear();
		elementLastName.sendKeys("Kpa");

		WebElement elementEmail = driver.findElement(By.xpath(".//*[@id='email_address']"));
		elementEmail.clear();

		Random random = new Random();
		int n = random.nextInt(9999 + 1);
		String randomEmail = "huongkpa" + n + "@gmail.com";

		elementEmail.sendKeys(randomEmail);

		WebElement elementPass = driver.findElement(By.xpath(".//*[@id='password']"));
		elementPass.clear();
		elementPass.sendKeys(password);

		WebElement elementConfirmPass = driver.findElement(By.xpath(".//*[@id='confirmation']"));
		elementConfirmPass.clear();
		elementConfirmPass.sendKeys(password);

		elementConfirmPass.submit();

	}

	@Test(priority = 3)
	public void TC_003_VerifySuccessfulMessage() {
		WebElement elementSuccessfulMessage = driver.findElement(By.xpath("//.[@class='success-msg']//span"));
		String actualSuccessfulMessage = elementSuccessfulMessage.getText();
		Assert.assertEquals(actualSuccessfulMessage, "Thank you for registering with Main Website Store.");
	}

	@Test(priority = 4)
	public void TC_004_Logout() {
		WebElement elementAccount = driver
				.findElement(By.xpath("//a[@class='skip-link skip-account']//span[contains(text(),'Account')]"));
		elementAccount.click();

		WebElement elementLogout = driver.findElement(By.xpath("//.[contains(text(),'Log Out')]"));
		elementLogout.click();

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
