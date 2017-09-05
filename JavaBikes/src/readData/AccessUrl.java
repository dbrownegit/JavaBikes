package readData;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import org.json.*;
import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

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
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(inputLine);
        JSONObject jsonobject = (JSONObject) obj;
        String name = (String) jsonobject.get("Name");
    }
}