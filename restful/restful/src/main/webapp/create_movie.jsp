<!DOCTYPE html>
<html>
<head>
<title>Create a new movie</title>
</head>
<body>
	<h4>If one input is empty, the movie might not be added in the
		database</h4>
	<form action="../restful/restful-api/movies" method="POST">
		<label for="name">Name of the movie</label> <input name="name" /> <br />
		<label for="age">Age required (only integer)</label> <input name="age" />
		<br /> <label for="duration">Duration of the movie (in
			minutes)</label> <input name="duration" /> <br /> <label for="language">Language</label>
		<input name="language" /> <br /> <label for="subtitle">Language
			of the subtitles (type None if there isn't any)</label> <input
			name="subtitles" /> <br /> <label for="actor">Actors</label> <input
			name="actor" /> <br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>