package lesson3;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;


import browser.OpenBrowser;

public class GoogleSignIn 
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
	
	@Test(priority = 1)
	public void clickSignIn() 
	{
		driver.findElement(By.linkText("Sign in")).click();
	}
	
	@Test(priority = 2)
	public void enterEmailorPhone() 
	{
		driver.findElement(By.name("identifier")).sendKeys("harsh89.resume@gmail.com");
	}
	
	@Test(priority = 3)
	public void clickNext1() 
	{
		driver.findElement(By.xpath("//span[text()='Next']")).click();
	}
	
	
	@Test(priority = 4)
	public void clickPassword() 
	{
		driver.findElement(By.name("password")).sendKeys("@BeforeTest[]Obj");
	}
	
	@Test(priority = 5)
	public void clickNext2() 
	{
		driver.findElement(By.className("VfPpkd-vQzf8d")).click();
		
	}
	
	
	
	
}
