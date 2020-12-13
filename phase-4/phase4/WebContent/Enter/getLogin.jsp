<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  import JDBC package -->    
<%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter, km.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KNU-Movie: Login</title>

</head>
<body>

<h2>Login test</h2>
<%
	
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		conn = Util.makeConnection();
		
		String id = request.getParameter("ID");
		String password = request.getParameter("PW");
		int membership=0;
		
		String query = "SELECT ID,PASSWORD,MEMBERSHIP_GRADE FROM ACCOUNT WHERE ACCOUNT.ID ='"+id+"' AND ACCOUNT.PASSWORD ='"+password+"'";
		//System.out.println(query);
		pstmt = conn.prepareStatement(query);
		rs=pstmt.executeQuery();
		int result=0;
		if(rs.next())
		{
			result =1;
			membership = rs.getInt("MEMBERSHIP_GRADE");
		}
		else{
			result =0;
		}
	
		if(result==1)
		{
			session.setAttribute("userID", id);
			session.setAttribute("membership_grade", membership);
			
			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("location.href='/phase4/LandingPage.jsp'");

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