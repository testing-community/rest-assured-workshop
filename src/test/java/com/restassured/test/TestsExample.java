package com.restassured.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestsExample {
	
	@Test
	public void test_one() {
		
		int statusExpected = 200; 
		Response response =  RestAssured.get("https://reqres.in/api/users?page=2");
		
		int responseStatusCode = response.getStatusCode();
		long time = response.getTime();
		String responseBody = response.getBody().asString();
		
		System.out.println(responseStatusCode);
		System.out.println(time);
		System.out.println(responseBody);
		System.out.println(response.getHeader("content-type"));
		
		AssertJUnit.assertEquals(statusExpected, responseStatusCode);
		
	}	

}
