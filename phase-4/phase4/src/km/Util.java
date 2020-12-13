package km;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Util {
	public static Scanner sc = new Scanner(System.in);

	public static void clearScr() {
		for (int i = 0; i < 100; i++)
			System.out.println("");
	}
	
	
	
	public static Connection makeConnection() {
		String serverIP = "localhost";
		String strSID = "xe";
		String portNum = "5059";
		String user = "moviedb";
		String pass = "oracle";
		String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":"+strSID;

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			
		}
		
		return conn;
	}
}
