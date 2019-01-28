<!DOCTYPE html>
<html>
<head>
<title>Search for session in a city</title>
</head>
<body>
	<h4>The city is case sensitive (Starts with an uppercase and only
		lowercase after)</h4>
	<form action="../restful/restful-api/sessions/city" method="GET">
		<label for="name">Name of the City</label> <input name="name" /> <br />
		<input type="submit" value="Search" />
	</form>
</body>
</html>