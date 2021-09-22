package mvn.pck.practice5.resources;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseRepoClass {
	private WebDriver driver;
	private Logger logger;

	public WebDriver initializeDriver() throws IOException {

		logger = LogManager.getLogger(BaseRepoClass.class.getName());

		switch (System.getProperty("browser")) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("driverPath"));
			driver = new ChromeDriver();
			logger.debug("Initialized Chrome Driver");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("driverPath"));
			driver = new FirefoxDriver();
			logger.debug("Initialized Firefox Driver");
			break;
		case "msedge":
			System.setProperty("webdriver.edge.driver", System.getProperty("driverPath"));
			driver = new EdgeDriver();
			logger.debug("Initialized Edge Driver");
			break;
		}
		driver.manage().window().maximize();
		logger.debug("Maximized Browser Window");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}
	
	public String captureScreenshot(String methodName, WebDriver driver) throws IOException {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		String dstFilePath = System.getProperty("user.dir")+"\\Reports\\"+methodName+".png";
		FileUtils.copyFile(src, new File(dstFilePath));
		
		return dstFilePath;
	}
}
