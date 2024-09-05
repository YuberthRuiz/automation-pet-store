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

public class LogOutStepDefinitions {
    @Steps(shared = true)
    UserSteps userSteps;

    Response responseLoginUser;
    Response responseLogoutUser;
    @Given("a user is logged in with {string}")
    public void a_user_is_logged_in(String type) {
        User user = UserCreator.getUserByType(type);
        responseLoginUser = userSteps.loginUser(user.getUsername(),user.getPassword());
    }
    @When("a GET request is sent to logout endpoint")
    public void a_get_request_is_sent_to_logout_endpoint() {
        responseLogoutUser = userSteps.logoutUser();
    }
    @Then("the logout response status code should be {int}")
    public void the_logout_response_status_code_should_be(int status) {
        assertThat(responseLogoutUser.getStatusCode()).isEqualTo(status);
    }

}
