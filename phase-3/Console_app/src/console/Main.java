package console;
import java.util.*;
import java.sql.*;
public class Main {
	public static final String URL = "jdbc:oracle:thin:@localhost:5059:xe";	// Oracle Address
	public static final String USER_NAME = "moviedb";				// Oracle user ID
	public static final String USER_PASSWD = "oracle";				// Oracle user password
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		/* Connect to oracle */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Success!");
		} catch(ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}
		
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWD);
			conn.setAutoCommit(false);
		}catch(SQLException ex) {
			System.err.println("Cannot get a connection : " + ex.getMessage());
			System.exit(1);
		}
		
		while (true) {
			if (LandingPage.land(conn, stmt)) {
				//System.out.println("Login Success!");
				
			} else {
				break;
			}
		}
		
		
		try {
			conn.close();
		}catch(SQLException ex) {
			System.err.println("Cannot close" + ex.getMessage());
			System.exit(1);
		}
	}
	
	
}
