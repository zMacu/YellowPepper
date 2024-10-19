package tests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/automation",
        glue = {"steps_definitions.automation"},
        plugin = {"pretty", "html:target/cucumber-automation-report.html"}
)
public class AutomationRunner {
}
