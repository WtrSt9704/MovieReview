<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*"%>
<%@ page language="java" import="km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light"  style="margin-bottom:30px">
  <a class="navbar-brand" href="LandingPage.jsp">KnuMovie</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
<%
	String userID = (String)session.getAttribute("userID");
	int membership =(int)session.getAttribute("membership_grade");
	
	if(membership == 3)  {
%>

        <a class="nav-link" href="UploadMoviePage.jsp">Upload <span class="sr-only">(current)</span></a>
      </li>
<%
		}
%>
      <li class="nav-item">
        <a class="nav-link" href="LandingPage.jsp">Search</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="MyPage.jsp">MyPage</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Logout.jsp">Logout</a>
      </li>
    </ul>
  </div>
</nav>

<%
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		conn = Util.makeConnection();

		String sql;
		ResultSetMetaData rsmd;
		int cnt;
		
		String movieID = request.getParameter("movieID");
%>

<%

//out.println("success");
//text(number)에 입력받은 값 어떻게 받지?
//<p/>type : Action / Comedy / Romance / Adventure / Thriller / Sci-Fi / Fantasy		
// 디렉터/라이터/컴퍼니/디스크립션
%>
<center><h2>MOVIE MODIFICATION</h2></center>

<%	
	conn.setAutoCommit(false);
	sql = "set transaction name '" + userID + "'"; // transaction
	pstmt = conn.prepareStatement(sql);
	pstmt.execute();
	
	sql = "select title, mtype, runtime, start_year, end_year," +
			"num_of_votes, director, writer, company, descriptions from movie where id = "+movieID;
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	String title = null;
	String mtype = null;
	String runtime = null;
	String start_year = null;
	String end_year = null;
	String num_of_votes = null;
	String director = null;
	String writer = null;
	String company = null;
	String descriptions = null;
	
	while(rs.next())
	{
		title = rs.getString(1);
		mtype = rs.getString(2);
		runtime = rs.getString(3);
		start_year = rs.getString(4);
		end_year = rs.getString(5);
		num_of_votes = rs.getString(6);
		director = rs.getString(7);
		writer = rs.getString(8);
		company = rs.getString(9);
		descriptions = rs.getString(10);	
	}

%>

<div class="box">
<table class="table table-bordered">
	<tr>
	<td>Title</td>
	<td>
		<form action="mupdate/titleUpdate.jsp" method="get">
			<input type="hidden" name="movieID" value=<%=movieID%>/> 
			<input class="form-control" type="input" name="titleInput" id = "titleInput" value="<%=title == null? "":title%>" disabled>
			<input class="btn btn-primary" type="submit" name = "titleButton" value="modify" disabled>
			<input type="checkbox" name="title" onClick="titleCheck(this.form)">
		</form>
	</td>
	</tr>
	
	<tr>
	<td>Type  <p style="font-size: 11px"/>Mtype : movie / knuOriginal / tvSeries</td>
	<td>
		<form action="mupdate/mtypeUpdate.jsp"  method="get">
			<input class="form-control" type="hidden" name="movieID" value=<%=movieID%>/>
			<input class="form-control" type="input" name="mtypeInput" id = "mtypeInput" value="<%=mtype == null? "":mtype%>" disabled>
			<input class="btn btn-primary" type="submit" name = "mtypeButton" value="modify" disabled>
			<input type="checkbox" name="title" onClick="titleCheck(this.form)">
		</form>
	</td>
	</tr>
	
	<tr>
	<td>Runtime <p style="font-size: 11px"/>minute unit </td>
	<td>
		<form action="mupdate/runTimeUpdate.jsp"  method="get">
		<input class="form-control" type="hidden" name="movieID" value=<%=movieID %>/>
		<input class="form-control" type="input" name="runTimeInput" id = "runTimeInput" value="<%=runtime == null? "":runtime%>" disabled>
		<input class="btn btn-primary"  type="submit" name = "runTimeButton" value="modify" disabled>
		<input type="checkbox" name="runTime" onClick="runTimeCheck(this.form)">
		
		</form>
	</td>
	</tr>
	
	<tr>
	<td>Start Year <p style="font-size: 11px"/>format : yyyy-dd-mm</td>
	<td>
		<form action="mupdate/startYearUpdate.jsp"  method="get">
		<input class="form-control" type="hidden" name="movieID" value=<%=movieID %>/>
		<input class="form-control" type="date" name ="startYearInput" value="<%=start_year == null? "":start_year.split(" ")[0]%>" disabled>
		<input  class="btn btn-primary" type="submit" name = "startYearButton" value="modify" disabled>
		<input type="checkbox" name="startYear" onClick="startYearCheck(this.form)">
		
		</form>

	</td>
	</tr>
	
	<tr>
	<td>End Year <p style="font-size: 11px"/>format : yyyy-dd-mm</td>
	<td>
		<form action="mupdate/endYearUpdate.jsp" method="get"> 
		<input class="form-control" type="hidden" name="movieID" value=<%=movieID %>/>
		<input class="form-control" type="date" name ="endYearInput" value="<%=end_year == null? "":end_year.split(" ")[0]%>" disabled>
		<input  class="btn btn-primary" type="submit" name = "endYearButton" value="modify" disabled>
		<input type="checkbox" name="endYear" onClick="endYearCheck(this.form)">
		</form>
	</td>
	</tr>
	
	<tr>
	<td>Writer</td>
	<td>
		<form action="mupdate/writerUpdate.jsp" method="get">
		<input class="form-control" type="hidden" name="movieID" value=<%=movieID %>/>
		<input class="form-control" type="input" name="WriterInput" value="<%=writer == null? "":writer%>" disabled>
		<input class="btn btn-primary" type="submit" name = "writerButton" value="modify" disabled>
		<input type="checkbox" name="Writer" onClick="WriterCheck(this.form)">
		</form>
	</td>
	</tr>
	
	<tr>
	<td>Director</td>
	<td>
		<form action="mupdate/directorUpdate.jsp" method="get">
		<input class="form-control"  type="hidden" name="movieID" value=<%=movieID %>/>
		<input class="form-control"  type="input" name="directorInput" value="<%=director == null? "":director%>" disabled>
		<input class="btn btn-primary" type="submit" name = "directorButton" value="modify" disabled>
		<input type="checkbox" name="director" onClick="directorCheck(this.form)">
		</form>
	</td>
	</tr>
	<tr>
	<td>Vote</td>
	<td>
		<form action="mupdate/voteUpdate.jsp" method="get">
		<input type="hidden" name="movieID" value=<%=movieID %>/>
		<input class="form-control"  type="input" name="voteInput" value="<%=num_of_votes == null? "":num_of_votes%>" disabled>
		<input class="btn btn-primary" type="submit" name = "voteButton" value="modify" disabled>
		<input type="checkbox" name="vote" onClick="voteCheck(this.form)">
		</form>
	</td>
	</tr>
	
	
	<tr>
	<td>Company</td>
	<td>
		<form action="mupdate/companyUpdate.jsp" method="get">
		<input type="hidden" name="movieID" value=<%=movieID %>/>
		<input class="form-control"  type="input" name="companyInput" value="<%=company == null? "":company%>" disabled>
		<input class="btn btn-primary" type="submit" name = "companyButton" value="modify" disabled>
		<input type="checkbox" name="company" onClick="companyCheck(this.form)">
		</form>	
	</td>
	</tr>
	
	
	<tr>
	<td>Description</td>
	<td>
		<form action="mupdate/descriptionUpdate.jsp" method="get">
		<input type="hidden" name="movieID" value=<%=movieID %>/>
		<br/><input type="input" class="form-control"  style="height: 40px; width: 500px;'" name="descriptionInput" value="<%=descriptions == null? "":descriptions%>" disabled>
		<input class="btn btn-primary" type="submit" name = "descriptionButton" value="modify" disabled>
		<input type="checkbox" name="description" onClick="descriptionCheck(this.form)">
		</form>

	</td>
	</tr>
	
	
</table>

<br/><input class="btn btn-primary" type="button" name = "back" id = "back" onclick="location.href='ShowDetail.jsp'" value="BACK">
</div>
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

</body>
</html>