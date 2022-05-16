package com.restassured.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssureAuth extends BaseClassAuth{
	
	@Test
	public void test1() {
		
		int code = RestAssured.given().
				get().
				getStatusCode();
		
		System.out.println("Response code form server is " + code);
		
	}

}
