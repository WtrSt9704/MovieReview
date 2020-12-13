<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter, km.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String mtype = request.getParameter("mtype");
String genre = request.getParameter("genre");
int runtime = Integer.valueOf(request.getParameter("runtime"));
String userID = (String)session.getAttribute("userID");

Connection conn = null;
Statement stmt = null;

conn = Util.makeConnection();
stmt = conn.createStatement();
conn.setAutoCommit(false);

String sql = String.format("INSERT INTO ACCOUNT_FAVORITE VALUES('%s','%s','%s', 100)", userID, mtype, genre);

int res=stmt.executeUpdate(sql);
PrintWriter script = response.getWriter();
conn.commit();
session.invalidate(); 
script.println("<script>");
script.println("alert('Fill in your id and password used to sign up')");
script.println("location.href='login.html'");

script.println("</script>");

script.close();

%>

</body>
</html>