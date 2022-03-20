package lesson6;

//Packages for reading Property File
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Packages for TestNG framework
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//Packages for Selenium
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MumbaiHighCourt 
{
	public WebDriver driver;
	public Properties prop;
	
	@Test
	public void contact() throws InterruptedException 
	{
		openPage(prop.getProperty("menuAbout"), prop.getProperty("submenuContact"));
		verifyURL("contact");
		changeTextSizeBtns(prop.getProperty("contentContact"));
	}
	
	@Test
	public void history() throws InterruptedException 
	{
		openPage(prop.getProperty("menuAbout"), prop.getProperty("submenuHistory"));
		verifyURL("history");
		changeTextSizeBtns(prop.getProperty("contentHistory"));
	}
	
	@Test
	public void formerChiefJustices() throws InterruptedException 
	{
		openPage(prop.getProperty("menuJudges"), prop.getProperty("submenuformerChiefJustices"));
		verifyURL("former");
	}
	
	public void changeTextSizeBtns(String textContentLocater)
	{
		SoftAssert softassert = new SoftAssert();
		System.out.println("Default size: " + driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		driver.findElement(By.xpath(prop.getProperty("smallTextBtn"))).click();
		System.out.println("Small size: " + driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		softassert.assertEquals("13px", driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		driver.findElement(By.xpath(prop.getProperty("extraSmallTextBtn"))).click();
		System.out.println("Extra Small size: " + driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		softassert.assertEquals("12px", driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		driver.findElement(By.xpath(prop.getProperty("normalTextBtn"))).click();
		System.out.println("Normal size: " + driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		softassert.assertEquals("15px", driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		driver.findElement(By.xpath(prop.getProperty("largeTextBtn"))).click();
		System.out.println("Large size: " + driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		softassert.assertEquals("17px", driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		driver.findElement(By.xpath(prop.getProperty("extraLargeTextBtn"))).click();
		System.out.println("Extra Large size: " + driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		softassert.assertEquals("18px", driver.findElement(By.xpath(textContentLocater)).getCssValue("font-size"));
		softassert.assertAll("One of the assert got failed.");
	}
	
	public void verifyURL(String urlValue) 
	{
		SoftAssert softassert = new SoftAssert();
		
		boolean expectedResult = true;
		boolean actualResult = false;
		
		if (driver.getCurrentUrl().contains(urlValue)) {
			actualResult = true;
		}
		else
		{
			actualResult = false;
		}
		softassert.assertEquals(actualResult, expectedResult);
		System.out.println("Correct Page: "+driver.getCurrentUrl());
		softassert.assertAll(urlValue+" is not exist in the URL value");
	}
	
	
	public void openPage(String menuOption, String submenuOption) throws InterruptedException
	{
		Actions act = new Actions(driver);
		WebElement menuElement = driver.findElement(By.xpath(menuOption));
		Thread.sleep(1000);
		act.moveToElement(menuElement);
		act.perform();
		System.out.println(menuElement.isDisplayed());
		WebElement subElement = driver.findElement(By.xpath(submenuOption));
		Thread.sleep(1000);
		System.out.println(subElement.isDisplayed());
		subElement.click();
	}
	
	@BeforeTest
	public void configure() 
	{	
		readDataPropertyFile();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://bombayhighcourt.nic.in");
	}
	
	
	public void readDataPropertyFile() 
	{
		File file = new File("C:\\Users\\harsh\\eclipse-workspace\\MumbaiCourt\\src\\test\\java\\mumbaiCourt\\DataFile.properties");
		FileInputStream fis = null;
		try 
		{
			fis = new FileInputStream(file);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
