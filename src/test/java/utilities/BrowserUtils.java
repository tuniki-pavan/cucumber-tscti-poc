package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

//	* This method will accept webelement of dropdown and String value of dropdown.
//	* And then it will select by provided value in dropdown. Ex:
//	* - selectDropdownByValue (element, "1") ;
	public static void selectDropdownByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

//	/**
//	* This method will wait for element to be clickable. Exi
//	-waitElementIoBeclickable (element, 10); -> returns element:
//	* /
	public static WebElement waitElementToBeClickable(WebElement element, Duration seconds) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
		WebElement elementl = wait.until(ExpectedConditions.elementToBeClickable(element));
		return elementl;
	}

//	/**
//	*
//	* /
//	* This method will wait for element to be visible. Ex:
//	-waitElementToBevisible (element, 10); -> returns element
	public static WebElement waitElementToBeVisible(WebElement element, Duration seconds) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
		WebElement elementl = wait.until(ExpectedConditions.visibilityOf(element));
		return elementl;
	}

//	* This method will help to switch to another window.
//	* /
	public static void switchWindow() {
		WebDriver driver = Driver.getDriver();
		String currentWindowID = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!currentWindowID.equals(window)) {
				driver.switchTo().window(window);
			}
		}
	}

	// scroll
	public static void scroll(int pixels) {
		WebDriver driver = Driver.getDriver();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window-scrollBy(0," + pixels + ")");
	}

	// Screen shot
	public static void takeScreenshot(String testName) throws IOException {
		WebDriver driver = Driver.getDriver();
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "src/test/resources/screenshots/" + testName + ".png";
		File file = new File(path);
		FileUtils.copyFile(screenshot, file);
	}

	public static void clickwithRetry(WebElement element, WebDriver driver) throws InterruptedException {
		int retries = 5;
		int attempts = 0;
		while (attempts < retries) {
			try {
				element.click();
				break;
			} catch (Exception e) {
				attempts++;
				driver.navigate().refresh();
				Thread.sleep(3000);
			}
		}
		if (attempts == retries) {
			throw new RuntimeException("Failed to click element after " + retries + " attempts");
		}
	}
}
