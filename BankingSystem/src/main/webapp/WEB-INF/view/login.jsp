<%@ page isELIgnored="false"%>
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
	<p><font color="red">${errorMessage}</font></p>
	<form action="/login.do" method="POST">
		Name : <input type="text" name="name"/> Password : <input name="password" type="password" /> <input type="submit" />
	</form>
</body>
</html>