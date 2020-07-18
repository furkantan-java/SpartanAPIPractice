package SpartanApp.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\api",
        glue = "SpartanApp\\step_definitions",
        dryRun = true,
        strict = false,
        tags = "",
        plugin = {
                "html:target/CucumberDefaultReport",
                "json:target/apitest.json",
                "rerun:rerun.txt"
        }
) public class APITestRunner {
}
