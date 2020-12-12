<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="java.text.*, java.sql.*, km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

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
		String UserID ="afa11v" ;
		String Mid = "123";
		String RatingPoint = "12" ;		
		%>
<body>
<%

//UserID = request.getParameter("USerID");
//Mid = request.getParameter("id");

RatingPoint = request.getParameter("Rating11");
//out.println(RatingPoint);

sql = "select * from rating where Account_id = '"+UserID+"' and movie_id = "+Mid;
out.println("<br/>"+sql);
pstmt = conn.prepareStatement(sql);
rs = pstmt.executeQuery();
//out.println(rs.next());

if(rs.next() == true)
{
	
	//exist
	sql = "update rating set stars = "+RatingPoint+" where Account_id = '"+UserID+"'";
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
		sql = "insert into rating values (" + maxNum + ", '" + UserID + "', " + RatingPoint + ", " + Mid+")";
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