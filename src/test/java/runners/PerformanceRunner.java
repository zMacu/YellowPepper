package tests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/performance",
        glue = {"steps_definitions.performance"},
        plugin = {"pretty", "html:target/cucumber-performance-report.html"}
)
public class PerformanceRunner {
}
