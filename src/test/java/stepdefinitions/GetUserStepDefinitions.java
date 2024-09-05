package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.UserSteps;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static util.RandomUser.randomUser;

public class GetUserStepDefinitions {
    @Steps(shared = true)
    UserSteps userSteps;

    Response responseGetUser;
    Response responseCreateUser;

    String userName;
    @Given("a user exists with username")
    public void a_user_exists_with_username() {
        responseCreateUser = userSteps.createUser();
        userName = responseCreateUser.jsonPath().getString("username");
    }
    @When("a GET request is sent to user endpoint")
    public void a_get_request_is_sent_to_user_endpoint() {
        responseGetUser = userSteps.getByUser(userName);
    }
    @Then("the user get response status code should be {int}")
    public void the_user_get_response_status_code_should_be(int status) {
        assertThat(responseGetUser.getStatusCode()).isEqualTo(status);
    }
    @Then("the response should contain the correct user details")
    public void the_response_should_contain_the_correct_user_details() {
        String strCreateUser = String.valueOf(responseCreateUser.prettyPrint());
        String strGetUser = String.valueOf(responseGetUser.prettyPrint());
        assertThatJson(strGetUser).isEqualTo(strCreateUser);
    }

    @Given("no user exists with username")
    public void no_user_exists_with_username() {
       String randomUser = randomUser();
       responseGetUser = userSteps.getByUser(randomUser);
    }

}
