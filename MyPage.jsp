<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page language="java" import="java.text.*, java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  버튼을 나중에 form을 submit하는 것으로 바꿔서 hidden으로 전달하기 -->
<h1>My Page</h1>
<a href="ShowMyInfo.jsp">정보수정</a><br>
<a href="ShowAccRating.jsp">나의평가</a><br>
<a href="DeleteAccount.jsp">계정삭제</a><br>
<%


String userID = (String)session.getAttribute("userID");
int membership =(int)session.getAttribute("membership_grade");

if(membership == 3)  { %>
<a href="UploadMoviePage.jsp">영화등록</a><br>
<a href="ModifyMovieInfo.jsp">영화수정</a><br>
<%
		}
%>
</body>
</html>