package SpartanApp.step_definitions;

import SpartanApp.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class APIStepDefinition {


    private String username;
    private String password;
    private ContentType contentType;
    private Response response;

    @Given("autherization credentials are provided for {string}")
    public void autherization_credentials_are_provided_for(String userType) {
       if (userType.equals("admin")){
           username = ConfigurationReader.getProperty("admin_username");
           password = ConfigurationReader.getProperty("admin_password");
       }
    }

    @Given("user accepts content type {string}")
    public void user_accepts_content_type(String type) {
        if(type.toLowerCase().contains("json")){
            contentType = ContentType.JSON;
        } else if(type.toLowerCase().contains("xml")){
            contentType = ContentType.HTML;
        } else if(type.toLowerCase().contains("html")){
            contentType = ContentType.HTML;
        }
    }

    @When("user sends GET request to {string}")
    public void user_sends_GET_request_to(String endPoint) {
        response = given().
                        accept(contentType).
                        auth().basic(username,password).
                    when().
                        get(endPoint).prettyPeek();
    }

    @Then("user verifies that response status code is {int}")
    public void user_verifies_that_response_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());
    }

}
