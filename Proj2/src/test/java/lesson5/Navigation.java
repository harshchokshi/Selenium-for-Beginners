package lesson5;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import browser.OpenBrowser;

public class Navigation 
{
	public WebDriver driver;

	@BeforeSuite
	public void startBrowser() 
	{
		OpenBrowser browse = new OpenBrowser();
		driver = browse.myDriver();
	}

	@Test(priority = 1)
	public void openLink() 
	{
		driver.navigate().to("https://www.google.com/");
		// Find the search bar
		driver.findElement(By.name("q"));

		// Enter the value in search bar
		driver.findElement(By.name("q")).sendKeys("site: sgligis.com");

		// Click on Google search button
		driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@class='gNO89b']")).click();

		// Click on first web site in the Google search avoid ads linking targeting
		// first link class
		driver.findElement(By.xpath("//div[@class='TbwUpd NJjxre']//cite")).click();
	}

	@Test(priority = 2)
	public void openContactPage() 
	{
		// Verify that driver holds new URL if priority remove then it go back to
		// Google.com
		System.out.println("Open Contact Page: " + driver.getCurrentUrl());
		try {
			driver.findElement(By.xpath("//ul[@id='menu-main-menu']/li[4]//child::a")).click();
		} catch (Exception e) {
			System.out.println("Unable to locate contact page link");
		}
	}

	@Test(priority = 3)
	public void verifyContactNumbers() 
	{
		String expectedPhoneNumber = "+ 91 2717 297096";
		try {
			Assert.assertTrue(driver.getPageSource().contains(expectedPhoneNumber));
		} catch (java.lang.AssertionError e) {
			throw new AssertionError("Test case failed as expectedPhoneNumber does not exist.");
		}
	}

	@Test(priority = 4)
	public void verifyEmailAddress() 
	{
		String expectedEmailAddress = "info@sgligis.com";
		try {
			Assert.assertTrue(driver.getPageSource().contains(expectedEmailAddress));
		} catch (java.lang.AssertionError e) {
			throw new AssertionError("Test case failed as expectedEmailAddress does not exist.");
		}
	}

	@Test(priority = 5)
	public void forwardNavigation() 
	{
		//Navigating forward in browser history
		driver.navigate().forward();
		System.out.println("Forward Navigation: "+driver.getCurrentUrl());
	}
	
	@Test(priority = 6)
	public void backNavigation() 
	{
		// Navigating backward in browser history
		driver.navigate().back();
		System.out.println("Backward Navigation: "+driver.getCurrentUrl());
	}
	
	@Test(priority = 7)
	public void reloadRefresh() 
	{
		//Refresh/Reload a web page
		driver.navigate().refresh();
		System.out.println("Refereshing... "+driver.getCurrentUrl());
	}

	@AfterTest
	public void closeBrowser() 
	{
		driver.close();
	}
	
	//What is the difference between browser close and browser quit
	public void quitBrowser() 
	{
		driver.quit();
	}
}
