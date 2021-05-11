package POC.GAC_Billing;

import org.testng.annotations.Test;

import POC.ReadData.ReadExcelSheet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Keyword driven frameworkperformAction
 * 
 * @author PragyaDwivedi
 *
 */

public class BillingScript {

	public WebDriver driver;
	// TODO Need to change path from your project folder location
	String billing_excelPath = "C:\\Users\\rupes\\workspace\\GAC-Billing\\src\\test\\java\\POC\\ReadData\\GAC.xlsx";
	String login_excelPath = "C:\\Users\\rupes\\workspace\\GAC-Billing\\src\\test\\java\\POC\\ReadData\\Login.xlsx";

	/*
	 * Open Browser
	 */
	public void openBrowser() {
		// TODO Need to change path from your project folder location
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\rupes\\workspace\\GAC-Billing\\src\\test\\java\\POC\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	}

	/*
	 * Read data from excel
	 */
	public void ReadData(String path) throws InvalidFormatException, IOException {
		DataProcess proce;
		String[][] data = getData(path);
		for (int i = 0; i < data.length; i++) {
			String key = data[i][0];
			String locType = data[i][1];
			String locValue = data[i][2];
			String param = data[i][3];
			proce = new DataProcess();
			proce.performAction(driver, key, locType, locValue, param);
		}
	}

	// Test script for billing for positive scenario
	@Test
	public void billing() throws InvalidFormatException, IOException {
		openBrowser();
		ReadData(billing_excelPath);
	}

	// Test script for login for negative scenario
	@Test
	public void Login() throws InvalidFormatException, IOException {
		openBrowser();
		ReadData(login_excelPath);
	}

	/*
	 * Get data from excel
	 */
	public String[][] getData(String excelPath) throws InvalidFormatException, IOException {

		ReadExcelSheet excelObj = new ReadExcelSheet(excelPath);
		int rowCount = excelObj.getRowCount(0);
		int colCount = excelObj.getColCount(0);
		String[][] data = new String[rowCount + 1][colCount];
		for (int i = 0; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i][j] = excelObj.getCellData(0, i, j);
			}

		}

		return data;

	}

}
