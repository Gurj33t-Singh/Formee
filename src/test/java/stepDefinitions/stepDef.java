package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class stepDef {

	RequestSpecification Request;
	String Response;
	String Token;	
	Utils util=new Utils();
	
	
	@Given("{string} and {string} for user")
	public void something_and_something_for_user(String email, String password) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		Request=given()
				.spec(util.requestSpec())
				.formParams("email", email)
				.formParams("password", password)
				.formParams("remember_me", "0");
	}
	
	
	@Given("User is logged in with {string} user")
	public void user_is_logged_in_with_user(String userType) throws IOException {
	    // Write code here that turns the phrase above into concrete actions

		//get login token
		switch(userType){
		case "Personal":
			String LoginResp=given()
			.spec(util.requestSpec())
			.formParams("email", "gpavan524@gmail.com")
			.formParams("password", "12345678")
			.formParams("remember_me", "0")
			.when().post("api/login").then().extract().response().asString();
	
			Token=Utils.getJsonVal(LoginResp, "data.token");
			
			break;
			
		case "Business":
			System.out.println("Business user login logic pending...");
		}
		
		
	}

	
	@Given("User has payload with Classified ID {string}")
	public void user_has_payload_with_classified_id(String ClassifiedID) throws IOException {
	    // Write code here that turns the phrase above into concrete actions

		//Actual request 
		Request=given()
				.spec(util.requestSpec())
				.header("Accept", "application/json")
				.header("Authorization", "Bearer "+Token)
				.header("Content-Type", "application/json")
				.body(new TestDataBuild().ProductListingByID_Payload(ClassifiedID));
		
	}
	
	
	@When("User calls {string} api")
	public void user_calls_api(String API) {
	    // Write code here that turns the phrase above into concrete actions
		
		switch(API) {
		
		case "login":
			Response=Request.when().post("api/"+API).then().extract().response().asString();
			break;
			
		case "product-listing-by-id":
			Response=Request.when().post("api/"+API).then().extract().response().asString();
			break;
		}
		
	}
	
	
	@Then("{string} key value is {string}")
	public void key_value_is(String Key, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(Response);
		String ActualValue=Utils.getJsonVal(Response, Key);
		assertEquals(ActualValue, ExpectedValue);
	}
	
	
	@Then("{string} is captured")
	public void is_captured(String Key) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Json Key \""+Key+"\": " + Utils.getJsonVal(Response, Key));
	}
	
}
