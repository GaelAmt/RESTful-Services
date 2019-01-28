<!DOCTYPE html>
<html>
<head>
<title>Create a new Session</title>
</head>
<body>
	<h4>If one input is empty, the theater might not be added in the
		database</h4>
	<form action="../restful/restful-api/sessions" method="POST">
		<label for="theaterId">Id of the movie theater (only integer)</label>
		<input name="theaterId" /> <br /> <label for="movieName">Name
			of the movie</label> <input name="movieName" /> <br /> <label for="date">Date</label>
		<input name="date" /> <br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>