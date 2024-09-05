package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.apache.commons.io.FileUtils;
import steps.StoreSteps;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static util.Constants.placeAnOrder;

public class PlaceOrderStepDefinitions {
    @Steps (shared = true)
    StoreSteps storeSteps;

    Response responsePlaceOrder;
    @Given("valid order details")
    public void valid_order_details() {

    }
    @When("a POST request is sent to store order")
    public void a_post_request_is_sent_to_store_order() {
        responsePlaceOrder = storeSteps.placeAnOrder();
    }
    @Then("the create order response status code should be {int}")
    public void the_create_order_response_status_code_should_be(int status) {
        assertThat(responsePlaceOrder.getStatusCode()).isEqualTo(status);
    }
    @Then("the response should match the order details")
    public void the_response_should_match_the_order_details() throws IOException {
        String strPlaceOrder = String.valueOf(responsePlaceOrder.prettyPrint());
        String jsonPlaceOrder = FileUtils.readFileToString(new File(placeAnOrder), StandardCharsets.UTF_8);
        assertThatJson(strPlaceOrder).whenIgnoringPaths("shipDate").isEqualTo(jsonPlaceOrder);
    }

}
