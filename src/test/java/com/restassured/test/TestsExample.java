package com.restassured.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestsExample {

    @Test
    public void test_one() {
        int statusExpected = 200;
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        int responseStatusCode = response.getStatusCode();
        long time = response.getTime();
        String responseBody = response.getBody().asString();

        System.out.println(responseStatusCode);
        System.out.println(time);
        System.out.println(responseBody);
        System.out.println(response.getHeader("content-type"));

        //Hamcrest
        assertThat(responseStatusCode, equalTo(statusExpected));
    }
}
