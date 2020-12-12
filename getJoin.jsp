<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!--  import JDBC package -->    
<%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
		String serverIP = "localhost";
		String strSID="orcl";
		String portNum = "1521";
		String user = "knumovie";
		String pass = "comp322";
		String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":"+strSID;
	
		
		Connection conn = null;
		Statement stmt = null;
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);
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
		/*	PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("location.href='favorite.jsp'");

			script.println("</script>");

			script.close();*/
			
			conn.commit();
			
			response.sendRedirect("favorite.jsp?userID="+id);

		
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