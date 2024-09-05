package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.UserSteps;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static util.Constants.createUserForDelete;

public class DeleteUserStepDefinitions {
    @Steps(shared = true)
    UserSteps userSteps;

    Response responseDeleteUser;
    Response responseCreateUser;
    Response responseGetUser;

    String userName;
    @Given("a new user is created")
    public void a_new_user_is_created() {
        File userForDel = new File(createUserForDelete);
        responseCreateUser = userSteps.createUser();
        userName = responseCreateUser.jsonPath().getString("username");
    }
    @When("a DELETE request is sent to user endpoint")
    public void a_delete_request_is_sent_to_user_endpoint() {
        responseDeleteUser = userSteps.deleteUser(userName);
    }
    @Then("the delete user response status code should be {int}")
    public void the_delete_user_response_status_code_should_be(int status) {
        assertThat(responseDeleteUser.getStatusCode()).isEqualTo(status);
    }
    @Then("a subsequent GET request to user should return a {int} status code")
    public void a_subsequent_get_request_to_user_should_return_a_status_code(int status) {
        responseGetUser = userSteps.getByUser(userName);
        assertThat(responseGetUser.getStatusCode()).isEqualTo(status);
    }
}
