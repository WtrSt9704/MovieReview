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

<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
		<label><input type="checkbox" id="movie" name="mtypes" value="movie"/>movie</label>
		<label><input type="checkbox" id="tvSeries" name="mtypes" value="tvSeries"/>tvSeries</label>
		<label><input type="checkbox" id="knuOriginal" name="mtypes" value="knuOriginal"/>knuOriginal</label>
	</div>
	
	<h4>Genre</h4>
	<div>
		<label><input type="checkbox" id="Action" name="genres" value="Action"/>Action</label>
		<label><input type="checkbox" id="Comedy" name="genres" value="Comedy"/>Comedy</label>
		<label><input type="checkbox" id="Romance" name="genres" value="Romance"/>Romance</label>
		<label><input type="checkbox" id="Thiller" name="genres" value="Thiller"/>Thiller</label>
		<label><input type="checkbox" id="Sci-Fi" name="genres" value="Sci-Fi"/>Sci-Fi</label>
		<label><input type="checkbox" id="Fantasy" name="genres" value="Fantasy"/>Fantasy</label>
	</div>
	
	<h4>version</h4>
	<div>
		<label><input type="checkbox" id="UA" name="versions" value="UA"/>UA</label>	
		<label><input type="checkbox" id="DE" name="versions" value="DE"/>DE</label>
		<label><input type="checkbox" id="KR" name="versions" value="KR"/>KR</label>
		<label><input type="checkbox" id="ES" name="versions" value="ES"/>ES</label>
		<label><input type="checkbox" id="FR" name="versions" value="FR"/>FR</label>
	</div>
	
	<h4>Top 5 recommendations</h4>
	
	
	<h4>Top 5 movie</h4>
	
	</div>
	
	
	
	
</form>

</body>
</html>