package qa.utility.reportutility;

import java.io.File;
import java.util.logging.Logger;

public class Report {

	private Logger logger;
	private boolean pass;
	private File screenshot;
	private String testName;
	
	public Report(Logger logger, boolean pass, File screenshot, String testName){
		this.logger = logger;
		this.pass = pass;
		this.screenshot = screenshot;
		this.testName = testName;
	}
	
	public Report(Logger logger, boolean pass, File screenshot){
		this.logger = logger;
		this.pass = pass;
		this.screenshot = screenshot;
	}
	
	public Report(Logger logger, boolean pass, String testName){
		this.logger = logger;
		this.pass = pass;	
		this.testName = testName;
		this.screenshot = new File("todo.png");
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public File getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(File screenshot) {
		this.screenshot = screenshot;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}	
}
