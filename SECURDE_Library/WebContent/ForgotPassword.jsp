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

<title>Jumbotron Template for Bootstrap</title>

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
		var dataCheck = false;
		if($('#user_id').val()=="" ||
				$('#user_name').val()==""){
			$('#gen-error').text("All fields must be filled.");	
			dataCheck = false;
		}else{
			$('#gen-error').text("");	
			dataCheck = true;
		}
		
		if(dataCheck){
			$('#forget-pass-form').submit();
		}
		
		
	});

});
</script>

<body>

	<jsp:include page="components/navbar.jsp" />


	<div id="forget-pass" class="col-md-6 col-md-offset-3">
	<form id="forget-pass-form" method="post" action="secret_question">
			<div class="form-group">
				<label for="user_id">ID #:</label> <input type="number"
					class="form-control" id="user_id" name="userid">
			</div>
			<div class="form-group">
				<label for="user_name">UserName:</label> <input type="text"
					class="form-control" id="user_name" name="username">
			</div>
			<button type="button" id="cancel"
				class="btn btn-default col-md-4 col-md-offset-2 btn-space">Cancel</button>
			<button type="button" id="confirm" class="btn btn-success col-md-4 btn-space">Done</button>
		</form>
	</div>



</body>
</html>
