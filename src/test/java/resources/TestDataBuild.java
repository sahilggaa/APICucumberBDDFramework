package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.MapsData;

public class TestDataBuild {
	
	
	
	public static MapsData addPlace(String name, String address, String language)
	{
		
		MapsData md = new MapsData();
		Location l = new Location();
		
		l.setLat(39.987);
		l.setLng(-123.21);
		
		List<String> types = new ArrayList<String>();
		types.add("Market");
		types.add("Shop");
		
		md.setLocation(l);
		md.setAccuracy(12);
		md.setName(name);
		md.setPhone_number("(+91) 983 893 3937");
		md.setAddress(address);
		md.setTypes(types);
		md.setWebsite("http://google.com");
		md.setLanguage(language);
		
		return md;
		
	}
	
	
	public static String getPlaceId(String place_id)
	{
		return "{\"place_id\":\"" + place_id + "\"}";
	}

}
