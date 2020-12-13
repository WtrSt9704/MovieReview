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
<style>
td {
	text-align:left;
	padding: 10px;
}
	
</style>
</head>

<body>
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
<form action= "UploadMovie.jsp" method="POST">

<table>
	<tr>
		<td>Movie ID<td>
		<td><input type="text" name="movie_id" value="<%=movie_id%>" readonly /><td>
	</tr>
	<tr>
		<td>Title<td>
		<td><input type="text" name="title" required/><td>
	</tr>
	<tr>
		<td>Movie type<br> (movie, tvSeries, knuOrigina)<td>
		<td><input type="text" name="mtype" onChange="handleTypeChange(this)" required/><td> <!-- 유효성 검사 필요 -->
	</tr>
	<tr>
		<td>Runtime<td>
		<td><input type="number" name="runtime" required/><td>
	</tr>
	<tr>
		<td>Start_year<td>
		<td><input type="date" name="start_year" required/><td>
	</tr>
	<tr>
		<td>End_year<td>
		<td><input type="date" name="end_year"/><td>
	</tr>
	<tr>
		<td>Admin ID<td>
		<td><input type="text" name="admin_id" required/><td>
	</tr>
	<tr>
		<td>Rating<td>
		<td><input type="number" name="rating" min="0" max="10" onchange="handleRatingChange(this)" /><td>
	</tr>
	<tr>
		<td>Num of votes<td>
		<td><input type="number" name="votes" value="0"/><td>
	</tr>
	<tr>
		<td>Director<td>
		<td><input type="text" name="director"/><td>
	</tr>
	<tr>
		<td>Writer<td>
		<td><input type="text" name="writer"/><td>
	</tr>
	<tr>
		<td>Company<td>
		<td><input type="text" name="company"/><td>
	</tr>
	<tr>
		<td>Description<td>
		<td><input type="text" name="description"/><td>
	</tr>
</table>


<center><input type="submit" value="Upload"/></center>

</form>

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