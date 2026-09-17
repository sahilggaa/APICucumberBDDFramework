package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/AddPlace.feature", glue={ "stepDefinition", "resources"},plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/jsonReports/cucumber.json"
})
public class TestRunner {

//	tags= "@DeletePlace"
}
