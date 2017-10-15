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
    	long stationLatitude;
    	long stationLongitude;
    	boolean stationBanking;
    	boolean stationBonus;
    	String stationStatus;
    	String stationCity;
    	int stationCapacity;
    	int stationFreeBikes;
    	int stationFreeStands;
    	long lastUpdate;

    	
    	for (int i = 0; i < objz.length(); ++i) {
    	    JSONObject bikeobj = objz.getJSONObject(i);
    	    stationName = bikeobj.getString("name");
    	    stationAddress = bikeobj.getString("address");
    	    stationCapacity = bikeobj.getInt("bike_stands");
    	    stationFreeBikes = bikeobj.getInt("available_bikes");
    	    stationFreeStands = bikeobj.getInt("available_bike_stands");
    	    lastUpdate = bikeobj.getLong("last_update");
        	Date lastDate = new Date(lastUpdate);
    	    System.out.println("The station is " +  stationAddress + ". It has a capacity of " + stationCapacity + " bikes. There are " + stationFreeBikes + " available bikes and " + stationFreeStands + " free bike stands as of " + lastDate);
    	    // ...
    	}
    	

    }
}