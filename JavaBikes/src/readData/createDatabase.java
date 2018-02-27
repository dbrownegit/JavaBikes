package readData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class createDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createNewDatabase("tests.db");
		createNewTable();
		//createDatabase app = new createDatabase();
	}

	
    public static void createNewDatabase(String fileName) {
   	 
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";
        
        // SQL statement for creating a new table
		String sql = "CREATE TABLE IF NOT EXISTS dublinbikes (\n"
                + "	address text,\n"
                + "	available_bike_stands integer,\n"
                + "	available_bikes integer,\n"
		        + "	banking text,\n"
			    + "	bike_stands integer,\n"
			    + "	bonus integer,\n"
	            + "	contract_name text,\n"
		        + "	last_update integer,\n"			
			    + "	name text,\n"	
		        + "	number integer,\n"	
	            + "	position real,\n"	
			    + "	status text,\n"	
		        + "	update_time timestamp,\n"	
		        + "	audit_time datetime\n"	
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
        	System.out.println("About to execute statement " + sql);
            stmt.execute(sql);
            System.out.println("Executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
