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

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put("page", 1);
        queryParams.put("per_page", 20);

        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .queryParams(queryParams)
                .when()
                .get(EndPoint.GET_ALL_USERS)
                .then().log().all().statusCode(200);

    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        // response.body(" ",hasSize(20));
    }

    //2. Verify the if the name of id = 7609174 is equal to ”Kamlesh Agarwal DDS”
    @Test
    public void test002() {
        response.body("findAll{it.id==7609174}.name.get(0)", equalTo("Kamlesh Agarwal DDS"));
    }

    //3. Check the single ‘Name’ in the Array list (Kamlesh Agarwal DDS)
    @Test
    public void test003() {
        response.body("name", hasItem("Kamlesh Agarwal DDS"));
        // response.body("id",equalTo("Baidehi Khatri"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test004() {
        response.body("name", hasItems("Chaanakya Prajapat", "Girish Adiga"));
    }

    //5. Verify the emai of userid = 7604786 is equal “dutta_acharyanandana@mitchell.example”
    @Test
    public void test005() {
        response.body("findAll{it.id == 7604786}.email", equalTo("dutta_acharyanandana@mitchell.example"));
    }

    //6. Verify the status is “Active” of user name is “Vaishnavi Pilla”
    @Test
    public void test006() {
        response.body("findAll{it.name=='Vaishnavi Pilla'}.status", hasItem("active"));
    }

    //7. Verify the Gender = male of user name is “Rohana Rana”
    @Test
    public void test007() {
        response.body("findAll{it.name=='Rohana Rana'}.gender", hasItem("male"));
    }
}
