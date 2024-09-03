package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import steps.PetSteps;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;


public class PetByIdStepDefinitions {
    @Steps(shared = true)
    PetSteps petSteps;
    Response responseCreatePet;
    Response responseSearchById;
    @Given("a pet exists with ID")
    public void a_pet_exists_with_id() {
        responseCreatePet = petSteps.createPet();
    }
    @When("a GET request is sent to pet endpoint")
    public void a_get_request_is_sent_to_pet() {
        int petId = responseCreatePet.jsonPath().get("id");
        responseSearchById = petSteps.searchPetById(petId);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int status) {
        assertThat(responseSearchById.getStatusCode()).isEqualTo(status);
    }
    @Then("the response should contain the correct pet details")
    public void the_response_should_contain_the_correct_pet_details() throws IOException, JSONException {
        String strCreatePet = String.valueOf(responseCreatePet.prettyPrint());
        String strGetPet = String.valueOf(responseSearchById.prettyPrint());
        assertThatJson(strCreatePet).isEqualTo(strGetPet);
    }
}
