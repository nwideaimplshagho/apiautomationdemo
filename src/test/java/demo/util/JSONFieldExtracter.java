package demo.util;

import io.restassured.path.json.JsonPath;

public class JSONFieldExtracter {

	private JSONFieldExtracter() {
		// TODO Auto-generated constructor stub
	}

	public static String getResponseFieldValue(String response, String fieldName) {
		JsonPath jsPath = new JsonPath(response);
		String placeId = jsPath.get(fieldName);
		return placeId;
	}
	
	public static String getFieldByXPath(String json, String queryPath , int fieldType) {
		System.out.println("Input json " + json);
		JsonPath jsPath = new JsonPath(json);
		String fieldVal = "";
		switch (fieldType) {
		//INTEGER
		case 0:
			int actualAmount = jsPath.getInt(queryPath);
			fieldVal = String.valueOf(actualAmount);
			break;
		case 1:
			fieldVal = jsPath.getString(queryPath);
		case 2:
		default:
			fieldVal = jsPath.get(queryPath).toString();
			break;
		}
		
		return fieldVal;
	}
	
	public static Object getFieldListByXPath(String json, String queryPath , int collectionType) {
		JsonPath jsPath = new JsonPath(json);
		Object fieldVals = null;
		
		switch (collectionType) {
		//LIST
		case 0:
			fieldVals = jsPath.getList(queryPath);
		default:
			break;
		}
		
		return fieldVals;
	}
}
