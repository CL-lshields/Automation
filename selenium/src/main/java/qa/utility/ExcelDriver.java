package qa.utility;

/**
* @author Lance Shields <lshields@bizjournals.com>
*
*/

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	// instance variables
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	HashMap<String, String> dataMap = new HashMap<String, String>();
	ArrayList<HashMap<String, String>> dataArray = new ArrayList<HashMap<String, String>>();
	private static final String eventConfigFilename = System.getProperty("user.dir")
			+ "/selenium/src/main/java/qa/events/resources/events_automation_data.xlsx";
	
	private static final String eventCloudConfigFilename = System.getProperty("user.dir")
			+ "/selenium/src/main/java/qa/events/resources/cloud_events_automation_data.xlsx";

	private static final String commerceConfigFilename = System.getProperty("user.dir")
			+ "/selenium/src/main/java/qa/commerce/resources/commerce_automation_data.xlsx";
	
	private static final String bizIntelligenceConfigFilename = System.getProperty("user.dir")
			+ "/selenium/src/main/java/qa/bizintelligence/resources/bizintelligence_automation_data.xlsx";
	
	private static final String magentoConfigFilename = System.getProperty("user.dir")
			+ "/selenium/src/main/java/qa/magento/resources/magento_automation_data.xlsx";
	
	private static final String servicesConfigFilename = System.getProperty("user.dir")
			+ "/selenium/src/main/java/qa/services/resources/services_automation_data.xlsx";
	
	private static final String ec2ConfigFilename = System.getProperty("user.dir")
			+ "/selenium/src/main/java/qa/utility/aws/resources/EC2_config_data.xlsx";
	
	/* Add resource location for the data files of future teams here
	 * using the example below, modeled after the event config file.
	 */
	//private static final String dataConfigFilename = *add the string path to your test resource here*
	
	// constructor
	public ExcelDriver() {

	}

	// Getter and Setter methods
	public HashMap<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(HashMap<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public ArrayList<HashMap<String, String>> getDataArray() {
		return dataArray;
	}

	public void setDataArray(ArrayList<HashMap<String, String>> dataArray) {
		this.dataArray = dataArray;
	}

	public static String getConfigfilename() {
		return eventConfigFilename;
	}
	
	public static String getCloudConfigfilename() {
		return eventCloudConfigFilename;
	}

	public static String getCommercefilename() {
		return commerceConfigFilename;
	}
	
	public static String getBizIntelligenceFilename() {
		return bizIntelligenceConfigFilename;		
	}
	
	public static String getMagentoFilename() {
		return magentoConfigFilename;		
	}
	
	public static String getServicesFilename() {
		return servicesConfigFilename;		
	}
	
	public static String getEc2Filename() {
		return ec2ConfigFilename;		
	}

	public String getDataSheetValue(String value){
		return getDataArray().get(0).get(value);
	}

	// Read in a workbook
	public void readInWorkbook(File file) {
		try {
			FileInputStream input = new FileInputStream(file);
			this.workbook = new XSSFWorkbook(input);
			this.sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row headerRow = null;
			loop: while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// keep track of the header row
				if (row.getRowNum() == 0) {
					headerRow = row;
				}

				// obtain first column in each row
				Cell cell = row.getCell(0);

				// handle cell based on cell type
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:

					// if cell is not empty and not the first row,
					// commit the row to dataArray
					if (!(cell.getStringCellValue().isEmpty() && row.getRowNum() == 0)) {
						
						//local datamap
						HashMap<String, String> dataMap = new HashMap<String, String>();

						// For this row, iterate through all the columns
						Iterator<Cell> cellIterator = row.cellIterator();

						// only iterate to the last cell in the column
						for (int i = 0; i < row.getLastCellNum(); i++) {
							cell = cellIterator.next();
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								dataMap.put(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
										cell.getNumericCellValue() + "");
								System.out.print(cell.getNumericCellValue() + " "
										+ headerRow.getCell(cell.getColumnIndex()).getStringCellValue());
								break;
							case Cell.CELL_TYPE_STRING:
								dataMap.put(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
										cell.getStringCellValue());
								System.out.print(cell.getStringCellValue() + " "
										+ headerRow.getCell(cell.getColumnIndex()).getStringCellValue());
								break;
							case Cell.CELL_TYPE_BLANK:
								System.out.print("null ");
								break;
							}
							//HashMap<String, String> tempDataMap = dataMap;
							//dataArray.add(tempDataMap);
							//break;
						}
						HashMap<String, String> tempDataMap = dataMap;
						dataArray.add(tempDataMap);						
						break;						
					}
					break;
				default:
					System.out.println("row does not exist for " + row.getRowNum());
					break loop;
				}
				System.out.println("");
			}
			input.close();
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			qa.SeleniumTest.logger.info(ex.getMessage()+System.lineSeparator());
		}

	}

	// @Overload - ReadInWorkbook Function
	public void readInWorkbook(File file, String sheetName) {
		try {
			FileInputStream input = new FileInputStream(file);
			this.workbook = new XSSFWorkbook(input);
			this.sheet = workbook.getSheet(sheetName);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row headerRow = null;
			loop: while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// keep track of the header row
				if (row.getRowNum() == 0) {
					headerRow = row;
				}

				// obtain first column in each row
				Cell cell = row.getCell(0);

				// handle cell based on cell type
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:

					// if cell is not empty and not the first row,
					// commit the row to dataArray
					if (!(cell.getStringCellValue().isEmpty() && row.getRowNum() == 0)) {

						// For this row, iterate through all the columns
						Iterator<Cell> cellIterator = row.cellIterator();

						// only iterate to the last cell in the column
						for (int i = 0; i < row.getLastCellNum(); i++) {
							cell = cellIterator.next();
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								dataMap.put(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
										cell.getNumericCellValue() + "");
								System.out.print(cell.getNumericCellValue() + " "
										+ headerRow.getCell(cell.getColumnIndex()).getStringCellValue());
								break;
							case Cell.CELL_TYPE_STRING:
								dataMap.put(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
										cell.getStringCellValue());
								System.out.print(cell.getStringCellValue() + " "
										+ headerRow.getCell(cell.getColumnIndex()).getStringCellValue());
								break;
							case Cell.CELL_TYPE_BLANK:
								System.out.print("null ");
								break;
							}
							//HashMap<String, String> tempDataMap = dataMap;
							//dataArray.add(tempDataMap);
							//break;
						}
						HashMap<String, String> tempDataMap = dataMap;
						dataArray.add(tempDataMap);						
						break;	
					}
					break;
				default:
					System.out.println("row does not exist for " + row.getRowNum());
					break loop;
				}
				System.out.println("");
			}
			input.close();
		} catch (Exception ex) {

		}

	}

	// Read in a workbook Line
	public void readInWorkbookLine(File file, String testname) {
		try {
			FileInputStream input = new FileInputStream(file);
			this.workbook = new XSSFWorkbook(input);
			this.sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row headerRow = null;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// keep track of the header row
				if (row.getRowNum() == 0) {
					headerRow = row;
				}

				// obtain first column in each row
				Cell cell = row.getCell(0);

				// handle cell based on cell type
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:

					// if cell is a string and matches test name, commit the row
					// to datalist
					if (cell.getStringCellValue().equalsIgnoreCase(testname)) {

						// For this row, iterate through all the columns
						Iterator<Cell> cellIterator = row.cellIterator();

						// only iterate to the last cell in the column
						for (int i = 0; i < row.getLastCellNum(); i++) {
							cell = cellIterator.next();
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								dataMap.put(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
										cell.getNumericCellValue() + "");
								System.out.print(cell.getNumericCellValue() + " "
										+ headerRow.getCell(cell.getColumnIndex()).getStringCellValue());
								break;
							case Cell.CELL_TYPE_STRING:
								dataMap.put(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
										cell.getStringCellValue());
								System.out.print(cell.getStringCellValue() + " "
										+ headerRow.getCell(cell.getColumnIndex()).getStringCellValue());
								break;
							case Cell.CELL_TYPE_BLANK:
								System.out.print("null ");
								break;
							}
						}
					}
					break;
				default:
					System.out.println("test name does not match for row " + row.getRowNum());
					break;
				}
				System.out.println("");
			}
			input.close();
		} catch (Exception ex) {

		}
	}

	// appends http:// prefix to website names
	public String toURL(String website) {
		String URL = "http://" + website + "/";
		return URL;
	}

}