<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*, java.util.HashMap"%>
<%@ page language="java" import="java.util.ArrayList"%>
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

<form action= "UploadMovie.jsp" >
<table>
<tr>
	<td>Title<td>
	<td><input type="text" required/><td>
</tr>
<tr>
	<td>Movie type<td>
	<td><input type="text" required/><td> <!-- 유효성 검사 필요 -->
</tr>
<tr>
	<td>Runtime<td>
	<td><input type="number" required/><td>
</tr>
<tr>
	<td>Start_year<td>
	<td><input type="date" required/><td>
</tr>
<tr>
	<td>End_year<td>
	<td><input type="date"/><td>
</tr>
<tr>
	<td>Admin ID<td>
	<td><input type="text" required/><td>
</tr>
<tr>
	<td>Rating<td>
	<td><input type="number"/><td>
</tr>
<tr>
	<td>Num of votes<td>
	<td><input type="number" value="0"/><td>
</tr>
<tr>
	<td>Director<td>
	<td><input type="text"/><td>
</tr>
<tr>
	<td>Writer<td>
	<td><input type="text"/><td>
</tr>
<tr>
	<td>Company<td>
	<td><input type="text"/><td>
</tr>
<tr>
	<td>Description<td>
	<td><input type="text"/><td>
</tr>

</table>


<input type="submit" value="Upload" />
</form>
</body>
</html>