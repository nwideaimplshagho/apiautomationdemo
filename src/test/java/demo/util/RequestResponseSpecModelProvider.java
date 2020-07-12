package demo.util;

import java.util.List;

import io.restassured.http.ContentType;

public class RequestResponseSpecModelProvider {

	public RequestSpecModel getCommonRequestModel() {
		return RequestSpecModel.builder().baseUri("https://rahulshettyacademy.com").contentType(ContentType.JSON)
				.queryParamList(List.of(QueryParam.builder().key("key").value("qaclick123").build())).build();
	}
	
	public ResponseSpecModel getCommonResponseModel() {
		return ResponseSpecModel.builder().statusCode(200).contentType(ContentType.JSON).build();
	}
}
