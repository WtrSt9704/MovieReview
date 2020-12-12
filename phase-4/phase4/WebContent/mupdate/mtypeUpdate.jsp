<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page language="java" import="java.text.*, java.sql.*, java.io.*, km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;

		conn = Util.makeConnection();

		String sql;
		String blank = "";
		ResultSetMetaData rsmd;
		
		int cnt;
		int res;
		
		//MovieTitle;
		String mtype  = request.getParameter("mtypeInput");
		//Movie id
		String movieID = request.getParameter("movieID");
		movieID = movieID.split("/")[0];
		
		
		//공백
		if(mtype == null || mtype.equals(""))
		{
			//out.println("blank");
			out.println("<script>alert('Blank spaces are not allowed.');");
			out.println("history.back();</script>");
			
		}
		//success
		else if(mtype.equals("movie") || mtype.equals("knuOriginal") || mtype.equals("tvSeries"))
		{
			//out.println(title);	
			sql = "update movie set mtype = '"+mtype+"' where id = "+movieID;
			//out.println(sql);
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeUpdate();
			out.println(sql);
			
		
			if(res != 0)
			{
				
				out.println("<script>alert('success!');");
				out.println("history.back();</script>");
				//request.setAttribute("mtype", o);
				
			}
		
		
		}
		//이상한값
		else
		{
			out.println("<script>alert('please Re-enter your answer');");
			out.println("history.back();</script>");
	
		}
		
		
		%>

</body>
</html>