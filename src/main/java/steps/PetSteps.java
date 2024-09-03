package steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import java.io.File;

import static util.Constants.createPetFile;


public class PetSteps {

    @Before
    public void setupBaseUrl(){
        RestAssured.baseURI = "http://localhost:8080";
    }
    @Step("#actor requested the add pet service")
    public Response createPet(){
        File newPet = new File(createPetFile);
        Response responseCreatePet = RestAssured.given().body(newPet)
                .contentType("application/json")
                .when().post("/api/v3/pet")
                .then()
                .extract().response();
        return responseCreatePet;
    }

    @Step("#actor requested the search by id pet service")
    public Response searchPetById( int id){
        Response responseSearchPetById = RestAssured.given()
                .contentType("application/json")
                .when().get("/api/v3/pet/" + id)
                .then()
                .extract().response();
        return responseSearchPetById;
    }
}
