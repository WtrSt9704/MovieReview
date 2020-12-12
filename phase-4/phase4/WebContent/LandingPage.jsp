<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMP322</title>

</head>
<body>

<a href="UploadMoviePage.jsp">Upload</a> <a href="">Search</a> <a href="MyPage.html">MyPage</a> <a href="">Logout</a> 
<hr>
<form action="SearchResult.jsp" method="POST">
	<input type="hidden" name="account_id" value="wewew2"/>
	<input type="hidden" name="account_pw" value="996052"/>
	
	Search: <input type="text" name="search_data"> <input type="submit" value="Search"/>
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
	
</form>

</body>
</html>