package mvn.pck.practice5.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	static ExtentReports er ;
	static ExtentSparkReporter esr;
	
	public static ExtentReports getExtentReporter() {
		String path = System.getProperty("user.dir")+"\\Reports\\Report.html";
		esr = new ExtentSparkReporter(path);
		esr.config().setDocumentTitle("Test Execution Extent Report");
		esr.config().setReportName("QA Click Academy Report");
		esr.config().setTheme(Theme.DARK);
		
		er = new ExtentReports();
		er.attachReporter(esr);
		
		return er;
	}
	
}
