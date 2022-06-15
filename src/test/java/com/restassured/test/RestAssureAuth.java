package com.restassured.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class RestAssureAuth extends BaseClassAuth{
	
	@Test
	public void test1() {
		
		RestAssured.given()
			.get()
			.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));
		
	}

}
