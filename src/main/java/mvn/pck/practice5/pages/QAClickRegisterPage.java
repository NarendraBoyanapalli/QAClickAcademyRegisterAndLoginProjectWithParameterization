package mvn.pck.practice5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QAClickRegisterPage {

	public WebDriver driver;
	private Logger logger;

	public QAClickRegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(QAClickRegisterPage.class.getName());
	}

	@FindBy(xpath = "//h1[contains(text(),'Sign Up to Rahul')]")
	private WebElement signUpPageTitle;

	@FindBy(css = "#user_name")
	private WebElement fullName;

	@FindBy(css = "#user_email")
	private WebElement signUpEmailAddress;

	@FindBy(name = "user[password]")
	private WebElement password;

	@FindBy(id = "user_password_confirmation")
	private WebElement confirmPassword;

	@FindBy(className = "agreed_to_terms_v1")
	private WebElement agreeTerms;

	@FindBy(css = "[name='commit']")
	private WebElement signUp;
	
	@FindBy(xpath = "//div[contains(@class,'alert-danger')]//li[contains(text(),'Email')]")
	private WebElement alreadyRegisteredError;

	public WebElement signUpPageTitle() {
		logger.debug("Identified Sign Up PageTitle Element. ", signUpPageTitle);
		return signUpPageTitle;
	}

	public WebElement fullName() {
		logger.debug("Identified Full Name Element. ", fullName);
		return fullName;
	}

	public WebElement signUpEmailAddress() {
		logger.debug("Identified Sign Up Email Address Element. ", signUpEmailAddress);
		return signUpEmailAddress;
	}

	public WebElement password() {
		logger.debug("Identified Password Element. ", password);
		return password;
	}

	public WebElement confirmPassword() {
		logger.debug("Identified Confirm Password Element. ", confirmPassword);
		return confirmPassword;
	}

	public WebElement agreeTerms() {
		logger.debug("Identified agreeTerms Element. ", agreeTerms);
		return agreeTerms;
	}

	public WebElement signUp() {
		logger.debug("Identified Sign Up Element. ", signUp);
		return signUp;
	}
	
	public WebElement alreadyRegisteredError() {
		logger.debug("Identified Already Registered Error Element. ", alreadyRegisteredError);
		return alreadyRegisteredError;
	}
}
