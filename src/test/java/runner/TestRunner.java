package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json",
		"rerun:target/rerun.txt" }, features = "src/test/resources/features", // folder name -- feature files
		glue = "steps" // package name -- steps definition
)
public class TestRunner {
}