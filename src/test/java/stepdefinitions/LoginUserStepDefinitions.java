package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.User;
import net.serenitybdd.annotations.Steps;
import steps.UserSteps;
import util.UserCreator;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUserStepDefinitions {
    @Steps(shared = true)
    UserSteps userSteps;

    Response responseLoginUser;
    @Given("a user exists with username and password")
    public void a_user_exists_with_username_and_password() {

    }
    @When("a GET request is sent to user login with {string}")
    public void a_get_request_is_sent_to_user_login_with_valid_credentials(String type) {
        User user = UserCreator.getUserByType(type);
        responseLoginUser = userSteps.loginUser(user.getUsername(),user.getPassword());
        System.out.println("status " + responseLoginUser.getStatusCode());
    }
    @Then("the login response status code should be {int}")
    public void the_login_response_status_code_should_be(int status) {
        assertThat(responseLoginUser.getStatusCode()).isEqualTo(status);
    }
    @Then("a session token should be returned")
    public void a_session_token_should_be_returned() {
        String token = String.valueOf(responseLoginUser.getBody());
        assertThat(token).isNotNull();
    }

    @Given("no user exists with the provided credentials")
    public void no_user_exists_with_the_provided_credentials() {

    }
}
