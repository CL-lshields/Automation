package qa.utility.reportutility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import qa.SeleniumTest;

public class ReportList {

	private static ArrayList<Report> reportList;
	
	public static String reportName;
	
	public static String reportPath;
	
	public static String reportResources;
	
	public static String testInstance;

	private String name;

	public ReportList() {
		ReportList.reportList = new ArrayList<Report>();
		
	}

	public static ArrayList<Report> getReportList() {
		return reportList;
	}

	public static void setReportList(ArrayList<Report> reportList) {
		ReportList.reportList = reportList;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Report getReport(int i) {
		return reportList.get(i);
	}

	public static void setReport(int i, Report report) {
		reportList.set(i, report);
	}

	public static void addReport(Report report) {
		reportList.add(report);
	}
	
	/**
	 * This method should be called after a test method, best practice would most likely be in the 
	 * TearDown method of Junit. This test checks if the previous test was successful or not. If the 
	 * test was not successful, a screenshot is captured and attached to the corresponding report in
	 * the reportList.
	 */
	public static boolean generateScreenshotOnFailure(){
		//attach screenshots to failed tests
				if(!ReportList.getReportList().isEmpty()){
					if(!ReportList.getReport(ReportList.getReportList().size()-1).isPass()){
						ReportList.getReport(ReportList.getReportList().size()-1)
							.setScreenshot(((TakesScreenshot)SeleniumTest.driver).getScreenshotAs(OutputType.FILE));
						return true;
					}
					return false;
				}
				return false;
	}

	public static void generateScreenshotFolder(ArrayList<Report> reportList, String name) throws IOException {
		for (int i = 0; i < reportList.size(); i++) {
			if (reportList.get(i).getScreenshot().exists()) {
				FileUtils.copyFile(reportList.get(i).getScreenshot(), new File("D3"+ReportList.reportPath+"screenshots/"+name+ i));
			}
		}
	}

	public static void generateReport(String name) {
		try {
			ReportHtml report = new ReportHtml(name);
			generateScreenshotFolder(getReportList(),ReportList.reportResources);
			report.generateHtml(getReportList(),ReportList.reportResources);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	public static void generateGraphReport(String name, ArrayList<int[]> result) {
		try {
			GraphReport report = new GraphReport(name, result);			
			report.generateHtml(getReportList(),ReportList.reportResources);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}
