package qa.covrize.testcases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import qa.covrize.base.TestBase;
import qa.covrize.listener.ExtentManager;
import qa.covrize.listener.ExtentTestManager;
import qa.covrize.pages.HomePage;

public class HomePageTestCase extends TestBase{
	
	HomePage homepage;
	
	//to call TestBase methods, and to access the superclass constructor
	public HomePageTestCase() {
		super();
	}
	
	@BeforeMethod
	public void setUp(ITestContext context) {
		initialization(context);
		homepage = new HomePage();	
		//context.setAttribute("WebDriver", driver);
	}

	@Test(priority=1,description="verifying title of the site.")
	public void verifyHomePageTitleName() {
		ExtentTestManager.getTest().log(Status.INFO, "User is on landing page of covrize.");
		ExtentTestManager.getTest().log(Status.INFO, "Verifying the home page title.");
		String title = homepage.verifyHomePageTitle();
		Assert.assertEquals(title, "Covrizeq | BEST IT outsourcing company in India | Offshore project consulting services");
		System.out.println(title);
	}
	
	@Test(priority=2,description="verifying Site Logo.")
	public void verifyCovrizeLogo() {
		ExtentTestManager.getTest().log(Status.INFO, "Verifying the covrize logo.");
		boolean logo = homepage.verifyLogo();
		Assert.assertEquals(logo, true);
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
}
}
