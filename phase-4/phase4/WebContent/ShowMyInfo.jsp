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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<link rel="stylesheet" href="./css/basic.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light"  style="margin-bottom:30px">
  <a class="navbar-brand" href="LandingPage.jsp">KnuMovie</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
<%
	
	int membershipp =(int)session.getAttribute("membership_grade");
	
	if(membershipp == 3)  {
%>

        <a class="nav-link" href="UploadMoviePage.jsp">Upload <span class="sr-only">(current)</span></a>
      </li>
<%
		}
%>
      <li class="nav-item">
        <a class="nav-link" href="LandingPage.jsp">Search</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="MyPage.jsp">MyPage</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Logout.jsp">Logout</a>
      </li>
    </ul>
  </div>
</nav>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	conn = Util.makeConnection();

	String userID = (String)session.getAttribute("userID");
	String sql = "select phone_number, name, membership_grade, job, address, birthday, password, id from account where id = " + "'" + userID + "'";
	
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
<div class="box">
	<center><h1> My Information </h1></center>
	<table >
		<tr>
			<td>ID</td>
			<td><input class="form-control" type="text" name="id" value="<%=id == null? "":id%>" disabled /> </td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input class="form-control" type="text" name="password" value="<%=password == null? "":password%>" required/> </td>
		</tr>
		<tr>
			<td>Phone number</td>
			<td><input class="form-control" type="text" name="phone_number" value="<%=phone_number == null? "":phone_number%>" required></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><input class="form-control" type="text" name="name" value="<%=name == null?"":name%>"></td>
		</tr>
		<tr>
			<td>Membership(between 0 and 3)</td>
			<td><input class="form-control" type="number" name="membership" onchange="handleChange(tdis)" min="0" max="3" value="<%=membership == null?"":membership%>" required></td>
		</tr>
		<tr>
			<td>Job</td>
			<td><input class="form-control" type="text" name="job" value="<%=job == null? "":job%>"></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><input class="form-control" type="text" name="address" value="<%=address == null? "":address%>"></td>
		</tr>
		<tr>
			<td>birthday</td>
			<td><input class="form-control" type="date" name="birthday" value="<%=birthday != null? birthday.split(" ")[0] : ""%>"></td>
		</tr>
	</table>
	<center><input class="btn btn-primary" type="submit" value="Modify"/></center>
</div>



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