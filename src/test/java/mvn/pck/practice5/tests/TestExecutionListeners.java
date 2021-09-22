package mvn.pck.practice5.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import mvn.pck.practice5.resources.BaseRepoClass;
import mvn.pck.practice5.resources.ExtentReporter;

public class TestExecutionListeners extends BaseRepoClass implements ITestListener {

	private Logger logger;
	WebDriver driver;
	private ExtentReports er = ExtentReporter.getExtentReporter();
	private ExtentTest et;
	private ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		et = er.createTest(result.getMethod().getMethodName());
		tl.set(et);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		tl.get().log(Status.PASS, result.getMethod().getMethodName() + " Execution Passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		tl.get().log(Status.FAIL, result.getMethod().getMethodName() + " Execution Failed.", result.getThrowable(), null);
		logger = LogManager.getLogger(TestExecutionListeners.class.getName());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception While Trying To Get WebDriver Instance.");
		}

		try {
			tl.get().addScreenCaptureFromPath(captureScreenshot(methodName, driver), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IO Exception While Trying To Capture Screenshot.");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		tl.get().log(Status.SKIP, result.getMethod().getMethodName() + " Execution Skipped.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		er.flush();
	}
}
