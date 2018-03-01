package readData;

import java.net.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;

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
    	String contractName;
    	int stationCapacity;
    	int stationFreeBikes;
    	int stationFreeStands;
    	long lastUpdate;
    	boolean jobRunning = true;
    	

    	String url = "jdbc:sqlite:C://sqlite/db/tests.db";
    	Connection c=(Connection) DriverManager.getConnection(url);	
    	Statement s=c.createStatement();
    	if(c.isClosed()) {
    		jobRunning = false;
    	}
    	System.out.println("Job running? " + jobRunning);
    	while(jobRunning) {
    		
    	
    	for (int i = 0; i < objz.length(); ++i) {
    	    JSONObject stationObjects = objz.getJSONObject(i);
    	    JSONObject locationObjects = objz.getJSONObject(i).getJSONObject("position");
    	    StationId = stationObjects.getInt("number");
    	    stationName = stationObjects.getString("name");
    	    stationAddress = stationObjects.getString("address");
    	    stationLatitude = locationObjects.getDouble("lat");
    	    stationLongitude = locationObjects.getDouble("lng");
    	    stationBanking = stationObjects.getBoolean("banking");
    	    stationBonus = stationObjects.getBoolean("bonus");
    	    contractName = stationObjects.getString("contract_name");
    	    stationStatus = stationObjects.getString("status");
    	    stationCapacity = stationObjects.getInt("bike_stands");
    	    stationFreeStands = stationObjects.getInt("available_bike_stands");
    	    stationFreeBikes = stationObjects.getInt("available_bikes");
    	    lastUpdate = stationObjects.getLong("last_update");
    	    Timestamp scrapeTimestamp = new Timestamp(System.currentTimeMillis());
    	    Timestamp bikeTimestamp = new Timestamp(lastUpdate);
    	    SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
    	    String webScrapeTime = dateTime.format(scrapeTimestamp);
    	    String bikeScrapeTime = dateTime.format(bikeTimestamp);
    	    String sql = "INSERT INTO dublinbikes VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    		PreparedStatement pstmt = c.prepareStatement(sql);
    		pstmt.setString(1, stationAddress );
    		pstmt.setInt(2, stationFreeStands);
    		pstmt.setInt(3, stationFreeBikes);
    		pstmt.setBoolean(4, stationBanking);
    		pstmt.setInt(5,stationCapacity);
       		pstmt.setBoolean(6,stationBonus);
       		pstmt.setString(7,contractName);
       		pstmt.setLong(8,lastUpdate);
       		pstmt.setString(9,stationName);
       		pstmt.setInt(10,StationId);
       		pstmt.setDouble(11,stationLatitude);
       		pstmt.setString(12,stationStatus);
       		pstmt.setString(13,bikeScrapeTime);
       		pstmt.setString(14, webScrapeTime);
    		pstmt.executeUpdate();
    	}

		System.out.println("Dublin Bikes Web Scrape Complete at: " + LocalDateTime.now());
		Thread.sleep(300000);
    	}
    }
    

    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("\nConnecting to database...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("SUCCESS!\n");
        return conn;
        
    }

    
    

}