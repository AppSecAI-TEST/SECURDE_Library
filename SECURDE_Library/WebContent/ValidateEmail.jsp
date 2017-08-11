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

<title>SHS Library: Email Validation</title>

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



<script>
	$(document).ready(function() {
		
	
		$('#confirm').click(function() {			
			
			$('#validate-email-form').submit();
			
		});

	});
</script>

<body>

	<jsp:include page="components/navbar.jsp" />


	<div id="validate-email" class="col-md-6 col-md-offset-3">

		<form id="validate-email-form" method="post" action="validate_email">
			<div class="form-group">
				<label for="email_code">Email Code:</label> <input type="password"
					class="form-control" id="email_code" name="emailcode">
			</div>
			<div class="form-group">
				<label for="pwd">Re-enter Password:</label> <input type="password"
					class="form-control" id="pwd" name="password">
			</div>
		
			<button type="button" id="confirm" class="btn btn-success col-md-4 btn-space">Confirm</button>
		</form>
	</div>



</body>
</html>
