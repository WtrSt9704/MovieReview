<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page language="java" import="java.text.*, java.sql.*,java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
String mtype = request.getParameter("mtype");
String genre = request.getParameter("genre");
int runtime = Integer.valueOf(request.getParameter("runtime"));
String userID = (String)session.getAttribute("userID");

String serverIP = "localhost";
String strSID="orcl";
String portNum = "1521";
String user = "knumovie";
String pass = "comp322";
String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":"+strSID;


Connection conn = null;
Statement stmt = null;


Class.forName("oracle.jdbc.driver.OracleDriver");
conn = DriverManager.getConnection(url,user,pass);
stmt = conn.createStatement();
conn.setAutoCommit(false);

String sql = String.format("INSERT INTO ACCOUNT_FAVORITE VALUES('%s','%s','%s', 100)", userID, mtype, genre);

int res=stmt.executeUpdate(sql);
PrintWriter script = response.getWriter();
conn.commit();
session.invalidate(); 
script.println("<script>");

script.println("location.href='login.html'");

script.println("</script>");

script.close();

%>

</body>
</html>