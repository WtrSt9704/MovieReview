<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%@ page language="java" import="java.text.*, java.sql.*"%>
<%@ page language="java" import="km.SearchingPage"%>
<%@ page language="java" import="km.Util"%>
<%@ page language="java" import="km.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String serverIP = "localhost";
	String strSID = "xe";
	String portNum = "5059";
	String user = "moviedb";
	String pass = "oracle";
	String url = "jdbc:oracle:thin:@" + serverIP + ":" + portNum + ":" + strSID;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn = DriverManager.getConnection(url, user, pass);
%>
	<h4>------------ Result ----------</h4>
<%
	User account = new User("jngds2", "123124");
	String title = request.getParameter("search_data");
	String[] types = request.getParameterValues("mtypes");
	String[] genres = request.getParameterValues("genres");
	String[] versions = request.getParameterValues("versions");
	
	String sql = SearchingPage.retrieve(conn, account, title, types, genres, versions, false);
	
	int cnt = 0;
	try {
		pstmt = conn.prepareStatement(sql);
	} catch (SQLException ex2) {
		System.err.println("stmt error = " + ex2.getMessage());
	}
	
	try {
		rs = pstmt.executeQuery();
		out.println("<table border=\"1\">");
		ResultSetMetaData rsmd = rs.getMetaData();
		int ccnt = rsmd.getColumnCount();
		for (int i = 2; i <= ccnt; ++i) {
			out.println("<th>" + rsmd.getColumnName(i) + "</th>");
		}
		while (rs.next()) {
		
			out.println("<tr onclick='onClickHandler(" + rs.getString(1) + ")' onmouseover=\"changeColor(this, '#FFFFFF', '#008ee2')\">");
			out.println("<td>" + rs.getString(2) + "</td>");
			out.println("<td>" + rs.getString(3) + "</td>");
			out.println("<td>" + rs.getString(4) + "</td>");
			out.println("</tr>");
		
		}
		out.println("</table>");
		
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

%>

<%
	if (rs != null)
		rs.close();
	if (pstmt != null)
		pstmt.close();
	conn.close();
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<script type="text/javascript">
	function onClickHandler(movieID) {
		document.location.href = "ShowDetail.jsp?movieID=" + movieID;
	}
	
	function changeColor(obj, oldColor, newColor) {
		obj.style.backgroundColor = newColor;
		obj.onmouseout = function(){
			obj.style.backgroundColor = oldColor;
		}
	}
</script>
</body>
</html>