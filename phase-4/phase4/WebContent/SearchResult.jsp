<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%@ page language="java" import="java.text.*, java.sql.*"%>
<%@ page language="java" import="km.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/basic.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light"  style="margin-bottom:30px">
  <a class="navbar-brand" href="LandingPage.jsp">KnuMovie</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
<%
	String userID = (String)session.getAttribute("userID");
	int membership =(int)session.getAttribute("membership_grade");
	
	if(membership == 3)  {
%>

        <a class="nav-link" href="UploadMoviePage.jsp">Upload <span class="sr-only">(current)</span></a>
      </li>
<%
		}
%>
      <li class="nav-item">
        <a class="nav-link" href="LandingPage.jsp">Search</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="MyPage.html">MyPage</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Logout.jsp">Logout</a>
      </li>
    </ul>
  </div>
</nav>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	conn = Util.makeConnection();
%>
<div class="box">
<br>
<%
	String title = request.getParameter("search_data");
	String[] types = request.getParameterValues("mtypes");
	String[] genres = request.getParameterValues("genres");
	String[] versions = request.getParameterValues("versions");

	String sql = SearchingPage.makeQueryForRetrieve(conn, userID, title, types, genres, versions, false);
	
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
			if (i == 3) {
				out.println("<th>" + "REGIONAL " + rsmd.getColumnName(i) + "</th>");
				continue;
			}
			out.println("<th>" + rsmd.getColumnName(i) + "</th>");
		}
		while (rs.next()) {
		
			out.println("<tr onclick='onClickHandler(" + rs.getString(1) + ")' onmouseover=\"changeColor(this, '#FFFFFF', '#008ee2')\">");
			out.println("<td>" + rs.getString(2) + "</td>");
			out.println("<td>" + rs.getString(3) + "</td>");
			out.println("<td>" + rs.getString(4) + "</td>");
			out.println("<td>" + rs.getString(5) + "</td>");
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
</div>
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