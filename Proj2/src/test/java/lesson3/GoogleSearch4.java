package lesson3;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import browser.OpenBrowser;

public class GoogleSearch4 
{
	public WebDriver driver;
	
	@BeforeTest
	public void startBrowser() 
	{
		OpenBrowser browse = new OpenBrowser();
		driver = browse.myDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	}
	
	
	public void googleSearch1() 
	{
		// Go to search bar

		driver.findElement(By.name("btnK"));

		// Enter the value

		driver.findElement(By.name("btnK")).sendKeys("ScanPoint Geomatics");

		// Press enter

		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		/*
		 * FAILED: googleSearch org.openqa.selenium.ElementNotInteractableException:
		 * element not interactable
		 * This happen because I have enter incorrect locate 
		 * btnK is the name for Google Search button  
		 * We can't pass input to button type element 
		 * only click event is perform in the button type
		 */
	}
	
	@Test
	public void googleSearch2() 
	{
		//Go to search bar 
		
		driver.findElement(By.name("q"));
		
		//Enter the value
		
		driver.findElement(By.name("q")).sendKeys("ScanPoint Geomatics");
		
		//Press enter
		
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
	}
	
}
