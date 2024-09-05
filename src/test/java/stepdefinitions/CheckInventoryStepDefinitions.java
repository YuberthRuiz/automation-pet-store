package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.StoreSteps;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static util.Constants.*;

public class CheckInventoryStepDefinitions {
    @Steps(shared = true)
    StoreSteps storeSteps;

    int approved;
    int placed;
    int delivered;
    Response responseInventoryOrder;
    Response responsePlaceOrderApproved;

    @Given("multiple pets exist in different statuses")
    public void multiple_pets_exist_in_different_statuses() {
        responseInventoryOrder = storeSteps.inventoryByStatus();
        approved = responseInventoryOrder.jsonPath().getInt("approved");
        placed = responseInventoryOrder.jsonPath().getInt("placed");
        delivered = responseInventoryOrder.jsonPath().getInt("delivered");
        File placeOrderApproved = new File(placeAnOrderApproved);
        responsePlaceOrderApproved = storeSteps.placeAnOrder(placeOrderApproved);
        File placeOrderDelivered = new File(placeAnOrderDelivered);
        storeSteps.placeAnOrder(placeOrderDelivered);
        File placeOrderPlaced = new File(placeAnOrderPlaced);
        storeSteps.placeAnOrder(placeOrderPlaced);
    }

    @When("a GET request is sent to store inventory")
    public void a_get_request_is_sent_to_store_inventory() {
        responseInventoryOrder = storeSteps.inventoryByStatus();
    }

    @Then("the get inventory response status code should be {int}")
    public void the_get_inventory_response_status_code_should_be(int status) {
        assertThat(responseInventoryOrder.getStatusCode()).isEqualTo(status);
    }

    @Then("the response should accurately reflect the counts of pets by status")
    public void the_response_should_accurately_reflect_the_counts_of_pets_by_status() {
        int newApproved = responseInventoryOrder.jsonPath().getInt("approved");
        int newPlaced = responseInventoryOrder.jsonPath().getInt("placed");
        int newDelivered = responseInventoryOrder.jsonPath().getInt("delivered");

        assertThat(newApproved)
                .isEqualTo(responseInventoryOrder.jsonPath().getInt("approved")
                        + responsePlaceOrderApproved.jsonPath().getInt("quantity"));
        storeSteps.deleteOrder(responsePlaceOrderApproved.jsonPath().getInt("id"));
    }
}
