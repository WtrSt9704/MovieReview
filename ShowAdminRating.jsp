<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="java.text.*, java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Rating</title>
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
		ResultSetMetaData rsmd;
		Statement stmt = null;
		int cnt = 1;
		int res;
		
		%>
</head>
<body>
<%

sql = "select movie.Title,Rating.stars FROM MOVIE, RATING where rating.Movie_id = movie.id";
pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery();
out.println("<h3>-----Rating-----</h3>");
out.println("<table border = \"1\">");
out.println("<th>id</th>");
out.println("<th>Title</th>");
out.println("<th>Rating</th>");
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