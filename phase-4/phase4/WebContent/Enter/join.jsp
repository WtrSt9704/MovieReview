<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<title>KNU-Movie: join</title>
</head>
<body>
<form action = "getJoin.jsp" method = "POST">
<!-- Practice for Input Form -->
<h4>Provide your ID/PW</h4>
ID(*): <input type = "text" name ="ID">
<br />
PW(*): <input type = "text" name ="PW">
<br />
Phone(*): <input type = "text" name ="phonenumber">
<br />
name: <input type = "text" name ="name">
<br />
Membership(*):
<input type = "checkbox" name = "membership" value = "0"/>basic
<input type = "checkbox" name = "membership" value = "1"/>premium
<input type = "checkbox" name = "membership" value = "2"/>prime
<br />
Gender:
<input type = "checkbox" name = "gender" value = "M"/>Male
<input type = "checkbox" name = "gender" value = "F"/>Female
<br />
Birthday(yyyy-mm-dd):<input type = "text" name ="birthday">
<br />
Job:<input type = "text" name ="job">
<br />
Address:<input type = "text" name ="address">
<br />
<input type = "submit" value ="Submit"/>
<input type="button" name="cancel" value="취소" onClick="location.href='login.html'">
</form>

</body>
</html>