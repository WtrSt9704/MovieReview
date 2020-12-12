<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="java.text.*, java.sql.*"%>
    <%@ page language="java" import="km.*"%>
    <%--show Rating about An acccount --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Rating</title>
		<%-- DB conn --%>
		<%


		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		conn = Util.makeConnection();

		String sql;
		ResultSetMetaData rsmd;
		int cnt;
		String UserID = null;
		UserID = "afa11v";			//test
		//UserID= request.getParameter("UserID");
		
		%>
		<%-- DB conn --%>
</head>

<body>

<%

		sql = "select MOVIE.Title, RATING.STARS FROM MOVIE, RATING WHERE RATING.ACCOUNT_ID = '"+UserID+ "' and rating.movie_id = movie.id";
		//out.println(sql);
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		out.println("<h3>-----Rating-----</h3>");
		out.println("<table border = \"1\">");
		out.println("<th>id</th>");
		out.println("<th>Title</th>");
		out.println("<th>Rating</th>");
		cnt = 1;
		while(rs.next())
		{
			out.println("<tr>");
			out.println("<td>"+cnt+"</td>");
			out.println("<td>"+rs.getString(1)+"</td>");
			out.println("<td>"+rs.getString(2)+"</td>");
			out.println("</tr>");
			cnt++;
		}
		out.println("</table>");
		
		
%>
<%--back 버튼 --%>
<input type="button" name = "Back" onclick="location.href='menu.jsp'" value="goBack">
				
</body>
</html>