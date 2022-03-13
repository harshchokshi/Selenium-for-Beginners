package lesson3;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import browser.OpenBrowser;

public class GoogleSearch2 
{
	public WebDriver driver;
	
	@BeforeTest
	public void startBrowser() 
	{
		OpenBrowser browse = new OpenBrowser();
		driver = browse.myDriver();
		driver.navigate().to("https://www.google.com/");
	}
	
	@Test
	public void openLink() 
	{
		//Find the search bar
		driver.findElement(By.name("q"));
		
		//Enter the value in search bar
		driver.findElement(By.name("q")).sendKeys("site: sgligis.com");
		
		//Click on Google search button
		driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@class='gNO89b']")).click();
		
		//Click on first web site in the Google search avoid ads elements
		driver.findElement(By.xpath("//div[@class='TbwUpd NJjxre']//cite")).click();
	}
}
