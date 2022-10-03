package com.restassured.test;

import org.testng.annotations.Test;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class RestAssuredAuthTest extends BaseClassAuth{

	/**
	 * Anotaciones de allure usadas para dar información adicional de la prueba cuando
	 * se genera el reporte
	 */
	@Test(description="Valid Authentication Scenario with valid username and password.")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description: Login test with valid username and password.")
	@Story("Get authentication token")
	@Step("GET request to the authentication")
	public void test1() {
		RestAssured.given()
			.get()
			.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));
	}
}
