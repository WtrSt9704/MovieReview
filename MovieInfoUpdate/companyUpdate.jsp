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
		
		//MovieTitle;
		String company  = request.getParameter("companyInput");
		//Movie id
		String id = request.getParameter("id");
		//test
		id = "11";
		if(company == null || company.equals(""))
		{
			//out.println("blank");
			out.println("<script>alert('Company Fail (blank)!');");
			out.println("history.back();</script>");
			
		}
		else
		{
			//out.println(title);	
			sql = "update movie set company = '"+company+"' where id = "+id;
			out.println(sql);
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeUpdate();
			//out.println(sql);
			
		
			if(res != 0)
			{
				
				out.println("<script>alert('company update success!');");
				out.println("history.back();</script>");

			}
		
		}
		
		
		%>

</body>
</html>