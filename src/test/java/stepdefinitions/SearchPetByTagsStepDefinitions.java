package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.description.Description;
import steps.PetSteps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static util.Constants.*;

public class SearchPetByTagsStepDefinitions {

    @Steps(shared = true)
    PetSteps petSteps;
    Response responseTagsPet;
    int statusCod;
    List<List<Map<String, Object>>> tagsList = new ArrayList<>();

    @Given("pets exist with different tags")
    public void pets_exist_with_different_tags() {
        File petSold = new File(createPetSold);
        petSteps.createPet(petSold);
    }
    @When("a GET request is sent to pet findByTags with {string}")
    public void a_get_request_is_sent_to_pet_find_by_tag_endpoint(String tag) {
        responseTagsPet = petSteps.getPetByTag(tag);
        tagsList = responseTagsPet.jsonPath().getList("tags");
    }
    @Then("the tags search status code should be {int}")
    public void the_search_response_tag_code_should_be(int statusCode) {
        assertThat(responseTagsPet.getStatusCode()).isEqualTo(statusCode);
    }
    @Then("all returned pets should have the tags {string}")
    public void all_returned_pets_should_have_the_tag(String tag) {
        Assertions.assertThat(tagsList)
                .isNotEmpty()
                .allSatisfy(tags ->
                        Assertions.assertThat(tags)
                                .extracting("name")
                                .contains(tag)
                );
    }
}
