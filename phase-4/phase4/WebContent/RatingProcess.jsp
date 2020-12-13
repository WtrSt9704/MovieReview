<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="java.text.*, java.sql.*, km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		
		conn = Util.makeConnection();

		String sql;
		ResultSetMetaData rsmd;
		Statement stmt = null;
		int cnt;
		int res;
		
		//test
		String Mid = request.getParameter("movieID");
		
		%>
<body>
<%

//UserID = request.getParameter("USerID");
//Mid = request.getParameter("id");

String RatingPoint = request.getParameter("Rating11");
//out.println(RatingPoint);

sql = "select * from rating where Account_id = '"+userID+"' and movie_id = "+Mid;
out.println("<br/>"+sql);
pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery();
//out.println(rs.next());

if(rs.next() == true)
{
	
	//exist
	sql = "update rating set stars = "+RatingPoint+" where Account_id = '"+userID+"'";
	pstmt = conn.prepareStatement(sql);
	res = pstmt.executeUpdate();
	//out.println(sql);
	
	if(res != 0)
	{
		out.println("<script>alert('success to update')</script>");
	}
	
}
	else
	{
		sql = "select max(Rating_id) from rating";
		rs = pstmt.executeQuery(sql);
		rs.next();
		int maxNum = rs.getInt(1)+1;
		//out.println("<br/>"+maxNum);
		sql = "insert into rating values (" + maxNum + ", '" + userID + "', " + RatingPoint + ", " + Mid+")";
		pstmt = conn.prepareStatement(sql);
		//out.println("<br/>"+sql);
		//out.println(sql);
		res = pstmt.executeUpdate();
		//out.println(res);
		
		if(res != 0)
		{
			out.println("<script>alert('success to insertion')</script>");
		}
		else
		{
			out.println("<script>alert('fail to insertion')</script>");
		}
		
	}

	%>	

</body>
</html>