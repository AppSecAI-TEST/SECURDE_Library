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

<title>SHS Library: Sign Up</title>

	<jsp:include page="components/headers.jsp" />
	<script type="text/javascript" src="strength-meter/password-score/dist/js/password-score.js"></script>
<script type="text/javascript" src="strength-meter/password-score/dist/js/password-score-options.js"></script>
<script type="text/javascript" src="strength-meter/dist/js/bootstrap-strength-meter.js"></script>

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
		
		$('#pwd').strengthMeter('text', {
            container: $('#example-text-hierarchy-text'),
            hierarchy: {
                '0': ['text-danger', 'ridiculous'],
                '10': ['text-danger', 'very weak'],
                '20': ['text-warning', 'weak'],
                '30': ['text-warning', 'good'],
                '40': ['text-success', 'strong'],
                '50': ['text-success', 'very strong']
            }
        });
		
		$('#confirm').click(function() {
			

			var pass = $('#pwd').val();
			var conPass = $('#cpwd').val();
			var score = new Score(pass);

			var scoreValue = score.calculateEntropyScore();
			
			var a = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/.test(pass);
			
			var PasswordCheck = false;
			var conPassCheck = false;
			var dataCheck = false;
			var emailCheck = false;
			
			var email = $('#email').val();
			var emailC = (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(email);

			if(emailC){
				$('#email-error').text("");
				emailCheck = true;
			}else{

				$('#email-error').text("Email address is not valid.");
				emailCheck = false;
			}
			
			if(pass.length < 8){
				$('#pwd-error').text("Password should have at least 8 characters.");
				PasswordCheck= false;
			}else if(!a){
				$('#pwd-error').text("Password should have at least 1 uppercase letter, lowercase letter, digit and special character.");
				PasswordCheck= false;
			}else if(scoreValue < 20){
				$('#pwd-error').text("Password too weak. Try making a longer password.");
				PasswordCheck= false;
			}else{
				$('#pwd-error').text("");	
				PasswordCheck= true;
			}	
			if(conPass != pass){
					$('#cpwd-error').text("Password does not match.");
					conPassCheck = false;
			}else{
				$('#cpwd-error').text("");
				conPassCheck = true;
			}
			
			if($('#user_id').val()=="" ||
					$('#first_name').val()=="" ||
					$('#mid_name').val()=="" ||
					$('#last_name').val()=="" ||
					$('#username').val()=="" ||
					$('#birthdate').val()=="" ||
					$('#question').val()=="" ||
						$('#answer').val()=="" ){
				$('#gen-error').text("All fields must be filled.");	
				dataCheck = false;
			}else{
				$('#gen-error').text("");	
				dataCheck = true;
			}
			
			if(PasswordCheck && conPassCheck && dataCheck && emailCheck){
				$('#sign-in-form').submit();
			}else{
				console.log(PasswordCheck);
				console.log(conPassCheck);
				console.log(dataCheck);
				console.log(emailCheck);
			}
			
			
		});

	});
</script>

<body>

	<jsp:include page="components/navbar.jsp" />


	<div id="sign-in" class="col-md-6 col-md-offset-3">

		<form id="sign-in-form" method="post" action="new_user">
			<div class="form-group">
				<label for="user_id">ID #:</label> <input type="number"
					class="form-control" id="user_id" name="userid">
			</div>
			<div class="form-group">
				<label>Status</label>
				<div>
					<select class="selectpicker" id="status" name="access_level">
						<option>Student</option>
						<option>Faculty</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="first_name">First Name:</label> <input type="text"
					class="form-control" id="first_name" name="firstname">
			</div>
			<div class="form-group">
				<label for="mid_name">Middle Name:</label> <input type="text"
					class="form-control" id="mid_name" name="middlename">
			</div>
			<div class="form-group">
				<label for="last_name">Last Name:</label> <input type="text"
					class="form-control" id="last_name" name="lastname">
			</div>
			<div class="form-group">
				<label>Birthdate</label>
				<div>
					<div class="input-group input-append date" id="datePicker">
						<input type="text" class="form-control" name="birthdate" id="birthdate" /> <span
							class="input-group-addon add-on"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="name">Username:</label> <input type="text"
					class="form-control" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="email">Email address:</label> <input type="email"
					class="form-control" id="email" name="email">
					 <div class="col-sm-12" id="email-error" style="font-weight:bold;padding:6px 12px;color:red;">
			</div>
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" name="password">
					 <div class="col-sm-12" id="pwd-error" style="font-weight:bold;padding:6px 12px;color:red;">
			</div>
			</div>
						<div class="form-group">
				<label for="pwd">Password Strength:</label> <div class="col-sm-6" id="example-text-hierarchy-text" style="font-weight:bold;padding:6px 12px;">
			</div>
			</div>
 
			<p id="password-error"></p>
			<div class="form-group">
				<label for="cpwd">Confirm Password:</label> <input type="password"
					class="form-control" id="cpwd">
					 <div class="col-sm-12" id="cpwd-error" style="font-weight:bold;padding:6px 12px;color:red;">
			</div>
			</div>
			<div class="form-group">
				<label for="question">Secret Question:</label> <input type="text"
					class="form-control" id="question" name="secret_question">
			</div>
			<div class="form-group">
				<label for="answer">Secret Answer:</label> <input type="password"
					class="form-control" id="answer" name="secret_answer">
			</div>
			 <div class="col-sm-12" id="gen-error" style="font-weight:bold;padding:6px 12px;color:red;">
			</div>
			<button type="button" id="cancel"
				class="btn btn-default col-md-4 col-md-offset-2 btn-space">Cancel</button>
			<button type="button" id="confirm" class="btn btn-success col-md-4 btn-space">Done</button>
		</form>
	</div>



</body>
</html>
