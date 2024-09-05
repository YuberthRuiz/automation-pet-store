package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import steps.StoreSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteOrderStepDefinitions {
    @Steps(shared = true)
    StoreSteps storeSteps;

    Response responsePlaceOrder;
    Response responseDeleteOrder;
    Response responseGetOrder;

    int idOrder;

    @Given("an order is created")
    public void an_order_exists_with_id() {
        responsePlaceOrder = storeSteps.placeAnOrder();
        idOrder = responsePlaceOrder.jsonPath().get("id");
    }
    @When("a DELETE request is sent to store order")
    public void a_delete_request_is_sent_to_store_order() {
        responseDeleteOrder = storeSteps.deleteOrder(idOrder);
    }
    @Then("the delete order response status code should be {int}")
    public void the_delete_order_response_status_code_should_be(int status) {
       assertThat(responseDeleteOrder.getStatusCode()).isEqualTo(status);
    }
    @Then("a subsequent GET request to with the order ID should return a {int} status code")
    public void get_request_to_with_the_order_id_return_a_status_code(int status) {
        responseGetOrder = storeSteps.findOrderById(idOrder);
        assertThat(responseGetOrder.getStatusCode()).isEqualTo(status);
    }
}
