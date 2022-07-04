package qa.covrize.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import qa.covrize.base.TestBase;
import qa.covrize.listener.ExtentTestManager;

public class ContactPage extends TestBase{

	//***************************Web Elements*****************************

	@FindBy(xpath="//a[@class='contact-btn']")
	private WebElement contact_btn;

	@FindBy(xpath="//input[@type='email']")
	private WebElement email;

	@FindBy(xpath="//input[@class='PhoneInputInput']")
	private WebElement mobileNumber;

	@FindBy(xpath="//input[@name='firstName']")
	private WebElement firstName;

	@FindBy(xpath="//input[@name='lastName']")
	private WebElement lastName;


	@FindBy(xpath="//select[@name='service']")
	private WebElement selectService;

	@FindBy(xpath="//select[@name='budget']")
	private WebElement selectBudget;

	@FindBy(xpath="//textarea[@name='description']")
	private WebElement message;

	@FindBy(xpath="//span[@id='recaptcha-anchor']")
	private WebElement captchaCheckBox;

	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submit_btn;

	@FindBy(xpath="//p[@class=' text-success']")
	private WebElement form_success;


	//*****************************Constructor*****************************
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	//****************************Page Methods****************************



	public boolean openContactPage() throws InterruptedException 
	{
		ExtentTestManager.getTest().log(Status.INFO, "Step 1: Open the browser.");

		ExtentTestManager.getTest().log(Status.INFO, "Step 2: Enter URL of \"Coverize\" website.");

		ExtentTestManager.getTest().log(Status.INFO, "Step 3: Click on \"Contact Us\" button.");
		contact_btn.click();
		Thread.sleep(3000);
		String contactPageTitle = driver.getTitle();

		if (contactPageTitle.contains("Contact") ) 
		{

			return true;
		}
		else
		{
			System.out.println("Condition False"+contactPageTitle);
			System.out.println("Something went wrong Contact Page is not getting open.");
			return false;

		}
	}

	public void enterFirstName(String userEnterFirstName) throws InterruptedException 
	{

		firstName.sendKeys(userEnterFirstName);
		firstName.sendKeys(Keys.ENTER);
	}

	public void enterLastName(String userEnterLastName) throws InterruptedException 
	{

		lastName.sendKeys(userEnterLastName);
		lastName.sendKeys(Keys.ENTER);
	}

	public void enterEmail(String userEnterEmail) throws InterruptedException 
	{

		email.sendKeys(userEnterEmail);
		email.sendKeys(Keys.ENTER);
	}

	public void enterPhoneNumber(String userEnterMobileNumber) throws InterruptedException 
	{

		mobileNumber.sendKeys(userEnterMobileNumber);
		mobileNumber.sendKeys(Keys.ENTER);
	}

	public void selectService(String selectValue) throws InterruptedException 
	{
		Select select_Service = new Select(selectService);
		select_Service.selectByVisibleText(selectValue);
		Thread.sleep(3000);
	}

	public void selectBudget(String selectValue) throws InterruptedException {
		Select select_Budget = new Select(selectBudget);

		select_Budget.selectByVisibleText(selectValue);
		Thread.sleep(3000);
	}

	public void enterMessage(String userEnterMessage) throws InterruptedException 
	{

		message.sendKeys(userEnterMessage);
		message.sendKeys(Keys.ENTER);
	}

	public void clickOnCaptchaCheckbox() 
	{
		captchaCheckBox.click();
	}

	public void clickSubmitBtn() 
	{
		Actions action = new Actions(driver);

		action.moveToElement(submit_btn).click().perform();
	}

	public boolean formSuccess()
	{
		return form_success.isDisplayed();
	}

}
