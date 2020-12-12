<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page language="java" import="java.text.*, java.sql.*, java.io.*"%>
     <%@ page language="java" import="km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		conn = Util.makeConnection();

		String sql;
		String blank = "";
		ResultSetMetaData rsmd;
		
		int cnt;
		int res;
		
		//MovieTitle;
		String description  = request.getParameter("descriptionInput");
		//Movie id
		String movieID = request.getParameter("movieID");
		movieID = movieID.split("/")[0];
		
		if(description == null || description.equals(""))
		{
			//out.println("blank");
			out.println("<script>alert('Description Fail (blank)!');");
			out.println("history.back();</script>");
			
		}
		else
		{
			//out.println(title);	
			sql = "update movie set DESCRIPTIONS = '"+description+"' where id = "+movieID;
			//out.println(sql);
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeUpdate();
			//out.println(sql);
			
		
			if(res != 0)
			{
				
				out.println("<script>alert('Description update success!');");
				out.println("history.back();</script>");

			}
		
		}
		
		
		%>

</body>
</html>