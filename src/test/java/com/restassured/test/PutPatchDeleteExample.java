package com.restassured.test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDeleteExample {
	
	@Test
	public void testPut() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Ernesto Perez");
		request.put("job", "QA Automation");
		
		RestAssured.baseURI =  "https://reqres.in/api";
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/users/2").
		then().
			statusCode(200).
			log().all();
	}
	
	@Test
	public void testPatch() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Ernesto Perez");
		request.put("job", "QA Automation");

		RestAssured.baseURI =  "https://reqres.in";
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("/api/users/2").
		then().
			statusCode(200).
			log().all();
	}
	
	@Test
	public void testDelete() {

		RestAssured.baseURI =  "https://reqres.in";

		when().
			delete("/api/users/2").
		then().
			statusCode(204).
			log().all();
	}
}
