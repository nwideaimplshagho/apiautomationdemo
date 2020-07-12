package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.PrintStream;

import demo.resources.HttpMethodName;
import demo.util.JSONFieldExtracter;
import demo.util.MapPlaceModificationPayloadProvider;
import demo.util.RequestResponseBldr;
import demo.util.RequestResponseSpecModelProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DemoPlaceModificationsStepDefinitions {

	private MapPlaceModificationPayloadProvider mapPlaceModificationPayloadProvider = new MapPlaceModificationPayloadProvider();
	private RequestSpecification reqSpec;
	private Response response;
	private RequestResponseSpecModelProvider reqSpecModelProv = new RequestResponseSpecModelProvider();
	private String responseBody;
	private static Response addPlaceResp;
	private static PrintStream logStream;

	static {
		if (logStream == null) {
			try {
				logStream = new PrintStream(new FileOutputStream("demo.log"));
			} catch (Exception e) {
			}
		}
	}

	@Given("Add place payload with {string} {string} {string} {string}")
	public void add_place_payload(String name, String language, String lattitude, String longitude) {
		reqSpec = given().spec(RequestResponseBldr.getRequestSpec(reqSpecModelProv.getCommonRequestModel(), logStream))
				.body(mapPlaceModificationPayloadProvider.getPlacePayload(name, language, lattitude, longitude));
	}

	@When("User calls {string} using http {string} request")
	public void user_calls_using_http_request(String resource, String httpMethod) {
		System.out.println(String.format("Resource =%s", resource));
		System.out.println(String.format("Http Method =%s", httpMethod));
		response = RequestResponseBldr.getRespone(resource, httpMethod, reqSpec);
	}

	@Then("response should be http status {string}")
	public void response_should_be_http_status(String responseStatus) {
		addPlaceResp = response;
		ValidatableResponse vlResp = addPlaceResp.then()
				.spec(RequestResponseBldr.getResponseSpec(reqSpecModelProv.getCommonResponseModel()));
		vlResp.statusCode(equalTo(200));
		responseBody = vlResp.extract().asString();
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String field, String fieldValue) {
		System.out.println(responseBody);
		String actualFieldVal = JSONFieldExtracter.getResponseFieldValue(responseBody, field);
		assertEquals(fieldValue, actualFieldVal);
	}

	@Then("verify place created maps to {string} using {string}")
	public void verify_place_created_maps_to_using(String name, String resource) {
		reqSpec = given().spec(RequestResponseBldr.getRequestSpec(reqSpecModelProv.getCommonRequestModel(), logStream))
				.queryParam("place_id", JSONFieldExtracter.getResponseFieldValue(responseBody, "place_id"));

		user_calls_using_http_request(resource, HttpMethodName.GET.name());
		responseBody = response.then()
				.spec(RequestResponseBldr.getResponseSpec(reqSpecModelProv.getCommonResponseModel())).extract()
				.asString();
		String actualName = JSONFieldExtracter.getResponseFieldValue(responseBody, "name");
		assertEquals(name, actualName);
	}

	@Given("Delete place paylod")
	public void delete_place_paylod() {
		reqSpec = given().spec(RequestResponseBldr.getRequestSpec(reqSpecModelProv.getCommonRequestModel(), logStream))
				.body(mapPlaceModificationPayloadProvider.getDeletePlacePayload(JSONFieldExtracter
						.getResponseFieldValue(addPlaceResp.then().extract().asString(), "place_id")));
	}
}