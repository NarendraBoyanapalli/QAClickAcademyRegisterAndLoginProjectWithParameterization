package mvn.pck.practice5.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QAClickHomePage {

	private WebDriver driver;
	private Logger logger;

	public QAClickHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(QAClickHomePage.class.getName());
	}

	@FindBy(xpath = "//span[text()='Register']")
	private WebElement register;

	@FindBy(xpath = "//span[text()='Login']")
	private WebElement login;

	public QAClickRegisterPage register() {
		logger.debug("Identified Register Element. ", register);
		register.click();
		return new QAClickRegisterPage(driver);
	}

	public QAClickLoginPage login() {
		logger.debug("Identified Login Element. ", login);
		login.click();
		return new QAClickLoginPage(driver);
	}
}
