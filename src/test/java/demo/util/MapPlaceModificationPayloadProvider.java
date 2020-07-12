package demo.util;

import java.util.List;

import demo.pojo.AddPlacePayload;
import demo.pojo.Location;


public class MapPlaceModificationPayloadProvider {
	
	public AddPlacePayload getPlacePayload() {
		AddPlacePayload addPlPyLoad = new AddPlacePayload();
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		addPlPyLoad.setLocation(loc);
		addPlPyLoad.setAccuracy(50);
		addPlPyLoad.setName("Frontline house");
		addPlPyLoad.setPhone_number("(+91) 983 893 3937");
		addPlPyLoad.setAddress("29, side layout, cohen 09");
		addPlPyLoad.setTypes(List.of("shoe park", "shop"));
		addPlPyLoad.setWebsite("http://google.com");
		addPlPyLoad.setLanguage("French-IN");

		return addPlPyLoad;
	}
	
	public AddPlacePayload getPlacePayload(String name, String language, String lattitude, String longitude) {
		
		AddPlacePayload addPlPyLoad = new AddPlacePayload();
		Location loc = new Location();
		loc.setLat(Double.valueOf(lattitude));
		loc.setLng(Double.valueOf(longitude));
		addPlPyLoad.setLocation(loc);
		addPlPyLoad.setAccuracy(50);
		addPlPyLoad.setName(name);
		addPlPyLoad.setPhone_number("(+91) 983 893 3937");
		addPlPyLoad.setAddress("29, side layout, cohen 09");
		addPlPyLoad.setTypes(List.of("shoe park", "shop"));
		addPlPyLoad.setWebsite("http://google.com");
		addPlPyLoad.setLanguage(language);

		return addPlPyLoad;

	}
	
	public String getDeletePlacePayload(String placeId) {
		return String.format("{\r\n" + 
				"    \"place_id\":\"%s\"\r\n" + 
				"}", placeId);
	}
}
