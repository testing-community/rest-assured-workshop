package com.restassured.test;

import org.testng.annotations.Test;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class RestAssureAuth extends BaseClassAuth{
	
	
	@Test(priority = 0, description="Valid Autentication Scenario with valid username and password.")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description: Login test with valid username and password.")
	@Story("Get autentication token")
	@Step("Petition get to autentication")
	public void test1() {
		
		RestAssured.given()
			.get()
			.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));
		
	}

}
