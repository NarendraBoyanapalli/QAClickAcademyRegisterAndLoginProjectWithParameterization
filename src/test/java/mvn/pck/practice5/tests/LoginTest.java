package mvn.pck.practice5.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mvn.pck.practice5.pages.QAClickHomePage;
import mvn.pck.practice5.pages.QAClickLoginPage;
import mvn.pck.practice5.pages.QAClickRegisterPage;
import mvn.pck.practice5.resources.BaseRepoClass;

public class LoginTest extends BaseRepoClass {

	QAClickHomePage qaClickHomePage;
	QAClickRegisterPage qaClickRegisterPage;
	QAClickLoginPage qaClickLoginPage;
	Object[][] registerData, loginData;
	WebDriver driver;
	Logger logger;

	@BeforeTest
	public void initializeWebDriver() throws IOException {
		driver = initializeDriver();
		logger = LogManager.getLogger(LoginTest.class.getName());
	}

	@Test()
	public void launchHomePage() {
		qaClickHomePage = new QAClickHomePage(driver);
		driver.get(System.getProperty("homePageURL"));
		logger.info("Launched Home Page URL.");
	}

	@Test()
	public void launchLoginPage() {
		qaClickLoginPage = qaClickHomePage.login();
		logger.info("Clicked on Login URL.");
	}

	@Test(dataProvider = "getDataToLogin")
	public void login(String emailID, String password) {
		qaClickLoginPage = new QAClickLoginPage(driver);
		logger.info("Login Page Object Created.");
		qaClickLoginPage.loginEmailAddress().sendKeys(emailID);
		logger.info("Entered Email ID..");
		qaClickLoginPage.loginPassword().sendKeys(password);
		logger.info("Entered Password.");
		qaClickLoginPage.logIn().click();
		logger.info("Clicked Login.");
	}

	@AfterTest
	public void closeWebDriver() {
		driver.close();
		logger.info("Closed Browser Window.");
		driver.quit();
		logger.info("Quit Driver Session.");
	}

	@DataProvider
	public Object[][] getDataToLogin() {
		loginData = new Object[1][2];

		loginData[0][0] = "narendra.boyanapalli@gmail.com";
		logger.info("DataProvider Provided Email ID.");
		loginData[0][1] = "NBaren125";
		logger.info("DataProvider Provided Password.");

		return loginData;
	}
}
