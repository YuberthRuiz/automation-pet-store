package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.apache.commons.io.FileUtils;
import steps.StoreSteps;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static util.Constants.placeAnOrder;

public class GetOrderStepDefinitions {
    @Steps(shared = true)
    StoreSteps storeSteps;

    Response responsePlaceOrder;

    Response responseGetOrder;

    int idOrder;
    @Given("an order exists with ID")
    public void an_order_exists_with_id() {
        responsePlaceOrder = storeSteps.placeAnOrder();
        idOrder = responsePlaceOrder.jsonPath().get("id");
    }
    @When("a GET request is sent to store order by ID")
    public void a_get_request_is_sent_to_store_order_by_id() {
        responseGetOrder = storeSteps.findOrderById(idOrder);
    }
    @Then("the get order response status code should be {int}")
    public void the_get_order_response_status_code_should_be(int status) {
        assertThat(responseGetOrder.getStatusCode()).isEqualTo(status);
    }
    @Then("the response should contain the correct order details")
    public void the_response_should_contain_the_correct_order_details() {
        String strGetOrder = String.valueOf(responseGetOrder.prettyPrint());
        String strPlaceOrder = String.valueOf(responsePlaceOrder.prettyPrint());
        assertThatJson(strGetOrder).whenIgnoringPaths("shipDate").isEqualTo(strPlaceOrder);
    }
    @Given("no order exists with ID {int}")
    public void no_order_exists_with_id(int idNoOrder) {
        idOrder = idNoOrder;
    }

}
