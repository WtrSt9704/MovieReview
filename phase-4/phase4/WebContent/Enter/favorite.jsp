<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>KNUMovie</title>
</head>
<body>
<form action = "getFavorite.jsp" method = "POST">
Favorite type : 
<input type = "checkbox" name = "mtype" value = "tvSeries"/>tvSeries
<input type = "checkbox" name = "mtype" value = "movie"/>movie
<input type = "checkbox" name = "mtype" value = "knuOriginal"/>knuOriginal
<input type = "checkbox" name = "mtype" value = ""/>None
<br />
Favorite Genre: 
<input type = "checkbox" name = "genre" value = "Action"/>tvSeries
<input type = "checkbox" name = "genre" value = "Comedy"/>movie
<input type = "checkbox" name = "genre" value = "Romance"/>knuOriginal
<input type = "checkbox" name = "genre" value = "Thiller"/>Thiller
<input type = "checkbox" name = "genre" value = "Adventure"/>Adventure
<input type = "checkbox" name = "genre" value = "Sci-Fi"/>Sci-fi
<input type = "checkbox" name = "genre" value = "Fantasy"/>Fantasy
<input type = "checkbox" name = "genre" value = ""/>None
<br />
Runtime:<input type = "text" name ="runtime">
<input type = "submit" value ="Submit"/>

</form>


</body>
</html>