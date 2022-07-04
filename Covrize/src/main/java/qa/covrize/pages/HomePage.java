package qa.covrize.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.covrize.base.TestBase;

public class HomePage extends TestBase{
	
	//***************************Web Elements*****************************
	
	@FindBy(xpath="//img[@alt='Covrize It Solutions Private Limited']")
	private WebElement CovrizeLogo;
	
	//*****************************Constructor*****************************
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	 //****************************Page Methods****************************
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	
	public boolean verifyLogo() {
		return CovrizeLogo.isDisplayed();
		
	}
	 

}
