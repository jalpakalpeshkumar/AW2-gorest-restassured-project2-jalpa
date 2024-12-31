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

public class UserExtractionTest {


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

    //1. Extract the All Ids
    @Test
    public void test001(){
        List<Integer> id=response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Ids Are : " + id);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the all Names
    @Test
    public void test002(){
        List<String> names=response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Names Are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the name of 5th object
    @Test
    public void test003(){
        String name=response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object : " + name);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004(){
        String names=response.extract().path("find{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status = inactive : " + names);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005(){
        String gender=response.extract().path("find{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = active : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test006(){
        String names=response.extract().path("find{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female : " + names);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007(){
        String emails=response.extract().path("find{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the emails of the object where status = inactive : " + emails);
        System.out.println("------------------End of Test---------------------------");
    }
    //8. Get the ids of the object where gender = male
    @Test
    public void test008(){
        int id=response.extract().path("find{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where gender = male : " + id);
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the status
    @Test
    public void test009(){
        List<String> status=response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the status are : " + status);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get email of the object where name = Shakuntala Ganaka
    @Test
    public void test010(){
        String email=response.extract().path("find{it.name=='Kamlesh Agarwal DDS'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Lal Dwivedi : " + email);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get gender of id = 7604787
    @Test
    public void test011(){
        String gender=response.extract().path("find{it.id==7609176}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of id = 7609176 : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
