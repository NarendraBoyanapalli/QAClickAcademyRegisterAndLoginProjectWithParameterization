package mvn.pck.practice5.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mvn.pck.practice5.pages.QAClickHomePage;
import mvn.pck.practice5.pages.QAClickLoginPage;
import mvn.pck.practice5.pages.QAClickRegisterPage;
import mvn.pck.practice5.resources.BaseRepoClass;

public class RegisterTest extends BaseRepoClass {

	QAClickHomePage qaClickHomePage;
	QAClickRegisterPage qaClickRegisterPage;
	QAClickLoginPage qaClickLoginPage;
	Object[][] registerData, loginData;
	WebDriver driver;
	Logger logger;

	@BeforeTest
	public void initializeWebDriver() throws IOException {
		driver = initializeDriver();
		logger = LogManager.getLogger(RegisterTest.class.getName());
	}

	@Test()
	public void launchHomePage() {
		qaClickHomePage = new QAClickHomePage(driver);
		driver.get(System.getProperty("homePageURL"));
		logger.info("Launched Home Page URL.");
	}

	@Test()
	public void launchRegisterPage() {
		qaClickRegisterPage = qaClickHomePage.register();
		logger.info("Clicked on Register URL.");
	}

	@Test(dataProvider = "getDataToRegister")
	public void register(String fullName, String emailID, String password) {
		qaClickRegisterPage = new QAClickRegisterPage(driver);
		logger.info("Register Page Object Created.");
		qaClickRegisterPage.fullName().sendKeys(fullName);
		logger.info("Entered Full Name.");
		qaClickRegisterPage.signUpEmailAddress().sendKeys(emailID);
		logger.info("Entered Email ID.");
		qaClickRegisterPage.password().sendKeys(password);
		logger.info("Entered Password.");
		qaClickRegisterPage.confirmPassword().sendKeys(password);
		logger.info("Confirmed Entered Password.");
		qaClickRegisterPage.agreeTerms().click();
		logger.info("Clicked Agree Terms.");
		qaClickRegisterPage.signUp().click();
		logger.info("Clicked Sign Up.");
		
		Assert.assertFalse(qaClickRegisterPage.alreadyRegisteredError().isDisplayed(), "Email is Already In Use.");
		logger.error("Registration Not Succesfull As Email Already In Use.");
	}

	@AfterTest
	public void closeWebDriver() {
		driver.close();
		logger.info("Closed Browser Window.");
		driver.quit();
		logger.info("Quit Driver Session.");
	}

	@DataProvider
	public Object[][] getDataToRegister() {
		registerData = new Object[1][3];

		registerData[0][0] = "Narendra Boyanapalli";
		logger.info("DataProvider Provided Full Name.");
		registerData[0][1] = "narendra.boyanapalli@gmail.com";
		logger.info("DataProvider Provided Email ID.");
		registerData[0][2] = "NBaren125";
		logger.info("DataProvider Provided Password..");

		return registerData;
	}
}
