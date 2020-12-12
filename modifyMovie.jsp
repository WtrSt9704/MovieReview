<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page language="java" import="java.text.*, java.sql.*"%>
     <%@ page language="java" import= "java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

function titleCheck(frm)
{
   if (frm.title.checked==true)
	   {
	   frm.titleInput.disabled=false
	   frm.titleButton.disabled = false
	   }
	   
   else
	   {
	   frm.titleInput.disabled=true
	   frm.titleButton.disabled = true
	   }
	   
}

function TypeCheck(frm)
{
   if (frm.mtype.checked==true)
	   {
	   frm.mtypeInput.disabled=false
	   frm.mtypeButton.disabled = false
	   }
	   
   else
	   {
	   frm.mtypeInput.disabled=true
	   frm.mtypeButton.disabled = true
	   }
	   
}

function WriterCheck(frm)
{
   if (frm.Writer.checked==true)
	   {
	   frm.WriterInput.disabled=false
	   frm.writerButton.disabled = false
	   }
	   
   else
	   {
	   frm.WriterInput.disabled=true
	   frm.writerButton.disabled = true
	   }
	   
}

function runTimeCheck(frm)
{
   if (frm.runTime.checked==true)
	   {
	   frm.runTimeInput.disabled=false
	   frm.runTimeButton.disabled = false
	   }
	   
   else
	   {
	   frm.runTimeInput.disabled=true
	   frm.runTimeButton.disabled = true
	   }
	   
}

function startYearCheck(frm)
{
   if (frm.startYear.checked == true)
	   {
	   frm.startYearInput.disabled=false
	   frm.startYearButton.disabled = false
	   }
	   
   else
	   {
	   frm.startYearInput.disabled=true
	   frm.startYearButton.disabled = true
	   }
	   
}


function endYearCheck(frm)
{
   if (frm.endYear.checked==true)
	   {
	   frm.endYearInput.disabled=false
	   frm.endYearButton.disabled = false
	   }
	   
   else
	   {
	   frm.endYearInput.disabled=true
	   frm.endYearButton.disabled = true
	   }
	   
}

function voteCheck(frm)
{
   if (frm.vote.checked==true)
	   {
	   frm.voteInput.disabled=false
	   frm.voteButton.disabled = false
	   }
	   
   else
	   {
	   frm.voteInput.disabled=true
	   frm.voteButton.disabled = true
	   }
	   
}
function directorCheck(frm)
{
   if (frm.director.checked==true)
	   {
	   frm.directorInput.disabled=false
	   frm.directorButton.disabled = false
	   }
	   
   else
	   {
	   frm.directorInput.disabled=true
	   frm.directorButton.disabled = true
	   }
	   
}

function companyCheck(frm)
{
   if (frm.company.checked==true)
	   {
	   frm.companyInput.disabled=false
	   frm.companyButton.disabled = false
	   }
	   
   else
	   {
	   frm.companyInput.disabled=true
	   frm.companyButton.disabled = true
	   }
	   
}

function descriptionCheck(frm)
{
   if (frm.description.checked==true)
	   {
	   frm.descriptionInput.disabled=false
	   frm.descriptionButton.disabled = false
	   }
	   
   else
	   {
	   frm.descriptionInput.disabled=true
	   frm.descriptionButton.disabled = true
	   }
	   
}
</script>
<body>
<%

		String serverIP = "localhost";
		String strSID = "orcl";
		String portNum = "1521";
		String user = "university";
		String pass = "comp322";
		String url = "jdbc:oracle:thin:@"+serverIP+":"+portNum+":"+strSID;

		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,user,pass);

		String sql;
		ResultSetMetaData rsmd;
		int cnt;
		//movie id
		String id;
		Date date = null;
		
		id = "11";
		String ans = null;
		
		%>
<%

//out.println("success");
//text(number)에 입력받은 값 어떻게 받지?
//<p/>type : Action / Comedy / Romance / Adventure / Thriller / Sci-Fi / Fantasy		
// 디렉터/라이터/컴퍼니/디스크립션
%>
<h2>수정할 목록을 선택해 주세요</h2>
<form action="titleUpdate.jsp">
Title : 

<%
	sql = "select title from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next())
	{
		ans = rs.getString(1);
	}
	out.println("<input type='input' name='titleInput' id = 'titleInput' disabled value = '"+ans+"'> ");
%>
<input type="submit" name = "titleButton" value="modify" disabled>
<input type="checkbox" name="title" onClick="titleCheck(this.form)">
</form>

<form action="mtypeUpdate.jsp">
<br/>Type :
<%
	sql = "select mtype from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();

	while(rs.next())
	{
		ans = rs.getString(1);
	}
	out.println("<input type='input' name='mtypeInput' id = 'mtypeInput' disabled value = '"+ans+"'> ");
%>
<input type="submit" name = "mtypeButton" value="modify" disabled>
<input type="checkbox" name="mtype" id = "mtype" onClick="TypeCheck(this.form)">
<p style="font-size: 11px"/>Mtype : movie / knuOriginal / tvSeries
</form>

<form action="runTimeUpdate.jsp">
<br/>Runtime :
<%
	sql = "select Runtime from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();

	while(rs.next())
	{
		ans = rs.getString(1);
	}
	out.println("<input type='input' name='runTimeInput' id = 'runTimeInput' disabled value = '"+ans+"'> ");
%>

<input type="submit" name = "runTimeButton" value="modify" disabled>
<input type="checkbox" name="runTime" onClick="runTimeCheck(this.form)">
<p style="font-size: 11px"/>분단위로 입력해 주세요
</form>

<form action="startYearUpdate.jsp">
<br/>Start Year : 
<%
	sql  = "select Start_year from movie where id ="+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();

	while(rs.next())
	{
		ans = rs.getString(1);
	}
	
	if(ans == null || ans.equals(""))
	{
		out.println("<input type='date' name ='startYearInput' disabled >");
	}
	
	else
	{
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-mm-dd");
		try {
				date = sDate.parse(ans);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat Ddate = new SimpleDateFormat("yyyy-mm-dd");
		
		out.println("<input type='date' name ='startYearInput' disabled value = '"+ Ddate.format(date)+"'>");
	}
	

%>

<input type="submit" name = "startYearButton" value="modify" disabled>
<input type="checkbox" name="startYear" onClick="startYearCheck(this.form)">
</form>

<form action="endYearUpdate.jsp">
<br/>End Year : 

<%
	sql  = "select END_YEAR from movie where id ="+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();

	while(rs.next())
	{
		ans = rs.getString(1);
	}
	
	if(ans == null || ans.equals(""))
	{
		out.println("<input type='date' name ='endYearInput' disabled >");
	}
	
	else
	{
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-mm-dd");
		try {
				date = sDate.parse(ans);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat Ddate = new SimpleDateFormat("yyyy-mm-dd");
		
		out.println("<input type='date' name ='endYearInput' disabled value = '"+ Ddate.format(date)+"'>");
	}
	

%>
<input type="submit" name = "endYearButton" value="modify" disabled>
<input type="checkbox" name="endYear" onClick="endYearCheck(this.form)">
<p style="font-size: 11px"/>format : yyyy-dd-mm
</form>


<form action="writerUpdate.jsp">
<br/>Writer : 

<%
	sql = "select writer from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next())
	{
		ans = rs.getString(1);
	}
	if(ans == null || ans.equals(""))
	{
		out.println("<input type='input' name='WriterInput' id = 'WriterInput' disabled> ");	
	}
	else
	{
		out.println("<input type='input' name='WriterInput' id = 'WriterInput' disabled value = '"+ans+"'> ");
	}
	
%>
<input type="submit" name = "writerButton" value="modify" disabled>
<input type="checkbox" name="Writer" onClick="WriterCheck(this.form)">
</form>

<form action="directorUpdate.jsp">
<br/>Director : 
<%
	sql = "select director from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next())
	{
		ans = rs.getString(1);
	}
	if(ans == null || ans.equals(""))
	{
		out.println("<input type='input' name='directorInput' id = 'directorInput' disabled> ");	
	}
	else
	{
		out.println("<input type='input' name='directorInput' id = 'directorInput' disabled value = '"+ans+"'> ");
	}
	
%>

<input type="submit" name = "directorButton" value="modify" disabled>
<input type="checkbox" name="director" onClick="directorCheck(this.form)">
</form>

<form action="voteUpdate.jsp">
<br/>vote : 
<%
	sql = "select NUM_OF_VOTES from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next())
	{
		ans = rs.getString(1);
	}
	if(ans == null || ans.equals(""))
	{
		out.println("<input type='input' name='voteInput' id = 'voteInput' disabled> ");	
	}
	else
	{
		out.println("<input type='input' name='voteInput' id = 'voteInput' disabled value = '"+ans+"'> ");
	}
	
%>
<input type="submit" name = "voteButton" value="modify" disabled>
<input type="checkbox" name="vote" onClick="voteCheck(this.form)">
</form>

<form action="companyUpdate.jsp">
<br/>Company : 
<%
	sql = "select company from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next())
	{
		ans = rs.getString(1);
	}
	if(ans == null || ans.equals(""))
	{
		out.println("<input type='input' name='companyInput' id = 'companyInput' disabled> ");	
	}
	else
	{
		out.println("<input type='input' name='companyInput' id = 'companyInput' disabled value = '"+ans+"'> ");
	}
	
%>
<input type="submit" name = "companyButton" value="modify" disabled>
<input type="checkbox" name="company" onClick="companyCheck(this.form)">
</form>

<form action="descriptionUpdate.jsp">
<br/>Description : 

<%
	sql = "select DESCRIPTIONS from movie where id = "+id;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next())
	{
		ans = rs.getString(1);
	}
	if(ans == null || ans.equals(""))
	{
		out.println("<p/><textarea rows='5' cols='60' name='descriptionInput' id = 'descriptionInput' disabled wrap='physical'></textarea>");	
	}
	else
	{
		out.println("<p/><textarea rows='5' cols='60' name='descriptionInput' id = 'descriptionInput' disabled wrap='physical'>"+ans+"</textarea>");
	}
	
%>
<input type="submit" name = "descriptionButton" value="modify" disabled>
<input type="checkbox" name="description" onClick="descriptionCheck(this.form)">
</form>

<br/><input type="button" name = "back" id = "back" onclick="location.href='ShowDetail.jsp'" value="뒤로가기">
</body>
</html>