
package webbrowser;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionConfig {
    
    public static final String URL = "jdbc:derby://localhost:1527/HISTORY";
    public static final String USERNAME = "rad";
    public static final String PASSWORD = "12345";
    
    public static Connection getConnection() {
        Connection connection = null;

	try {
	       // Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	
        } catch (Exception e) {
			
            e.printStackTrace();
	}

		return connection; //To change body of generated methods, choose Tools | Templates.
    }
}
