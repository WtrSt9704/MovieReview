<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page language="java" import="java.text.*, java.sql.*, java.io.*"%>
     <%@ page  language="java"  import = "java.util.*, java.text.*, km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int mm =  cal.get(Calendar.MONTH)+1;
		int dd = cal.get(Calendar.DATE);

		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		
		conn = Util.makeConnection();

		String sql;
		String blank = "";
		ResultSetMetaData rsmd;
		
		int cnt;
		int res;
		
		int sMM;
		int sDD;
		int sYear;
		//year
		String startYear;
		String id = request.getParameter("id");
		//Movie id
		String movieID = request.getParameter("movieID");
		movieID = movieID.split("/")[0];
		try{
			startYear = request.getParameter("startYearInput");
			sYear = 1000*(startYear.charAt(0)-48)+100*(startYear.charAt(1)-48)+10*(startYear.charAt(2)-48)+(startYear.charAt(3)-48);
			sMM = 10*(startYear.charAt(5)-48)+(startYear.charAt(6)-48);
			sDD = 10*(startYear.charAt(8)-48)+(startYear.charAt(9)-48);
			if(year>= sYear && mm>=sMM && dd >= sDD)
			{
				sql = "update movie set Start_year = TO_DATE('"+startYear+"', 'yyyy-mm-dd') where id = "+movieID;
				
				//TO_DATE('1982-12-22', 'yyyy-mm-dd')
				//out.println(sql);
				pstmt = conn.prepareStatement(sql);
				res = pstmt.executeUpdate();
				
				if(res != 0)
				{
					
					out.println("<script>alert('success!');");
					out.println("history.back();</script>");

				}
			}
			else
			{
				out.println("<script>alert ('cannot exceed the current date');");
				out.println("history.back();</script>");
			}
		}
		catch(Exception e)
		{
			out.println("<script>alert('Fail!');");
			out.println("history.back();</script>");
		}

		
		
		
	
		
		
		%>

</body>
</html>