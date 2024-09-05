package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.apache.commons.io.FileUtils;
import steps.UserSteps;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static util.Constants.*;

public class updateUserStepDefinitions {
    @Steps(shared = true)
    UserSteps userSteps;

    Response responseUpdateUser;
    Response responseCreateUser;
    Response responseGetUser;

    String userName;

    @Given("a user is created")
    public void a_user_is_created() {
        File createUserUpdate = new File(createUserForUpdate);
        responseCreateUser = userSteps.createUser(createUserUpdate);
        userName = responseCreateUser.jsonPath().getString("username");
    }
    @When("a PUT request is sent to user endpoint")
    public void a_put_request_is_sent_to_user_endpoint() {
        responseUpdateUser = userSteps.updateUser(userName);
        userName = responseUpdateUser.jsonPath().getString("username");
    }
    @Then("the update user response status code should be {int}")
    public void the_update_user_response_status_code_should_be(int status) {
        assertThat(responseUpdateUser.getStatusCode()).isEqualTo(status);
    }
    @Then("a GET request to user endpoint should return the updated details")
    public void a_get_request_to_user_endpoint_should_return_the_updated_details() throws IOException {
        responseGetUser = userSteps.getByUser(userName);
        String strGetUser = String.valueOf(responseGetUser.prettyPrint());
        String jsonUpdateUser= FileUtils.readFileToString(new File(updateUser), StandardCharsets.UTF_8);
        assertThatJson(strGetUser).isEqualTo(jsonUpdateUser);
    }
}
