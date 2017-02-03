package qa.utility.reportutility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import qa.SeleniumTest;
import qa.utility.FileTool;
import qa.utility.aws.WebServicesFactory;

public class ReportHtml {

	public  WebServicesFactory awsServices = new WebServicesFactory(new DefaultAWSCredentialsProviderChain());
	private File reportTemplate = new File(System.getProperty("user.dir") + "/D3/index.html");
	private File report;
	
	// Name of the generated report
		public ReportHtml(String fileName) {
			report = new File(System.getProperty("user.dir") + "/D3/" + fileName + ".html");

		}

	// read in file from given path
	protected String readFile(String path, String logStart, String logEnd) {
		
		// variables for log line color
		Boolean write = false;		
		String newLine = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				// process the line.
				if (line.contains(logStart)) {
					write = true;					
				}
				if (line.contains(logEnd)) {
					write = false;					
				}
				if(write.booleanValue()){
					if(line.contains("SEVERE")){
						newLine += "<li class=\"list-group-item list-group-item-danger\">" + line + "</li>";
					}else{
						newLine += "<li class=\"list-group-item list-group-item-success\">" + line + "</li>";
					}
					
				}				
			}
		} catch (Exception ex) {
			qa.SeleniumTest.logger.severe(ex.getMessage() + System.lineSeparator());
		}
		return newLine;
	}

	protected String generateTabs(ArrayList<Report> reportList) {
		String temp = "";
		
		//Generate market tabs
		for (int i = 0; i < reportList.size(); i++) {
			String pass = "<a href=\"#" + reportList.get(i).getTestName()
					+ "\" class=\"list-group-item list-group-item-success\">" + reportList.get(i).getTestName()
					+ "</a>";
			String fail = "<a href=\"#" + reportList.get(i).getTestName()
					+ "\" class=\"list-group-item list-group-item-danger\">" + reportList.get(i).getTestName() + "</a>";

			if (reportList.get(i).isPass()) {
				temp += pass;
			} else {
				temp += fail;
			}
		}
		return temp;
	}

	protected String generateTabContent(ArrayList<Report> reportList, String name) {
		String temp = "";
		
		//Generate screenshots and attach corresponding log reports
		for (int i = 0; i < reportList.size(); i++) {
			if (reportList.get(i).isPass()) {
				temp += "<div class=\"row\">" + "<div id=\"" + reportList.get(i).getTestName()
						+ "\" class=\"panel panel-success\">" + "<div class=\"panel-heading\">"
						+ "<h3 class=\"panel-title\">" + reportList.get(i).getTestName() + "</h3>"
						+ "</div><!-- /.panel-heading -->" + "<div class=\"panel-body\">"
						+ "<img class=\"img-thumbnail img-responsive\" src=\""
						+ "./screenshots/" + name + i
						+ "\" alt=\"" + reportList.get(i).getTestName() + "\">" + "</div><!-- /.panel-body -->" +

				"<ul class=\"list-group\">" + readFile(SeleniumTest.staticLogFile, 
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+1),
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+2))
						+ "</ul><!-- /.list-group -->" + "</div><!-- /.panel -->" + "</div><!-- /.row -->";
			} else {
				temp += "<div class=\"row\">" + "<div id=\"" + reportList.get(i).getTestName()
						+ "\" class=\"panel panel-danger\">" + "<div class=\"panel-heading\">"
						+ "<h3 class=\"panel-title\">" + reportList.get(i).getTestName() + "</h3>"
						+ "</div><!-- /.panel-heading -->" + "<div class=\"panel-body\">"
						+ "<img class=\"img-thumbnail img-responsive\" src=\""
						+ "./screenshots/" + name + i
						+ "\" alt=\"" + reportList.get(i).getTestName() + "\">" + "</div><!-- /.panel-body -->" +

				"<ul class=\"list-group\">" + "<ul class=\"list-group\">" + readFile(SeleniumTest.staticLogFile,						 
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+1),
						ReportList.testInstance.substring(0,ReportList.testInstance.length()-2)+(i+2))
						+ "</ul><!-- /.list-group -->" + "</div><!-- /.panel -->" + "</div><!-- /.row -->";
			}
		}
		return temp;
	}	

	// generate data to replace placeholders in the report template
	public void generateHtml(ArrayList<Report> reportList, String name) throws FileNotFoundException, IOException {
		int passed = 0;
		int failed = 0;
		String content = IOUtils.toString(new FileInputStream(reportTemplate));

		// tests run
		content = content.replaceAll("_TESTS RUN_", Integer.toString(reportList.size()));

		// tests passed
		for (int i = 0; i < reportList.size(); i++) {
			if (reportList.get(i).isPass()) {
				passed++;
			} else {
				failed++;
			}
		}
		content = content.replaceAll("_TESTS PASSED_", Integer.toString(passed));

		content = content.replaceAll("_TESTS FAILED_", Integer.toString(failed));

		// sum of run and passed
		content = content.replaceAll("_TESTS RUN&PASSED_", Integer.toString(passed + reportList.size()));

		// add logger to report
		//content = content.replace("_LOG_", readFile(SeleniumTest.staticLogFile, Charset.defaultCharset()));

		// add screenshots to report
		content = content.replaceAll("_TABCONTENT_", generateTabs(reportList));

		// add screenshots to report
		content = content.replace("_SCREENSHOTS_", generateTabContent(reportList, name));

		// create file if file does not exist		
		if (!report.exists()) {
			report.getParentFile().mkdirs();
			report.createNewFile();
		}

		// write new report html
		IOUtils.write(content, new FileOutputStream(report, false));
		
		//send report to amazon s3		
		//awsServices.amazonS3Utility().uploadDirectoryToS3(new File(System.getProperty("user.dir") + "/D3/"));	    
		
		//archive old files
		awsServices.amazonS3Utility().ArchiveFilesFromS3();		
	}

}
