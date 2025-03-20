package steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.HomePage;
import utilities.Configuration;
import utilities.Driver;
import utilities.BrowserUtils;

public class HomeSteps {
	WebDriver driver = Driver.getDriver();
	HomePage home = new HomePage();

	@Given("I open the TSCTI homepage")
	public void openTSCTIHomepage() {
		driver.get(Configuration.getBaseUrl());
	}

	@Then("I should see the Our Services text")
	public void verifyTSCTIVisibleText() throws IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", home.ourServices);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Our Services']")));
		if (element != null && element.isDisplayed()) {
			System.out.println("The text 'Our Services' is visible on the page.");
		} else {
			System.out.println("The text 'Our Services' is NOT visible on the page.");
		}

	}
}
