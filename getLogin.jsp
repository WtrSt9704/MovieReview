<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!--  import JDBC package -->    
<%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>KNU-Movie: Login</title>
</head>
<body>

<h2>Login test</h2>
<%
		String serverIP = "localhost";
		String strSID="orcl";
		String portNum = "1521";
		String user = "knumovie";
		String pass = "comp322";
		String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":"+strSID;
	
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
		
		String id = request.getParameter("ID");
		String password = request.getParameter("PW");
		
		String query = "SELECT ID,PASSWORD,MEMBERSHIP_GRADE FROM ACCOUNT WHERE ACCOUNT.ID ='"+id+"' AND ACCOUNT.PASSWORD ='"+password+"'";
		//System.out.println(query);
		pstmt = conn.prepareStatement(query);
		rs=pstmt.executeQuery();
		int result=0;
		if(rs.next())
		{
			result =1;
		}
		else{
			result =0;
		}
	
		if(result==1)
		{
			session.setAttribute("userID", id);

			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("location.href='LandingPage.jsp'");

			script.println("</script>");

			script.close();

		}
		else if (result == 0) {

			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("alert('비밀번호가 틀립니다.');");

			script.println("history.back();");

			script.println("</script>");

			script.close();
		}
		
		
	rs.close();
	pstmt.close();
	conn.close();
	%>
</body>
</html>