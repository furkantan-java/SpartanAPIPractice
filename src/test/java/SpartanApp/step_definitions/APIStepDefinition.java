package SpartanApp.step_definitions;

import SpartanApp.pojo.Spartan;
import SpartanApp.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class APIStepDefinition {


    private String username;
    private String password;
    private ContentType contentType;
    private Response response;
    private Spartan spartan;


    @Given("authorization credentials are provided for {string}")
    public void authorization_credentials_are_provided_for(String userType) {
        if (userType.equals("admin")) {
            username = ConfigurationReader.getProperty("admin_username");
            password = ConfigurationReader.getProperty("admin_password");
        }
    }

    @Given("user accepts content type {string}")
    public void user_accepts_content_type(String type) {
        if (type.toLowerCase().contains("json")) {
            contentType = ContentType.JSON;
        } else if (type.toLowerCase().contains("xml")) {
            contentType = ContentType.HTML;
        } else if (type.toLowerCase().contains("html")) {
            contentType = ContentType.HTML;
        }
    }

    @When("user sends GET request to {string}")
    public void user_sends_GET_request_to(String endPoint) {
        response = given().
                accept(contentType).
                auth().basic(username, password).
                when().
                get(endPoint).prettyPeek();
    }

    @Then("user verifies that response status code is {int}")
    public void user_verifies_that_response_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @And("user verifies that response has {int} Spartans")
    public void userVerifiesThatResponseHasSpartans(int numberOfSpartan) {
        //response.then().statusCode(200);

        response.then().
                assertThat().
                statusCode(200).
                and().
                header("Content-Type", "application/json;charset=UTF-8").
                //and().
                //contentType(ContentType.JSON).
                        and().
                body("size()", is(100));

    }

    @When("user create Spartan POJO Object with following information")
    public void user_create_Spartan_POJO_Object_with_following_information(List<Map<String, String>> dataTable) {
        String name = dataTable.get(0).get("name");
        String gender = dataTable.get(0).get("gender");
        Integer mobilePhoneNumber = Integer.valueOf(dataTable.get(0).get("phone"));

        spartan = new Spartan(name, gender, mobilePhoneNumber);
    }

    @Then("user sends POST request to {string}")
    public void user_sends_POST_request_to(String string) {
        response = given().
                auth().basic(username, password).
                contentType(contentType).
                accept(contentType).
                body(spartan).
                when().
                post("/api/spartans").prettyPeek();
    }

    @Then("user verifies that Spartan Born")
    public void user_verifies_that_Spartan_Born() {
        response.then().
                assertThat().
                body("success", is("A Spartan is Born!")).
                and().
                body("data.name", is(spartan.getName())).
                body("data.gender", is(spartan.getGender())).
                body("data.phone", is(spartan.getMobilePhoneNumber()));
    }

    @When("user create Spartan POJO Object with List information")
    public void user_create_Spartan_POJO_Object_with_List_information(List<String> dataTable) {
        String name = dataTable.get(0);
        String gender = dataTable.get(1);
        Integer mobilePhoneNumber = Integer.valueOf(dataTable.get(2));

        spartan = new Spartan(name, gender, mobilePhoneNumber);
    }
}
