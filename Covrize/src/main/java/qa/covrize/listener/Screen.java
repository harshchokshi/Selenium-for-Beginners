package qa.covrize.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Screen {

	public void Screnshot(WebDriver driver, String classname, String Methodname) {

		String targetLocation = null;

		String testClassName = classname;

		String testMethodName = Methodname;
		String screenShotName = testMethodName + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "Screenshots-PASS";

		try {
			File file = new File(reportsPath + fileSeperator + testClassName); // Set
																				// screenshots
																				// folder
			if (!file.exists()) {
				if (file.mkdirs()) {
					// log.info("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					// log.info("Failed to create directory: " + file.getAbsolutePath());
				}

			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
																											// location
			File targetFile = new File(targetLocation);
			// log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
			// log.info("Target File location - " + targetFile.getAbsolutePath());
			FileHandler.copy(screenshotFile, targetFile);

		} catch (FileNotFoundException e) {
			// log.info("File not found exception occurred while taking screenshot " +
			// e.getMessage());
		} catch (Exception e) {
			// log.info("An exception occurred while taking screenshot " + e.getCause());
		}

		// attach screenshots to report
		try {
			// ExtentTestManager.getTest().fail("Screenshot",
			// MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
			// ExtentTestManager.getTest().log(Status.PASS, "Pass Case is: " +
			// result.getName());

			ExtentTestManager.getTest().pass("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());

		} catch (IOException e) {
			e.printStackTrace();
			// log.info("An exception occured while taking screenshot " + e.getCause());
		}
		// System.out.println("*** Executed " + result.getMethod().getMethodName() + "
		// test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");

	}

}
