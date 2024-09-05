package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.apache.commons.io.FileUtils;
import steps.PetSteps;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static util.Constants.updatePetFile;

public class UpdatePetStepDefinitions {
    @Steps(shared = true)
    PetSteps petSteps;

    Response responsePetUpdate;
    @When("a PUT request is sent to pet endpoint")
    public void a_put_request_is_sent_to_pet_endpoint() {
        responsePetUpdate = petSteps.updatePet();
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int status) {
        assertThat(responsePetUpdate.getStatusCode()).isEqualTo(status);
    }
    @Then("a GET request to pet endpoint should return the updated details")
    public void a_get_request_to_pet_endpoint_should_return_the_updated_details() throws IOException {
        String strUpdatePet = String.valueOf(responsePetUpdate.prettyPrint());
        String jsonUpdatePet = FileUtils.readFileToString(new File(updatePetFile), StandardCharsets.UTF_8);
        assertThatJson(strUpdatePet).isEqualTo(jsonUpdatePet);
    }
}
