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

%>
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
		int a;
		
		//MovieTitle;
		String runtime  = request.getParameter("runTimeInput");
		//Movie id
		String movieID = request.getParameter("movieID");
		movieID = movieID.split("/")[0];
		
		
		boolean result = true;
		
		// null, 공백일시
		if (runtime == null || runtime.length() == 0) {
			result = false;
		}
		// null이나 공백이 아닐시
		else {
			for (int i = 0; i < runtime.length(); i++) {
				int c = (int) runtime.charAt(i);
				// 숫자가 아니라면
				if (c < 48 || c > 57) {
					result = false;
				}
			}
		}
		out.println(runtime);
		
		if(result == true)
		{
			sql = "update movie set Runtime  = "+runtime+" where id = "+movieID;
			//out.println(sql);
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeUpdate();
			//out.println(sql);
			
		
			if(res != 0)
			{
				out.println("<script>alert('success!');");
				out.println("history.back();</script>");

			}
		}
		else
		{
			out.println("<script>alert('only number available');");
			out.println("history.back();</script>");
		}
		
		


		
		
		%>

</body>
</html>