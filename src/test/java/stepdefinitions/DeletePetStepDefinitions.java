package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.PetSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class DeletePetStepDefinitions {
    @Steps(shared = true)
    PetSteps petSteps;

    Response responsePetDelete;
    Response responseSearchPet;
    @When("a DELETE request is sent to pet ID {int} endpoint")
    public void a_delete_request_is_sent_to_pet_endpoint(int petId) {
        responsePetDelete = petSteps.deletePet(petId);
    }
    @Then("the delete response status code should be {int}")
    public void the_delete_response_status_code_should_be(int status) {
        assertThat(responsePetDelete.getStatusCode()).isEqualTo(status);
    }
    @Then("a subsequent GET request to pet with ID {int} should return a {int} status code")
    public void a_subsequent_get_request_to_pet_with_id_should_return_a_status_code(int petId, int status) {
        responseSearchPet = petSteps.searchPetById(petId);
        assertThat(responseSearchPet.getStatusCode()).isEqualTo(status);
    }

}
