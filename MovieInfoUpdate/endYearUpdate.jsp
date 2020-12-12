<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page language="java" import="java.text.*, java.sql.*, java.io.*"%>
     <%@ page  language="java"  import = "java.util.*, java.text.*"%>
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
		String serverIP = "localhost";
		String strSID = "orcl";
		String portNum = "1521";
		String user = "university";
		String pass = "comp322";
		String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":"+strSID;
		

		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);

		String sql;
		String blank = "";
		ResultSetMetaData rsmd;
		
		int cnt;
		int res;
		
		int eMM;
		int eDD;
		int eYear;
		
		
		int sMM;
		int sDD;
		int sYear;
		
		String aaa = null;
		//year
		String endYear;
		String id = request.getParameter("id");
		id = "11";
		sql = "select Start_year from movie where id = 11";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next())
		
		{
			aaa = rs.getString(1);
			//out.println(aaa);
		}
		if(aaa != null)
		{
			sYear = 1000*(aaa.charAt(0)-48)+100*(aaa.charAt(1)-48)+10*(aaa.charAt(2)-48)+(aaa.charAt(3)-48);
			sMM = 10*(aaa.charAt(5)-48)+(aaa.charAt(6)-48);
			sDD = 10*(aaa.charAt(8)-48)+(aaa.charAt(9)-48);
			
			
			try{
				endYear = request.getParameter("endYearInput");
				eYear = 1000*(endYear.charAt(0)-48)+100*(endYear.charAt(1)-48)+10*(endYear.charAt(2)-48)+(endYear.charAt(3)-48);
				eMM = 10*(endYear.charAt(5)-48)+(endYear.charAt(6)-48);
				eDD = 10*(endYear.charAt(8)-48)+(endYear.charAt(9)-48);
				if(sYear>= eYear && sMM>=eMM && sDD >= eDD)
				{
					sql = "update movie set End_year = TO_DATE('"+endYear+"', 'yyyy-mm-dd') where id = "+id;
					
					//TO_DATE('1982-12-22', 'yyyy-mm-dd')
					
					out.println(sql);
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
				out.println("<script>alert('endYear Fail!');");
				out.println("history.back();</script>");
			}
		}
		else
		{
			try{
				endYear = request.getParameter("endYearInput");
				eYear = 1000*(endYear.charAt(0)-48)+100*(endYear.charAt(1)-48)+10*(endYear.charAt(2)-48)+(endYear.charAt(3)-48);
				eMM = 10*(endYear.charAt(5)-48)+(endYear.charAt(6)-48);
				eDD = 10*(endYear.charAt(8)-48)+(endYear.charAt(9)-48);
				
					sql = "update movie set End_year = TO_DATE('"+endYear+"', 'yyyy-mm-dd') where id = "+id;
					
					//TO_DATE('1982-12-22', 'yyyy-mm-dd')
					
					out.println(sql);
					pstmt = conn.prepareStatement(sql);
					res = pstmt.executeUpdate();
					
					if(res != 0)
					{
						
						out.println("<script>alert('success!');");
						out.println("history.back();</script>");

					}
				
				
				
			}
			catch(Exception e)
			{
				out.println("<script>alert('end Year Fail!');");
				out.println("history.back();</script>");
			}
		}
		
		
		
		//test
		
		
		
		

		
		
		%>

</body>
</html>