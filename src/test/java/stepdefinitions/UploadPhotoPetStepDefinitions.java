package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.PetSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class UploadPhotoPetStepDefinitions {
    @Steps(shared = true)
    PetSteps petSteps;
    Response responseCreatePet;

    Response uploadPhotoPet;
    int petId;
    @Given("a pet exists")
    public void a_pet_exists() {
        responseCreatePet = petSteps.createPet();
        petId = responseCreatePet.jsonPath().get("id");
    }
    @When("a POST request is sent to pet uploadImage with the photo file")
    public void a_post_request_is_sent_to_pet_upload_image_with_the_photo_file() {
        uploadPhotoPet =  petSteps.uploadPetImg(String.valueOf(petId));
    }
    @Then("the upload photo response status code should be {int}")
    public void the_upload_photo_response_status_code_should_be(int status) {
        assertThat(uploadPhotoPet.getStatusCode()).isEqualTo(status);
    }
    @Then("the response should indicate that the photo was uploaded successfully")
    public void the_response_should_indicate_that_the_photo_was_uploaded_successfully() {
        String img = uploadPhotoPet.jsonPath().get("photoUrl");
        assertThat(img).endsWith(".tmp");
    }
}
