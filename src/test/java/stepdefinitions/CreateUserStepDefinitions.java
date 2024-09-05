package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.UserSteps;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserStepDefinitions {
    @Steps(shared = true)
    UserSteps userSteps;

    Response responseCreateUser;
    Response responseGetUser;

    String userName;
    @Given("valid user details")
    public void valid_user_details() {

    }
    @When("a POST request is sent to user")
    public void a_post_request_is_sent_to_user() {
        responseCreateUser = userSteps.createUser();
        userName = responseCreateUser.jsonPath().getString("username");
    }
    @Then("the create user response status code should be {int}")
    public void the_create_user_response_status_code_should_be(int status) {
       assertThat(responseCreateUser.getStatusCode()).isEqualTo(status);
    }
    @Then("the user should be retrievable using a GET request")
    public void the_user_should_be_retrievable_using_a_get_request_to_user() {
        responseGetUser = userSteps.getByUser(userName);
        String strCreateUser = String.valueOf(responseCreateUser.prettyPrint());
        String strGetUser = String.valueOf(responseGetUser.prettyPrint());
        assertThatJson(strGetUser).isEqualTo(strCreateUser);
    }

}
