package readData;

import java.net.*;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class AccessUrl {
    public static void main(String[] args) throws Exception {

    	String root = "https://api.jcdecaux.com/vls/v1/stations?contract="; //base url
        String city = "Dublin"; //enter city here
        String apiKey = "&apiKey=30ae8e68ff6ff0175b969c62ee4bd43c4c32bb0f"; //secure key		   	
    	URL urlBike = new URL(root + city + apiKey);
    	JSONTokener tokenerz = new JSONTokener(urlBike.openStream());
    	JSONArray objz = new JSONArray(tokenerz);
    	
    	//data variables
    	int StationId;
    	String stationName;
    	String stationAddress;
    	double stationLatitude;
    	double stationLongitude;
    	boolean stationBanking;
    	boolean stationBonus;
    	String stationStatus;
    	String stationCity;
    	int stationCapacity;
    	int stationFreeBikes;
    	int stationFreeStands;
    	long lastUpdate;

    	
    	for (int i = 0; i < objz.length(); ++i) {
    	    JSONObject stationObjects = objz.getJSONObject(i);
    	    JSONObject locationObjects = objz.getJSONObject(i).getJSONObject("position");
    	    stationName = stationObjects.getString("name");
    	    stationAddress = stationObjects.getString("address");
    	    stationCapacity = stationObjects.getInt("bike_stands");
    	    stationFreeBikes = stationObjects.getInt("available_bikes");
    	    stationFreeStands = stationObjects.getInt("available_bike_stands");
    	    stationLatitude = locationObjects.getDouble("lat");
    	    stationLongitude = locationObjects.getDouble("lng");
    	    lastUpdate = stationObjects.getLong("last_update");
        	Date lastDate = new Date(lastUpdate);
    	    System.out.println("The station is " +  stationAddress + ", located at (" + stationLatitude + ", " + stationLongitude + "). It has a capacity of " + stationCapacity + " bikes. There are " + stationFreeBikes + " available bikes and " + stationFreeStands + " free bike stands as of " + lastDate);
    	    // ...
    	}
    	

    }
}