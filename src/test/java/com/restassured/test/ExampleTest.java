package com.restassured.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ExampleTest {

    @Test
    public void test_one() {
        int statusExpected = 200;
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        int responseStatusCode = response.getStatusCode();
        long time = response.getTime();
        String responseBody = response.getBody().asString();

        System.out.println("Response status: " + responseStatusCode);
        System.out.printf("Request time: %s ms%n", time);
        System.out.println("Response body: "+responseBody);
        System.out.println("Response header: "+response.getHeader("content-type"));

        //Hamcrest assertion
        assertThat(responseStatusCode, equalTo(statusExpected));
    }
}
