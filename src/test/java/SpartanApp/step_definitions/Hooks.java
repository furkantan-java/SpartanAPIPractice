package SpartanApp.step_definitions;

import SpartanApp.utilities.ConfigurationReader;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before()
    public void setup() {
        RestAssured.baseURI = ConfigurationReader.getProperty("url");
    }
}
