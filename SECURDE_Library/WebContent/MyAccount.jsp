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
		
		$('#npwd').strengthMeter('text', {
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

			var pass = $('#npwd').val();
			var conPass = $('#cpwd').val();
			var score = new Score(pass);

			var scoreValue = score.calculateEntropyScore();
			
			var a = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$/.test(pass);
			
			var PasswordCheck = false;
			var conPassCheck = false;
				
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
			
			
			if(PasswordCheck && conPassCheck){
				$('#change-pass-form').submit();
			}else{

				console.log(PasswordCheck);
				console.log(conPassCheck);
			}
			
			
		});

	});
</script>

<body>

	<jsp:include page="components/navbar.jsp" />


	<div id="change-pass" class="col-md-6 col-md-offset-3">

		<form id="change-pass-form" method="post" action="change_pass">
			<div class="form-group">
				<label for="old_pwd">Old Password:</label> <input type="password"
					class="form-control" id="opwd" name="oldpassword">
			</div>
			<div class="form-group">
				<label for="new_pwd">New Password:</label> <input type="password"
					class="form-control" id="npwd" name="newpassword">
					 <div class="col-sm-12" id="pwd-error" style="font-weight:bold;padding:6px 12px;color:red;"></div>
			</div>
			<div class="form-group">
				<label for="new_pwd">Password Strength:</label> <div class="col-sm-6" id="example-text-hierarchy-text" style="font-weight:bold;padding:6px 12px;"></div>
			</div>
 
			<p id="password-error"></p>
			<div class="form-group">
				<label for="cpwd">Confirm Password:</label> <input type="password"
					class="form-control" id="cpwd">
					 <div class="col-sm-12" id="cpwd-error" style="font-weight:bold;padding:6px 12px;color:red;"></div>
			</div>
			
			<button type="button" id="cancel"
				class="btn btn-default col-md-4 col-md-offset-2 btn-space">Cancel</button>
			<button type="button" id="confirm" class="btn btn-success col-md-4 btn-space">Save Changes</button>
		</form>
	</div>



</body>
</html>
