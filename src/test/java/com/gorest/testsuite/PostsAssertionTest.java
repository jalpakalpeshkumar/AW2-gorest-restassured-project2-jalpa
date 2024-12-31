package com.gorest.testsuite;

import com.gorest.constant.EndPoint;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put("page", 1);
        queryParams.put("per_page", 25);

        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .queryParams(queryParams)
                .when()
                .get(EndPoint.GET_ALL_POSTS)
                .then().log().all().statusCode(200);


    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        // response.body(" ");
    }

    //2. Verify the if the title of id = 184660 is equal to ”Suffragium spes comitatus omnis vesper centum beneficium approbo voluptas.”
    @Test
    public void test002() {
        response.body("findAll{it.id==184660}.title.get(0)", equalTo("Suffragium spes comitatus omnis vesper centum beneficium approbo voluptas."));
    }

    //3. Check the single user_id in the Array list (7609175)
    @Test
    public void test003() {
        response.body("user_id", hasItem(7609175));
    }

    //4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
    @Test
    public void test004() {
        response.body("user_id", hasItems(7609175, 7609173, 7609171));
    }

    //5. Verify the body of userid = 7609175 is equal “Sto tepidus cumque. Temeritas desolo qui. Blandior cohors omnis. Ascit auxilium strues. Sed et ciminatio. Viduo aliquam incidunt. Est crudelis creo. Cotidie culpo adsuesco. Cohibeo causa numquam. Copia tripudio vestigium. Atrox velit voveo. Bellum tabesco tabella. Et corpus approbo.”
    @Test
    public void test005() {
        response.body("findAll{it.user_id==7609175}.body.get(0)", equalTo("Sto tepidus cumque. Temeritas desolo qui. Blandior cohors omnis. Ascit auxilium strues. Sed et ciminatio. Viduo aliquam incidunt. Est crudelis creo. Cotidie culpo adsuesco. Cohibeo causa numquam. Copia tripudio vestigium. Atrox velit voveo. Bellum tabesco tabella. Et corpus approbo."));
    }
}
