package openPack;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import browser.OpenBrowser;


@Test
public class Demo 
{
	
	public WebDriver driver;
	
	@BeforeTest
	public void startBrowser() 
	{
		OpenBrowser browse = new OpenBrowser();
		driver = browse.myDriver();
	}
	
	
	public void demoTest() throws IOException
	{
		Reporter.log("Chrome Browser got launched");
		driver.manage().window().maximize();
		Reporter.log("Navigate to passport application website");
		driver.get("http://localhost/form.html");
		Reporter.log("Executing test case 1:");
		Assert.assertTrue(true);
		File src1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File("screenshots\\screenshot1.png"));
		Reporter.log("<a href=\"C:\\Users\\harsh\\eclipse-workspace\\ProjOne\\screenshots\\screenshot1.png\" target=\"_blank\"><img src=\"C:\\Users\\harsh\\eclipse-workspace\\ProjOne\\screenshots\\screenshot1.png\" width=\"500\" height=\"333\"></a>");
		driver.close();
		
	}

}
