<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*, java.util.HashMap"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%@ page language="java" import="km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="./css/basic.css">
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
	int membershipp =(int)session.getAttribute("membership_grade");
	
	if(membershipp == 3)  {
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
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	conn = Util.makeConnection();
%>

<%
	/* max+1말고 다른 방법 찾아보기 */
	String sql = "select max(id) from movie";
	try {
		pstmt = conn.prepareStatement(sql);
	} catch (SQLException ex2) {
		System.err.println("stmt error = " + ex2.getMessage());
	}
	
	int movie_id = 0;
	
	try {
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			movie_id = rs.getInt(1) + 1;			
		}
		
		if (pstmt != null)
			pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>
<div class="box">
	<form action= "UploadMovie.jsp" method="POST">
	
	<table class="box">
		<tr>
			<td>Movie ID<td>
			<td><input class="form-control" type="text" name="movie_id" value="<%=movie_id%>" readonly /><td>
		</tr>
		<tr>
			<td>Title<td>
			<td><input class="form-control" type="text" name="title" required/><td>
		</tr>
		<tr>
			<td>Movie type<br> (movie, tvSeries, knuOrigina)<td>
			<td><input  class="form-control" type="text" name="mtype" onChange="handleTypeChange(this)" required/><td> <!-- 유효성 검사 필요 -->
		</tr>
		<tr>
			<td>Runtime<td>
			<td><input class="form-control" type="number" name="runtime" required/><td>
		</tr>
		<tr>
			<td>Start_year<td>
			<td><input class="form-control" type="date" name="start_year" required/><td>
		</tr>
		<tr>
			<td>End_year<td>
			<td><input class="form-control" type="date" name="end_year"/><td>
		</tr>
		<tr>
			<td>Admin ID<td>
			<td><input class="form-control" type="text" name="admin_id" required/><td>
		</tr>
		<tr>
			<td>Rating<td>
			<td><input class="form-control" type="number" name="rating" min="0" max="10" onchange="handleRatingChange(this)" /><td>
		</tr>
		<tr>
			<td>Num of votes<td>
			<td><input class="form-control" type="number" name="votes" value="0"/><td>
		</tr>
		<tr>
			<td>Director<td>
			<td><input class="form-control" type="text" name="director"/><td>
		</tr>
		<tr>
			<td>Writer<td>
			<td><input class="form-control" type="text" name="writer"/><td>
		</tr>
		<tr>
			<td>Company<td>
			<td><input class="form-control" type="text" name="company"/><td>
		</tr>
		<tr>
			<td>Description<td>
			<td><input type="text" name="description"/><td>
		</tr>
	</table>
	
	
	<center><input class="btn btn-primary" type="submit" value="Upload"/></center>
	
	</form>
	</div>


<script type="text/javascript">
	function handleRatingChange(input) {
	    if (input.value < 0)  {
	    	input.value = 0;
	    	alert("It must be between 0 and 10");
	    }
	    if (input.value > 10) {
	    	input.value = 10;
	    	alert("It must be between 0 and 10")
	    }
	}
	function handleTypeChange(input) {
	  	if (input.value !== "movie" && input.value !== "tvSeries" &&
	  			input.value !== "knuOriginal") {
	  		alert("You must choose one of movie, tvSeries and knuOriginal")
	  		input.value = "";
	  	}
	}
</script>
</body>
</html>