package steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;

import java.io.File;

import static util.Constants.*;


public class PetSteps {
    @Before
    public void setupBaseUrl() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Step("#actor requested the add pet service")
    public Response createPet() {
        File newPet = new File(createPetFile);
        Response responseCreatePet = RestAssured.given().body(newPet)
                .contentType("application/json")
                .when().post("/api/v3/pet")
                .then()
                .extract().response();
        return responseCreatePet;
    }

    @Step("#actor a POST request is sent to pet endpoint without missing_attribute")
    public Response createPetMissingAttribute(String missing) {
        switch (missing) {
            case "name":
                File newPetName = new File(createPetWithoutName);
                Response responseCreateMissingName = RestAssured.given().body(newPetName)
                        .contentType("application/json")
                        .when().post("/api/v3/pet")
                        .then()
                        .extract().response();
                return responseCreateMissingName;
            case "photoUrls":
                File newPetphotoUrls = new File(createPetWithoutPhotoUrls);
                Response responseCreateMissingPhoto = RestAssured.given().body(newPetphotoUrls)
                        .contentType("application/json")
                        .when().post("/api/v3/pet")
                        .then()
                        .extract().response();
                return responseCreateMissingPhoto;
            default:
                throw new IllegalArgumentException("No user missing attribute defined " + missing);
        }


    }

    @Step("#actor requested the search by id pet service")
    public Response searchPetById(int id) {
        Response responseSearchPetById = RestAssured.given()
                .contentType("application/json")
                .when().get("/api/v3/pet/" + id)
                .then()
                .extract().response();
        return responseSearchPetById;
    }

    @Step("#actor requested the update pet service")
    public Response updatePet() {
        File updatePet = new File(updatePetFile);
        Response responseUpdatePet = RestAssured.given().body(updatePet)
                .contentType("application/json")
                .when().put("/api/v3/pet/")
                .then()
                .extract().response();
        return responseUpdatePet;
    }

    @Step("#actor requested the delete pet service")
    public Response deletePet(int petId) {
        Response responseCreatePet = RestAssured.given()
                .contentType("application/json")
                .when().delete("/api/v3/pet/" + petId)
                .then()
                .extract().response();
        return responseCreatePet;
    }

    @Step("#actor requested the add pet service")
    public Response createPet(File file) {
        Response responseCreatePet = RestAssured.given().body(file)
                .contentType("application/json")
                .when().post("/api/v3/pet")
                .then()
                .extract().response();
        return responseCreatePet;
    }

    @Step("#actor requested the find by status pet service")
    public Response getPetByStatus(String status) {
        Response responseCreatePet = RestAssured.given()
                .contentType("application/json")
                .when().get("/api/v3/pet/findByStatus?status=" + status)
                .then()
                .extract().response();
        return responseCreatePet;
    }

    @Step("#actor requested the find by tags pet service")
    public Response getPetByTag(String tag) {
        Response responseCreatePet = RestAssured.given()
                .contentType("application/json")
                .when().get("/api/v3/pet/findByTags?tags=" + tag)
                .then()
                .extract().response();
        return responseCreatePet;
    }

    @Step("#actor requested the find by tags pet service")
    public Response uploadPetImg(String id) {
        File petPicture = new File(petPhoto);
        Response responseCreatePet = RestAssured.given().body(petPicture)
                .header("accept", "application/json")
                .multiPart("File",petPicture)
                .post("api/v3/pet/12/uploadImage")
                .then()
                .extract().response();

        return responseCreatePet;
    }


}
