<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*, java.sql.*, km.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMP322</title>
<link rel="stylesheet" href="./css/basic.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>			
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom:30px">
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

<form action="SearchResult.jsp" method="POST">
	
	<div class="box" >
		<div class="form-inline my-14 my-lg-0">
	      <input class="form-control mr-sm-2" type="search" name="search_data" placeholder="Search" aria-label="Search">
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	    </div>
	</div>
	<div class="box">
		<br/><br>
	<h4>Type</h4>
	<div>
		<div class="form-check form-check-inline">
		<label class="form-check-label" ><input class="form-check-input" type="checkbox" id="movie" name="mtypes" value="movie"/>movie</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label" ><input class="form-check-input" type="checkbox" id="tvSeries" name="mtypes" value="tvSeries"/>tvSeries</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label" ><input class="form-check-input" type="checkbox" id="knuOriginal" name="mtypes" value="knuOriginal"/>knuOriginal</label>
		</div>
	</div>
	
	<h4>Genre</h4>
	<div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="Action" name="genres" value="Action"/>Action</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="Comedy" name="genres" value="Comedy"/>Comedy</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="Romance" name="genres" value="Romance"/>Romance</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="Thiller" name="genres" value="Thiller"/>Thiller</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="Sci-Fi" name="genres" value="Sci-Fi"/>Sci-Fi</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="Fantasy" name="genres" value="Fantasy"/>Fantasy</label>
		</div>
	</div>
	
	<h4>version</h4>
	<div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="UA" name="versions" value="UA"/>UA</label>
		</div>
		<div class="form-check form-check-inline">	
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="DE" name="versions" value="DE"/>DE</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="KR" name="versions" value="KR"/>KR</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="ES" name="versions" value="ES"/>ES</label>
		</div>
		<div class="form-check form-check-inline">
		<label class="form-check-label"><input class="form-check-input" type="checkbox" id="FR" name="versions" value="FR"/>FR</label>
		</div>
	</div>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	conn = Util.makeConnection();
%>
	<hr>
	<h4>Top 5 movie</h4>

<%
	String sql = "create view avg_rating as " + 
			"select movie_id, avg(cast(stars as DECIMAL(10,2))) as ravg " +
			"from rating " +
			"group by movie_id " +
			"order by ravg DESC";

	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.execute(); // make a view for top 5 movie
	} catch (SQLException ex2) {
		System.err.println("stmt error = " + ex2.getMessage());
	}
	
	/* show top 5 movies */
	try {
		sql = "select distinct m.id, m.title, m.mtype, t.ravg " +
				"from avg_rating t, movie m, genre_of g " +
				"where t.movie_id = m.id and g.movie_id = t.movie_id " +
				"and not exists ( select r.rating_id  from rating r " +
				"where r.movie_id = m.id and r.account_id ='" + userID + "') " +
				"order by ravg desc";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		out.println("<table class='table table-bordered'>");
		ResultSetMetaData rsmd = rs.getMetaData();
		int ccnt = rsmd.getColumnCount();
		for (int i = 2; i <= ccnt; ++i) {
			out.println("<th>" + rsmd.getColumnName(i) + "</th>");
		}
		
		int cnt = 0;
		while (rs.next()) {
			if (cnt++ == 5) break;
			out.println("<tr onclick='onClickHandler(" + rs.getString(1) + ")' onmouseover=\"changeColor(this, '#FFFFFF', '#008ee2')\">");
			out.println("<td>" + rs.getString(2) + "</td>");
			out.println("<td>" + rs.getString(3) + "</td>");
			out.println("<td>" + rs.getFloat(4) + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
%>
	<hr>
	<h4>Top 5 Recommendations </h4>
<%
	/* show top 5 movies */
	try {
		sql = "drop view favor";
		pstmt = conn.prepareStatement(sql);
		pstmt.execute();	
	} catch (SQLException ex2) {
		
	}
	
	
	try {
		sql = "create view favor as " +
				  "select mtype, genre, runtime " +
				  "from account_favorite " +
				  "where id =" + "'" + userID + "'";
		
		pstmt = conn.prepareStatement(sql);
	
		pstmt.execute(); // make a view for top 5 movie
	} catch (SQLException ex2) {
		System.err.println("pstmt error = " + ex2.getMessage());
	}

	try {				
		sql = "select distinct m.id, m.title, m.mtype, t.ravg " +
				"from avg_rating t, genre_of g, movie m, favor f " +
				"where t.movie_id = g.movie_id and t.movie_id = m.id and " +
				"g.GENRE_NAME = f.genre and m.mtype=f.mtype and " +
				"m.runtime between f.runtime - 20 and f.runtime + 20 " +
				"and not exists ( select r.rating_id  from rating r " +
				"where r.movie_id = m.id and r.account_id ='" + userID + "') " +
				"order by t.ravg desc";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		out.println("<table class='table table-bordered'>");
		ResultSetMetaData rsmd = rs.getMetaData();
		int ccnt = rsmd.getColumnCount();
		for (int i = 2; i <= ccnt; ++i) {
			out.println("<th>" + rsmd.getColumnName(i) + "</th>");
		}
		
		int cnt = 0;
		while (rs.next()) {
			if (cnt++ == 5) break;
			out.println("<tr onclick='onClickHandler(" + rs.getString(1) + ")' onmouseover=\"changeColor(this, '#FFFFFF', '#008ee2')\">");
			out.println("<td>" + rs.getString(2) + "</td>");
			out.println("<td>" + rs.getString(3) + "</td>");
			out.println("<td>" + rs.getFloat(4) + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
%>
	

	
	</div>
	
	<script type="text/javascript">
	function onClickHandler(movieID) {
		document.location.href = "ShowDetail.jsp?movieID=" + movieID;
		
	}
	
	function changeColor(obj, oldColor, newColor) {
		obj.style.backgroundColor = newColor;
		obj.onmouseout = function(){
			obj.style.backgroundColor = oldColor;
		}
	}
</script>
</form>

</body>
</html>