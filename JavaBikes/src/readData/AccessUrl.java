package readData;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONException; 
import org.json.JSONObject; 
import org.json.JSONArray; 

public class AccessUrl {
    public static void main(String[] args) throws Exception {

    	String root = "https://api.jcdecaux.com/vls/v1/stations?contract="; //base url
        String city = "Dublin"; //enter city here
        String apiKey = "&apiKey=30ae8e68ff6ff0175b969c62ee4bd43c4c32bb0f"; //secure key		
    	URL api = new URL(root + city + apiKey);
        
        BufferedReader in = new BufferedReader(
        new InputStreamReader(api.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine); 
        in.close();
        JSONObject json = new JSONObject(api);
        String title = (String) json.get("file");
        System.out.println("file" + title);
        JSONObject jsonObject = new JSONObject(api); 
        System.out.println(jsonObject);

    }
}