
package POC.GAC_Billing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DataProcess {

	private By getElementLocator(String keyword, String locatorType, String locatorValue) {

		switch (locatorType) {
		case "className":
			return By.className(locatorValue);
		case "id":
			return By.id(locatorValue);
		case "cssSelector":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	/**
	 * 
	 * @param driver
	 * @param keyword
	 * @param locatorType
	 * @param locatorValue
	 * @param param
	 * @author PragyaDwivedi
	 */
	public void performAction(WebDriver driver, String keyword, String locatorType, String locatorValue, String param) {

		switch (keyword) {

		case "openBrowser":
			driver.get(param);
			break;
		case "click":
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			driver.findElement(By.xpath(locatorValue)).click();
			break;
		case "clickClose":
			try {
				Thread.sleep(21000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			driver.findElement(By.xpath(locatorValue)).click();
			break;
		case "sendKeys":
			driver.findElement(getElementLocator(keyword, locatorType, locatorValue)).sendKeys(param);
			break;
		case "scrollPageDown":
			try {
				scrollPageDown(driver);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case "assert":
			String ActualTitle = "The username/password entered is invalid. Usernames and passwords are case sensitive.";
			String ExpectedTitle = "Ready For Billing";
			Assert.assertEquals(ActualTitle, ExpectedTitle);
			break;
		}

	}

	public void scrollPageDown(WebDriver driver) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");

	}
}
