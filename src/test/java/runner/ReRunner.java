package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "@target/rerun.txt", // Rerun the failed scenarios
		glue = "steps", plugin = { "pretty", "html:target/cucumber-reports/cucumber-rerun.html",
				"json:target/cucumber-reports/cucumber-rerun.json" })
public class ReRunner {
	// This will rerun the failed tests
}
