package com.gorest.crudtest;

import com.gorest.constant.EndPoint;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static int id;
    static String createName = "kokila";
    String email = TestUtils.getRandomValue() + "prime11@gmail.com";
    static String createGender = "female";
    static String createStatus = "active";

    @Test(priority = 1)
    public void getListOfUsers() {
        Map<String,Integer> queryParams=new HashMap<>();
        queryParams.put("page",1);
        queryParams.put("per_page",20);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .queryParams(queryParams)
                .when()
                .get(EndPoint.GET_ALL_USERS)
                .then().log().all().statusCode(200);
        id = response.extract().path("[0].id");
    }

    @Test(priority = 2)
    public void getUsersById() {
        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .get(EndPoint.GET_USER_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 3)
    public void createUsersRecord() {

        UserPojo usersPojo = new UserPojo();
        usersPojo.setName(createName);
        usersPojo.setEmail(email);
        usersPojo.setGender(createGender);
        usersPojo.setStatus(createStatus);

        ValidatableResponse response = given().log().all()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .when()
                .body(usersPojo)
                .post(EndPoint.CREATE_USERS_RECORD)
                .then().log().all().statusCode(201);
        id=response.extract().path("id");

    }

    @Test(priority = 4)
    public void updateUser() {
        UserPojo usersPojo = new UserPojo();
        usersPojo.setName(createName+"updated");
        usersPojo.setEmail(email);
        usersPojo.setGender(createGender);
        usersPojo.setStatus(createStatus);

        ValidatableResponse response = given().log().all()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .pathParam("id", id)
                .when()
                .body(usersPojo)
                .put(EndPoint.UPDATE_USER)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 5)
    public void partiallyUpdateUser() {
        UserPojo usersPojo = new UserPojo();
        usersPojo.setName(createName+"updated");
        usersPojo.setEmail(email);
        usersPojo.setGender(createGender);
        usersPojo.setStatus(createStatus);

        ValidatableResponse response = given().log().all()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .pathParam("id", id)
                .when()
                .body(usersPojo)
                .patch(EndPoint.PARTIALLY_UPDATE_USER)
                .then().statusCode(200);
    }

    @Test(priority = 6)
    public void deleteUserRecord() {
        given().log().all()
                .header("Accept","application/json")
                .header("Content-type","application/json")
                .header("Authorization","Bearer e832a705655493c7e143306a74ee223819a9c96b6376f7603bcebd4887434d6e")
                .pathParam("id", id)
                .when()
                .delete(EndPoint.DELETE_USER)
                .then().log().all()
                .statusCode(204);
    }
}
