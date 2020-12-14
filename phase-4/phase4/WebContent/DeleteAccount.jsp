<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*"%>
<%@ page language="java" import="km.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	conn = Util.makeConnection();
%>

<%
	String sql = "delete from account where id=?";
	try {
		pstmt = conn.prepareStatement(sql);
	} catch (SQLException ex2) {
		System.err.println("stmt error = " + ex2.getMessage());
	}
	
	try {
		String userID = (String)session.getAttribute("userID");
		pstmt.setString(1, userID);
		pstmt.executeUpdate();
		conn.commit();
		if (pstmt != null)
			pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>

</body>
<script type="text/javascript">
alert("We completely delete your account");
window.location.href="/phase4/enter/login.html"
</script>
</html>