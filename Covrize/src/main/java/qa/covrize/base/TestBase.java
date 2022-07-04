package qa.covrize.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;

import com.aventstack.extentreports.Status;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import qa.covrize.listener.ExtentTestManager;
import qa.covrize.utility.TestUtil;



public class TestBase {
	
	public static Properties prop;
	public static WebDriver driver;
	
	
	public TestBase(){
		try {
			
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/qa/covrize/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void initialization(ITestContext context){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			 
		}
		else if(browserName.equals("FF")){
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		
		driver.get(prop.getProperty("url"));
		context.setAttribute("WebDriver", driver);
}
}
