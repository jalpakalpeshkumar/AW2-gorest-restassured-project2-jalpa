package com.gorest.testsuite;

import com.gorest.constant.EndPoint;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

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

    //1. Extract the title is equal to Quia thymum totam aspernatur sperno.
    @Test
    public void test001(){
        String title=response.extract().path("[0].title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the total number of record
    @Test
    public void test002(){
        List<Integer> totalNumber=response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of record : " + totalNumber.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the body of 15th record
    @Test
    public void test003(){
        String body=response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<Integer> user_id=response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records : " + user_id);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the title of all the records
    @Test
    public void test005(){
        List<String> title=response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Extract the title of all records whose user_id = 7609179
    @Test
    public void test006(){
        String titleOfRecord=response.extract().path("find{it.user_id==7609179}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 7609179 : " + titleOfRecord);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Extract the body of all records whose id = 184663
    @Test
    public void test007(){
        String body=response.extract().path("find{it.id==184663}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 184663 : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
