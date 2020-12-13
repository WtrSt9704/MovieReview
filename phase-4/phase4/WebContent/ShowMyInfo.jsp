<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.text.*, java.sql.*"%>
<%@ page language="java" import="km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
td {
	text-align:left;
	padding: 10px;
}
	
</style>
</head>
<body>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	conn = Util.makeConnection();

	/* User account = new User("jngds2", "123124"); */
		/* phone_number,name,membership,job,address,birtdday */
	String sql = "select phone_number, name, membership_grade, job, address, birtdday, password, id from account where id = " + "'wewew2'";
	
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
		<td>ID</td>
		<td><input type="text" name="id" value="<%=id == null? "":id%>" disabled /> </td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type="text" name="password" value="<%=password == null? "":password%>" required/> </td>
	</tr>
	<tr>
		<td>Phone number</td>
		<td><input type="text" name="phone_number" value="<%=phone_number == null? "":phone_number%>" required></td>
	</tr>
	<tr>
		<td>Name</td>
		<td><input type="text" name="name" value="<%=name == null?"":name%>"></td>
	</tr>
	<tr>
		<td>Membership(between 0 and 3)</td>
		<td><input type="number" name="membership" onchange="handleChange(tdis)" min="0" max="3" value="<%=membership == null?"":membership%>" required></td>
	</tr>
	<tr>
		<td>Job</td>
		<td><input type="text" name="job" value="<%=job == null? "":job%>"></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><input type="text" name="address" value="<%=address == null? "":address%>"></td>
	</tr>
	<tr>
		<td>birtdday</td>
		<td><input type="date" name="birthday" value="<%=birthday != null? birthday.split(" ")[0] : ""%>"></td>
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