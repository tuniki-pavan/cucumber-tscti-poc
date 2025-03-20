package steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Driver;

public class Hooks {

	WebDriver driver = Driver.getDriver();

	@Before
	public void setUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("Hook before");
	}

	@After
	public void tearDown(Scenario scenario) throws InterruptedException {
		System.out.println("Hook After");
		driver.quit();
	}

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {
		System.out.println("After step hook");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("source path" + sourcePath);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
		scenario.attach(fileContent, "image/png", "screenshot");
	}
}