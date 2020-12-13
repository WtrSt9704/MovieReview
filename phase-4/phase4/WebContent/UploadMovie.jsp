<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*, km.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	conn = Util.makeConnection();
%>

<%
	String movie_id = request.getParameter("movie_id");
	String title = request.getParameter("title");
	String mtype = request.getParameter("mtype");
	String runtime = request.getParameter("runtime");
	String start_year = request.getParameter("start_year");
	String end_year = request.getParameter("end_year");
	String admin_id = request.getParameter("admin_id");
	String rating = request.getParameter("rating");
	String votes = request.getParameter("votes");
	String director = request.getParameter("director");
	String writer = request.getParameter("writer");
	String company = request.getParameter("company");
	String description = request.getParameter("description");
	
	String sql = "insert into movie values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	try {
		pstmt = conn.prepareStatement(sql);
	} catch (SQLException ex2) {
		System.err.println("stmt error = " + ex2.getMessage());
	}

	try {
		//max+1말고 다른 방법 찾아보기 
		pstmt.setInt(1, Integer.parseInt(movie_id));
		pstmt.setString(2, title);
		pstmt.setString(3, mtype);
		pstmt.setInt(4, Integer.parseInt(runtime));
		pstmt.setDate(5, java.sql.Date.valueOf(start_year));
		pstmt.setObject(6, end_year==null? null:java.sql.Date.valueOf(end_year), java.sql.Types.Date);
		pstmt.setString(7, admin_id);
		pstmt.setFloat(8, Float.parseFloat(rating));
		pstmt.setInt(9, Integer.parseInt(votes));
		pstmt.setString(10, director);
		pstmt.setString(11, writer);
		pstmt.setString(12, company);
		pstmt.setString(13, description);

		pstmt.executeUpdate();
		
		conn.commit();
		if (pstmt != null)
			pstmt.close();
	} catch (SQLException e) {

		e.printStackTrace();
	}
	
%>
<script type="text/javascript">
	alert("Complete update");
	window.history.back();
</script>
</body>
</html>