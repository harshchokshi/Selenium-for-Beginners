package qa.covrize.testcases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import qa.covrize.base.TestBase;
import qa.covrize.listener.ExtentManager;
import qa.covrize.listener.ExtentTestManager;
import qa.covrize.pages.ContactPage;

public class ContactPageTestCase extends TestBase{
	
	ContactPage contactpage;
	
	//to call TestBase methods, and to access the superclass constructor
	public ContactPageTestCase() {
		super();
	}
	
	@BeforeMethod
	public void setUp(ITestContext context) {
		initialization(context);
		contactpage = new ContactPage();	
		//context.setAttribute("WebDriver", driver);
	}

	//@Test(priority=1, description="To verify that user is able to redirect to contact page.")
	public void scenerio_1() throws InterruptedException {
		
		
		boolean UsergetContactPage = contactpage.openContactPage();
		
		if (UsergetContactPage==true) 
		{
			ExtentTestManager.getTest().log(Status.INFO, "User is able to redirect to Contact Us Page.");
			Assert.assertTrue(true);
		}else 
		{
			ExtentTestManager.getTest().log(Status.INFO, "User is not able to redirect to Contact Us Page.");
			Assert.assertTrue(false);
		}
		
	}
	
  	@DataProvider(name = "test-data")
  	public Object[][] dataProvFunc(){
        	return new Object[][]{
              	{"Harsh", "Chokshi", "harsh.c@something.com","8511012831", "Product Engineering Services", "USD 50000-100000", "This is Automation Test Message."}
        	};
  	}
  	
	@Test(priority=2, description="To verify that user is able to submit the Contact Us form.", dataProvider ="test-data")
	public void scenerio_2(String firstName, String lastName, String email, String mobileNumber, String service, String budget, String message) throws InterruptedException {
		
		
		boolean UsergetContactPage = contactpage.openContactPage();
		
		if (UsergetContactPage==true) 
		{
			ExtentTestManager.getTest().log(Status.INFO, "Step 4: Enter valid value in \"First Name\" textbox.");
			contactpage.enterFirstName(firstName);
			ExtentTestManager.getTest().log(Status.INFO, "Step 5: Enter valid value in \"Last Name\" textbox.");
			contactpage.enterLastName(lastName);
			ExtentTestManager.getTest().log(Status.INFO, "Step 6: Enter valid value in \"Email\" textbox.");
			contactpage.enterEmail(email);
			ExtentTestManager.getTest().log(Status.INFO, "Step 7: Enter valid value in \"Mobile Number\" textbox.");
			contactpage.enterPhoneNumber(mobileNumber);
			ExtentTestManager.getTest().log(Status.INFO, "Step 7: Select required value from \"Service you are looking for \" dropdown.");
			contactpage.selectService(service);
			ExtentTestManager.getTest().log(Status.INFO, "Step 8: Select required value from \"Project Budget\" dropdown.");
			contactpage.selectBudget(budget);
			ExtentTestManager.getTest().log(Status.INFO, "Step 9: Enter valid value in \"Message\" textbox.");
			contactpage.enterMessage(message);
			ExtentTestManager.getTest().log(Status.INFO, "Step 10: Click on \"Captcha\" checkbox.");
			Thread.sleep(8000);
			contactpage.clickSubmitBtn();
			ExtentTestManager.getTest().log(Status.INFO, "Step 11: Click on \"Submit\" button.");
			Thread.sleep(8000);
			Assert.assertTrue(contactpage.formSuccess());
		}else 
		{
			ExtentTestManager.getTest().log(Status.INFO, "User is not able to redirect to Contact Us Page.");
			Assert.assertTrue(false);
		}
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
}
}
