package demo.util;

import java.io.PrintStream;

import demo.resources.APIResource;
import demo.resources.HttpMethodName;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseBldr {

	private RequestResponseBldr() {
	}

	public static RequestSpecification getRequestSpec(RequestSpecModel reqSpecModel, PrintStream prtStr) {
		QueryParam qp = reqSpecModel.getQueryParamList().get(0);
		return new RequestSpecBuilder().setBaseUri(reqSpecModel.getBaseUri())
				.setContentType(reqSpecModel.getContentType()).addQueryParam(qp.getKey(), qp.getValue())
				.addFilter(RequestLoggingFilter.logRequestTo(prtStr))
				.addFilter(ResponseLoggingFilter.logResponseTo(prtStr)).build();
	}

	public static ResponseSpecification getResponseSpec(ResponseSpecModel respSpecModel) {
		return new ResponseSpecBuilder().expectStatusCode(respSpecModel.getStatusCode())
				.expectContentType(respSpecModel.getContentType()).build();
	}

	public static Response getRespone(String resource, String httpMethod, RequestSpecification reqSpec) {
		Response response = null;
		HttpMethodName httpMethodEnum = HttpMethodName.valueOf(httpMethod.toUpperCase().intern());
		switch (httpMethodEnum) {
		case GET:
			response = reqSpec.when().get(APIResource.valueOf(resource).getResource());
			break;
		case POST:
			response = reqSpec.when().post(APIResource.valueOf(resource).getResource());
			break;
		default:
			break;
		}

		return response;
	}
}
