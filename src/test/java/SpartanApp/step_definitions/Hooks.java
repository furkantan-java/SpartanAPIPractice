package SpartanApp.step_definitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before()
    public void setup() {
        RestAssured.baseURI = "http://54.84.47.121:8000";
    }
}
