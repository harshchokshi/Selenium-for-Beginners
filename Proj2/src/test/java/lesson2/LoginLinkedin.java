package lesson2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginLinkedin 
{

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Software\\Software Developers\\Selenium Java\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.linkedin.com/");
		
		driver.findElement(By.linkText("Sign in")).click();
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("harsh.choksi1@gmail.com");
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("z1c2@B3M4$x0o");
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@class='login__form_action_container ']//button[@aria-label='Sign in']")).click();
	}
}
