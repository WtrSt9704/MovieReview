<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.text.*, java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
th {
	text-align:left;
	padding: 10px;
}
	
</style>
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

	/* User account = new User("jngds2", "123124"); */
		/* phone_number,name,membership,job,address,birthday */
	String sql = "select phone_number, name, membership_grade, job, address, birthday, password, id from account where id = " + "'wewew2'";
	
	int cnt = 0;
	try {
		pstmt = conn.prepareStatement(sql);
	} catch (SQLException ex2) {
		System.err.println("stmt error = " + ex2.getMessage());
	}
	
	String phone_number = null;
	String name = null;
	String membership = null;
	String job = null;
	String address = null;
	String birthday = null;
	String password = null;
	String id = null;
	try {
		rs = pstmt.executeQuery();
	
		while (rs.next()) {
			phone_number = rs.getString(1);
			name = rs.getString(2);
			membership = rs.getString(3);
			job = rs.getString(4);
			address = rs.getString(5);
			birthday = rs.getString(6);
			password = rs.getString(7);
			id = rs.getString(8);
		}
		
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>


<form action="ModifyMyInfo.jsp" action="POST">
<h1> My Information </h1>
<table >
	<tr>
		<th>ID</th>
		<th><input type="text" name="id" value="<%=id == null? "":id%>" disabled /> </th>
	</tr>
	<tr>
		<th>Password</th>
		<th><input type="text" name="password" value="<%=password == null? "":password%>" required/> </th>
	</tr>
	<tr>
		<th>Phone number</th>
		<th><input type="text" name="phone_number" value="<%=phone_number == null? "":phone_number%>" required></th>
	</tr>
	<tr>
		<th>Name</th>
		<th><input type="text" name="name" value="<%=name == null?"":name%>"></th>
	</tr>
	<tr>
		<th>Membership(between 0 and 3)</th>
		<th><input type="number" name="membership" onchange="handleChange(this)" min="0" max="3" value="<%=membership == null?"":membership%>" required></th>
	</tr>
	<tr>
		<th>Job</th>
		<th><input type="text" name="job" value="<%=job == null? "":job%>"></th>
	</tr>
	<tr>
		<th>Address</th>
		<th><input type="text" name="address" value="<%=address == null? "":address%>"></th>
	</tr>
	<tr>
		<th>birthday</th>
		<th><input type="date" name="birthday" value="<%=birthday != null? birthday.split(" ")[0] : ""%>"></th>
	</tr>
</table>



<input type="submit" value="Modify"/>
</form>

<script type="text/javascript">
function handleChange(input) {
    if (input.value < 0)  {
    	input.value = 0;
    	alert("Membership must be between 0 and 3");
    }
    if (input.value > 3) {
    	input.value = 3;
    	alert("Membership must be between 0 and 3")
    }
}
</script>

</body>
</html>