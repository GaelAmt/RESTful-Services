<!DOCTYPE html>
<html>
<head>
<title>Create a new movie theater</title>
</head>
<body>
	<h4>If one input is empty, the theater might not be added in the
		database</h4>
	<form action="../restful/restful-api/movieTheaters" method="POST">
		<label for="name">Name of the movie theater</label> <input name="name" />
		<br /> <label for="location">Location</label> <input name="location" />
		<br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>