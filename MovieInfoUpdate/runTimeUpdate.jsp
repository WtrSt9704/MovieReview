<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page language="java" import="java.text.*, java.sql.*, java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

%>
<%

		String serverIP = "localhost";
		String strSID = "orcl";
		String portNum = "1521";
		String user = "university";
		String pass = "comp322";
		String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":"+strSID;

		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);

		String sql;
		String blank = "";
		ResultSetMetaData rsmd;
		
		int cnt;
		int res;
		int a;
		
		//MovieTitle;
		String runtime  = request.getParameter("runTimeInput");
		//Movie id
		String id = request.getParameter("id");
		//test
		id = "11";
		
		
		boolean result = true;
		
		// null, 공백일시
		if (runtime == null || runtime.length() == 0) {
			result = false;
		}
		// null이나 공백이 아닐시
		else {
			for (int i = 0; i < runtime.length(); i++) {
				int c = (int) runtime.charAt(i);
				// 숫자가 아니라면
				if (c < 48 || c > 57) {
					result = false;
				}
			}
		}
		out.println(runtime);
		
		if(result == true)
		{
			sql = "update movie set Runtime  = "+runtime+" where id = "+id;
			//out.println(sql);
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeUpdate();
			//out.println(sql);
			
		
			if(res != 0)
			{
				out.println("<script>alert('success!');");
				out.println("history.back();</script>");

			}
		}
		else
		{
			out.println("<script>alert('only number available');");
			out.println("history.back();</script>");
		}
		
		


		
		
		%>

</body>
</html>