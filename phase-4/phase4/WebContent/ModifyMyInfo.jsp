<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String serverIP = "localhost";
	String strSID = "xe";
	String portNum = "5059";
	String user = "moviedb";
	String pass = "oracle";
	String url = "jdbc:oracle:thin:@" + serverIP + ":" + portNum + ":" + strSID;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn = DriverManager.getConnection(url, user, pass);
	%>

	<%
		String phone_number = request.getParameter("phone_number");
	String name = request.getParameter("name");
	String membership = request.getParameter("membership");
	String job = request.getParameter("job");
	String address = request.getParameter("address");
	String birthday = request.getParameter("birthday");

	out.print(phone_number);
	out.print(name);
	out.print(membership);
	out.print(job);
	out.print(address);
	out.print(birthday);

	String sql = "update account set phone_number=?, name=?, membership_grade=?, job=?, address=?, birthday=? where id = ?";
	out.println(sql);
	try {
		pstmt = conn.prepareStatement(sql);
	} catch (SQLException ex2) {
		System.err.println("stmt error = " + ex2.getMessage());
	}

	try {
		pstmt.setString(1, phone_number);
		pstmt.setString(2, name);
		pstmt.setInt(3, Integer.parseInt(membership));
		pstmt.setString(4, job);
		pstmt.setString(5, address);
		pstmt.setDate(6, java.sql.Date.valueOf(birthday));
		pstmt.setString(7, "wewew2");

		pstmt.executeUpdate();
		
		conn.commit();
		if (pstmt != null)
			pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	%>

	<%
		if (pstmt != null)
		pstmt.close();
	%>

<script type="text/javascript">
	alert("Complete update");
	location.href="MyPage.html";
	
</script>
</body>
</html>