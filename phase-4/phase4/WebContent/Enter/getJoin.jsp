<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  import JDBC package -->    
<%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter, km.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		Connection conn = null;
		Statement stmt = null;

		conn = Util.makeConnection();
		stmt = conn.createStatement();
		conn.setAutoCommit(false);
		
		String id = request.getParameter("ID");
		String password = request.getParameter("PW");
		String phonenumber = request.getParameter("phonenumber");
		String name = request.getParameter("name");
		int membership =Integer.valueOf(request.getParameter("membership"));
		String gender = request.getParameter("gender");
		if(gender == null)
			gender = "";
		String birthday = request.getParameter("birthday");
		int age;
		String newdate;
		String tmp2;
		if (birthday.equals("")) {
			age = 0;
			newdate = "NULL";

		} else {
			tmp2 = birthday.substring(0, 4);
			age = 2020 - Integer.valueOf(tmp2);

			newdate = "TO_DATE('" + birthday + "','yyyy-mm-dd')";

		}
		String job = request.getParameter("job");
		String address = request.getParameter("address");
		
		
		int res;
		String sql;
		ResultSet rs;
		PreparedStatement pstmt;

		sql = "SELECT ID FROM ACCOUNT WHERE ACCOUNT.ID ='"+id+"'";
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");

			script.println("alert('회원가입 실패 : id 중복');");

			script.println("history.back();");

			script.println("</script>");
		}
		
		sql = "SELECT PHONE_NUMBER FROM ACCOUNT WHERE ACCOUNT.PHONE_NUMBER ='"+phonenumber+"'";
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");

			script.println("alert('회원가입 실패 : 전화번호 중복');");

			script.println("history.back();");

			script.println("</script>");
		}
		
		
		
		
		System.out.println(gender);
		System.out.println(age);
		System.out.println(address);
		if (age == 0) {
			sql = String.format("INSERT INTO ACCOUNT VALUES('%s','%s','%s','%s', %d ,'%s', NULL, %s, '%s', '%s')",
					id, password, phonenumber, name, membership,gender, newdate, job, address);
		} else {
			sql = String.format("INSERT INTO ACCOUNT VALUES('%s','%s','%s','%s', %d , '%s', %d, %s, '%s', '%s')",
					id, password, phonenumber, name, membership,  gender,age, newdate, job, address);
		}
				
		System.out.println(sql);
		
		
		try{
			res=stmt.executeUpdate(sql);
			conn.commit();
			session.setAttribute("userID", id);

			PrintWriter script = response.getWriter();

		    script.println("<script>");

			script.println("location.href='favorite.jsp'");

			script.println("</script>");

			script.close();
			

		
		}catch (SQLException e) {
			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("alert('회원가입 실패 : 필수값 입력하시오');");

			script.println("history.back();");

			script.println("</script>");

			script.close();
		}
		
		 %>
</body>
</html>