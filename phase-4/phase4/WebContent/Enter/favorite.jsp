<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KNUMovie</title>
<link rel="stylesheet" type="text/css" href="../css/basic.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<div class="box">
<h1> What is your favor?</h1>

<form action = "getFavorite.jsp" method = "POST">
<table>
	<tr>
		<td>Favorite type</td>
		<td><input type = "checkbox" name = "mtype" value = "tvSeries"/>tvSeries
			<input type = "checkbox" name = "mtype" value = "movie"/>movie
			<input type = "checkbox" name = "mtype" value = "knuOriginal"/>knuOriginal
			<input type = "checkbox" name = "mtype" value = ""/>None</td>
	</tr>
	<tr>
		<td>Favorite Genre</td>
		<td><input type = "checkbox" name = "genre" value = "Action"/>Action
		<input type = "checkbox" name = "genre" value = "Comedy"/>Comedy
		<input type = "checkbox" name = "genre" value = "Romance"/>Romance
		<input type = "checkbox" name = "genre" value = "Thiller"/>Thiller
		<input type = "checkbox" name = "genre" value = "Adventure"/>Adventure
		<input type = "checkbox" name = "genre" value = "Sci-Fi"/>Sci-fi
		<input type = "checkbox" name = "genre" value = "Fantasy"/>Fantasy
		<input type = "checkbox" name = "genre" value = ""/>None</td>	
	</tr>
	<tr>
		<td>Runtime</td>
		<td><input type = "text" name ="runtime"></td>	
	</tr>
</table>

<input style="display: table;
    margin: auto;"type = "submit" value ="Submit"/>

</form>
</div>



</body>
</html>