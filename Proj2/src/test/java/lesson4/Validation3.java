package lesson4;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Validation3 {

	public static void main(String[] args) {
		Validation3 tutorial = new Validation3();
		tutorial.testAssertFunctions();
	}

	public void testAssertFunctions() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Software\\Software Developers\\Selenium Java\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.browserstack.com/");
		String ActualTitle = driver.getTitle();
		String verifyAssertNull = null;
		String ExpectedTitle = "Most Reliable App & Cross Browser Testing Platform | BrowserStack";
		Boolean verifyTitleIsPresent = driver.getTitle()
				.equalsIgnoreCase("Most Reliable App & Cross Browser Testing Platform | BrowserStack");
		Boolean verifyTitleIsChanged = driver.getTitle().equalsIgnoreCase("Testing Platform | BrowserStack");
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		Assert.assertNotEquals(ExpectedTitle, "browserstack");
		Assert.assertTrue(verifyTitleIsPresent);
		Assert.assertFalse(verifyTitleIsChanged);
		Assert.assertNotNull(verifyTitleIsPresent);
		Assert.assertNull(verifyAssertNull);
	}
}