<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>KNU-Movie: join</title>
<link rel="stylesheet" type="text/css" href="../css/basic.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>


<div class=box>
<form action = "getJoin.jsp" method = "POST">
<!-- Practice for Input Form -->
<h1>Sign up</h1>

<table>
	<tr>
		<td>ID(*)</td>
		<td><input type = "text" name ="ID"></td>
	</tr>
	<tr>
		<td>PW(*)</td>
		<td><input type = "password" name ="PW"></td>
	</tr>
	<tr>
		<td>Phone(*)</td>
		<td><input type = "text" name ="phonenumber"></td>
	</tr>
	<tr>
		<td>Name</td>
		<td><input type = "text" name ="ID"></td>
	</tr>
	<tr>
		<td>Membership(*)</td>
		<td><input type = "radio" name = "membership" value = "0" checked = "checked"/>basic
			<input type = "radio" name = "membership" value = "1"/>premium
			<input type = "radio" name = "membership" value = "2" />prime</td>
	</tr>
	<tr>
		<td>Gender</td>
		<td><input type = "radio" name = "gender" value = "M"/>Male
			<input type = "radio" name = "gender" value = "F"/>Female</td>
	</tr>
	<tr>
		<td>Birthday(yyyy-mm-dd)</td>
		<td><input type = "text" name ="birthday"></td>
	</tr>
	<tr>
		<td>Job</td>
		<td><input type = "text" name ="job"></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><input type = "text" name ="address"></td>
	</tr>
	<tr>
		<td><input type = "submit" value ="Submit"/></td>
		<td><input type="button" name="cancel" value="Back" onClick="location.href='login.html'"></td>
	</tr>
	

</table>

<div class="box">

</div>

</form>
</div>


</body>
</html>