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
<!--  ��ư�� ���߿� form�� submit�ϴ� ������ �ٲ㼭 hidden���� �����ϱ� -->
<h1>My Page</h1>
<a href="ShowMyInfo.jsp">��������</a><br>
<a href="ShowAccRating.jsp">������</a><br>
<a href="DeleteAccount.jsp">��������</a><br>
<%


String userID = (String)session.getAttribute("userID");
int membership =(int)session.getAttribute("membership_grade");

if(membership == 3)  { %>
<a href="UploadMoviePage.jsp">��ȭ���</a><br>
<a href="ModifyMovieInfo.jsp">��ȭ����</a><br>
<%
		}
%>
</body>
</html>