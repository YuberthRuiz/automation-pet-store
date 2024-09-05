package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.json.JSONException;
import steps.PetSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePetsStepDefinitions {
    @Steps(shared = true)
    PetSteps petSteps;
    Response responseCreatePet;
    int status;
    Response responseSearchById;

    @Given("a pet with valid details")
    public void a_pet_with_valid_details() {

    }
    @When("a POST request is sent to pet endpoint")
    public void a_post_request_is_sent_to_pet() {
        responseCreatePet = petSteps.createPet();
    }
    @Then("the response status code should be successful")
    public void the_response_status_code_should_be_or() {
        status = responseCreatePet.getStatusCode();
        assertThat(status).isEqualTo(200);
    }
    @Then("the pet should be retrievable using a GET request with the petId")
    public void the_pet_should_be_retrievable_using_a_get_request_to_pet() {
        int idRequest = responseCreatePet.jsonPath().get("id");
        responseSearchById = petSteps.searchPetById(idRequest);
        int idSearch = responseSearchById.jsonPath().get("id");
        assertThat(idRequest).isEqualTo(idSearch);
    }

    @When("a POST request is sent to pet endpoint without {string}")
    public void a_post_request_is_sent_to_pet_endpoint_without(String missingAtribute) throws JSONException {
        responseCreatePet = petSteps.createPetMissingAttribute(missingAtribute);
    }
    @Then("the create status code should be {int}")
    public void the_response_status_code_should_be(int status) {
        assertThat(responseCreatePet.getStatusCode()).isEqualTo(status);
    }


}
