package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;

import java.io.File;

import static util.Constants.placeAnOrder;


public class StoreSteps {
    @Step("#actor requested the inventory by status")
    public Response inventoryByStatus() {
        Response responseInventoryByStatus = RestAssured.given()
                .contentType("application/json")
                .when().get("/api/v3/store/inventory")
                .then()
                .extract().response();
        return responseInventoryByStatus;
    }

    @Step("#actor requested the place an order request")
    public Response placeAnOrder() {
        File placeOrder = new File(placeAnOrder);
        Response responseInventoryByStatus = RestAssured.given().body(placeOrder)
                .contentType("application/json")
                .when().post("/api/v3/store/order")
                .then()
                .extract().response();
        return responseInventoryByStatus;
    }

    @Step("#actor requested find an order by id")
    public Response findOrderById(int idOrder) {
        Response responseInventoryByStatus = RestAssured.given()
                .contentType("application/xml")
                .when().get("/api/v3/store/order" + idOrder)
                .then()
                .extract().response();
        return responseInventoryByStatus;
    }

    @Step("#actor requested find an order by id")
    public Response deleteOrder(int idOrder) {
        Response responseInventoryByStatus = RestAssured.given()
                .contentType("application/xml")
                .when().delete("/api/v3/store/order" + idOrder)
                .then()
                .extract().response();
        return responseInventoryByStatus;
    }
}
