package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import util.Constants;

import java.io.File;

import static util.Constants.*;

public class UserSteps {
    @Step("#actor requested create user")
    public Response createUser() {
        File createUser = new File(createAUSer);
        Response responseCreateUser = RestAssured.given().body(createUser)
                .accept("application/json")
                .contentType("application/json")
                .when().post("/api/v3/user")
                .then()
                .extract().response();
        return responseCreateUser;
    }

    @Step("#actor requested create users by list")
    public Response createUserList() {
        File createUserWithList = new File(createUsersWithList);
        Response responseCreateUserList = RestAssured.given().body(createUserWithList)
                .contentType("application/json")
                .when().post("/api/v3/user/createWithList")
                .then()
                .extract().response();
        return responseCreateUserList;
    }

    @Step("#actor requested login user")
    public Response loginUser(String user, String password) {
        Response responseLogin = RestAssured.given()
                .param("username",user)
                .param("password", password)
                .contentType("application/xml")
                .when().get("/api/v3/user/login?username=" + user + "&password=" + password)
                .then()
                .extract().response();
        return responseLogin;
    }

    @Step("#actor requested logout user")
    public Response logoutUser() {
        Response responseLogout = RestAssured.given()
                .when().get("/api/v3/user/logout")
                .then()
                .extract().response();
        return responseLogout;
    }

    @Step("#actor requested get user")
    public Response getByUser(String user) {
        Response responseGetByUser = RestAssured.given()
                //.accept("application/xml")
                .when().get("/api/v3/user/" + user)
                .then()
                .extract().response();
        return responseGetByUser;
    }

    @Step("#actor requested update user")
    public Response updateUser(String user) {
        File updateAUser = new File(updateUser);
        Response responseUpdateUser = RestAssured.given().body(updateAUser)
                .contentType("application/json")
                .when().put("/api/v3/user/" + user)
                .then()
                .extract().response();
        return responseUpdateUser;
    }

    @Step("#actor requested delete user")
    public Response deleteUser(String user) {
        Response deleteUser = RestAssured.given()
                .when().delete("/api/v3/user/" + user)
                .then()
                .extract().response();
        return deleteUser;
    }

}
