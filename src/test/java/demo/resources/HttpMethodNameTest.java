package demo.resources;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpMethodNameTest {

	@Test
	public void testValueOf() {
//		System.out.println(HttpMethodName.POST.name());
		HttpMethodName httpMethodEnum = HttpMethodName.valueOf("Post".toUpperCase().intern());
		assertEquals(HttpMethodName.POST, httpMethodEnum);
	}
	
	@Test
	public void testAPIValueOf() {
		APIResource apiResourceEnum = APIResource.valueOf("AddPlaceAPI");
		assertEquals(APIResource.AddPlaceAPI, apiResourceEnum);
	}
}
