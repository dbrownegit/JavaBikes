package readData;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    	connect();
    	
    	for (int i = 0; i < objz.length(); ++i) {
    	    JSONObject stationObjects = objz.getJSONObject(i);
    	    JSONObject locationObjects = objz.getJSONObject(i).getJSONObject("position");
    	    StationId = stationObjects.getInt("number");
    	    stationName = stationObjects.getString("name");
    	    stationAddress = stationObjects.getString("address");
    	    stationLatitude = locationObjects.getDouble("lat");
    	    stationLongitude = locationObjects.getDouble("lng");
    	    stationBanking = stationObjects.getBoolean("bonus");
    	    stationStatus = stationObjects.getString("status");
    	    stationCapacity = stationObjects.getInt("bike_stands");
    	    stationFreeStands = stationObjects.getInt("available_bike_stands");
    	    stationFreeBikes = stationObjects.getInt("available_bikes");
    	    lastUpdate = stationObjects.getLong("last_update");
    	    //System.out.println("The station is " +  stationAddress + ", located at (" + stationLatitude + ", " + stationLongitude + "). It has a capacity of " + stationCapacity + " bikes. There are " + stationFreeBikes + " available bikes and " + stationFreeStands + " free bike stands as of " + lastDate);
    	    // ...
        	//System.out.println("[" + stationLongitude + "," + stationLatitude + "]" );
    	    String sql = "INSERT INTO dublinbikes(address,available_bike_stands,available_bikes,"
    	    		+ "banking,bike_stands,bonus,contract_name,last_update,name,number,position,status,update_time,update_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	}
    	    	
       
    }
    
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    

}