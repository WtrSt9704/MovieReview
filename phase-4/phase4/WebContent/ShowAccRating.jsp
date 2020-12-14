<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="java.text.*, java.sql.*"%>
    <%@ page language="java" import="km.*"%>
    <%--show Rating about An acccount --%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/basic.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<center><title>My Rating</title></center>
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
        <a class="nav-link" href="MyPage.jsp">MyPage</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Logout.jsp">Logout</a>
      </li>
    </ul>
  </div>
</nav>
<%

		sql = "select MOVIE.Title, RATING.STARS FROM MOVIE, RATING WHERE RATING.ACCOUNT_ID = '"+userID+ "' and rating.movie_id = movie.id";
		//out.println(sql);
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		out.println("<div class='box'>");
		out.println("<center><h3>MY EVALUATING</h3></center>");
		out.println("<table class='table table-bordered'>");
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
		out.println("</div>");
		
		
%>
<%--back 버튼 --%>
<%-- <input type="button" name = "Back" onclick="location.href='menu.jsp'" value="goBack">--%>
				
</body>
</html>