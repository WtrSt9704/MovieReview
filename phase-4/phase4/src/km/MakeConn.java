package km;

import java.sql.*;
public class MakeConn {
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
