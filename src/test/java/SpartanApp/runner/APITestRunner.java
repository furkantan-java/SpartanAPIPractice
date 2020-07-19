package SpartanApp.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "SpartanApp/step_definitions",
        dryRun = false,
        strict = false,
        tags = "@add_spartan_with_JSONFile",
        plugin = {
                "json:target/cucumber.json",
                "rerun:rerun.txt"
        }
) public class APITestRunner {
}
