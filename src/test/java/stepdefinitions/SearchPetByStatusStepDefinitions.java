package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.PetSteps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static util.Constants.*;

public class SearchPetByStatusStepDefinitions {

    @Steps(shared = true)
    PetSteps petSteps;
    Response responseStatusPet;
    int statusCod;
    List statusesList = new ArrayList();

    @Given("pets exist with different statuses")
    public void pets_exist_with_different_statuses() {
        File petSold = new File(createPetSold);
        petSteps.createPet(petSold);
        File petPending = new File(createPetPending);
        petSteps.createPet(petPending);
        File petAvailable = new File(createPetFile);
        petSteps.createPet(petAvailable);
    }
    @When("a GET request is sent to pet findByStatus with {string}")
    public void a_get_request_is_sent_to_pet_find_by_status_endpoint(String status) {
        responseStatusPet = petSteps.getPetByStatus(status);
        statusesList = responseStatusPet.jsonPath().getList("status");
    }
    @Then("the search status code should be {int}")
    public void the_search_response_status_code_should_be(int statusCode) {
        assertThat(responseStatusPet.getStatusCode()).isEqualTo(statusCode);
    }
    @Then("all returned pets should have the status {string}")
    public void all_returned_pets_should_have_the_status(String status) {
        assertThat(asList(statusesList))
                .allSatisfy(i ->
                        assertThat(i.get(0)).isEqualTo(status));
    }
}
