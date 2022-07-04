package qa.covrize.listener;

import java.io.*;




import com.aventstack.extentreports.model.Log;
import org.openqa.selenium.io.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.sql.Timestamp;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.*;

public class TestListener implements ITestListener {
	public String getClassName(ITestContext iTestContext) {

		return iTestContext.getCurrentXmlTest().getClasses().stream().findFirst().get().getName();
	}

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");

	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		String Dis = result.getMethod().getDescription();

		ExtentTestManager.getTest().log(Status.INFO,
				String.format("<b style=\"color:#000080;font-size:18px;\">Test Description : " + Dis + "</b>"));

	}

	public void onTestSuccess(ITestResult result) {

		ITestContext context = result.getTestContext();
		// WebDriver driver = (WebDriver) context.getAttribute("driver");
		WebDriver driver = null;

		driver = (WebDriver) context.getAttribute("WebDriver");
		String targetLocation = null;

		String testClassName = getTestClassName(result.getInstanceName()).trim();

		// String timeStamp = Util.getCurrentTimeStamp(); // get timestamp
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "Screenshots-PASS";
//		log.info("Screen shots reports path - " + reportsPath);
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
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");

	}

//	public void onTestFailure(ITestResult result) {
//		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
//		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
//	}
	public void onTestFailure(ITestResult result) {
		// log.info("*** Test execution " + result.getMethod().getMethodName() + "
		// failed...");
		// log.info((result.getMethod().getMethodName() + " failed!"));

		ITestContext context = result.getTestContext();
		// WebDriver driver = (WebDriver) context.getAttribute("driver");
		WebDriver driver = null;

		driver = (WebDriver) context.getAttribute("WebDriver");
		String targetLocation = null;

		String testClassName = getTestClassName(result.getInstanceName()).trim();

		// String timeStamp = Util.getCurrentTimeStamp(); // get timestamp
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
//		log.info("Screen shots reports path - " + reportsPath);
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

			// ExtentTestManager.getTest().log(Status.FAIL, "Failed Case is: " +
			// result.getName());

			ExtentTestManager.getTest().fail("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());

			ExtentTestManager.getTest().log(Status.FAIL,
					result.getName() + " FAIL with error " + result.getThrowable());

		} catch (IOException e) {
			e.printStackTrace();
			// log.info("An exception occured while taking screenshot " + e.getCause());
		}
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}
}