package mvn.pck.practice5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QAClickLoginPage {
	
	public WebDriver driver;
	private Logger logger;
	
	public QAClickLoginPage(WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(QAClickLoginPage.class.getName());
	}
	
	@FindBy(css = "//h1[contains(text(),'Log In to')]")
	private WebElement loginPageTitle;
	
	@FindBy(css = "[type='email']")
	private WebElement loginEmailAddress;

	@FindBy(css = "[type='password']")
	private WebElement loginPassword;
	
	@FindBy(css = ".btn.btn-primary.btn-md.login-button")
	private WebElement logIn;
	
	public WebElement loginPageTitle() {
		logger.debug("Identified Login Page Title Element. ", loginPageTitle);
		return loginPageTitle;
	}
	
	public WebElement loginEmailAddress() {
		logger.debug("Identified Login Email Address Element. ", loginEmailAddress);
		return loginEmailAddress;
	}
	
	public WebElement loginPassword() {
		logger.debug("Identified Login Password Element. ", loginPassword);
		return loginPassword;
	}
	
	public WebElement logIn() {
		logger.debug("Identified Login Element. ", logIn);
		return logIn;
	}
}
