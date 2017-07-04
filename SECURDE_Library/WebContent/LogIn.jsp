<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>DLSU-LIB : Sign in</title>

	<jsp:include page="components/headers.jsp" />
</head>
<style>
#sign-in {
	margin-top: 15px;
	padding: 20px;
	border-radius: 10px;
	background-color: #e0e0e0;
}
</style>

<script type="text/javascript" src="js/datepicker.js"></script>

<script>
	$(document).ready(function() {
		$('#submit').click(function() {
			$('#sign-in-form').submit();
		});

	});
</script>

<body>

	<jsp:include page="components/navbar.jsp" />


	<div id="sign-in" class="col-md-6 col-md-offset-3">

		<form id="sign-in-form" method="post" action="login">
			<div class="form-group">
				<label for="name">Username:</label> <input type="text"
					class="form-control" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" name="password">
			</div>
			<button id="submit" class="btn btn-success col-md-4 col-md-offset-4">Log In</button>
		</form>
	</div>



</body>
</html>
