<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
#nav-buttons {
	margin-top: 7px
}
</style>



<nav class="navbar navbar-inverse navbar-fixed-top">
<div class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="home">DLSU SHS Library</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<div class="navbar-right" id="nav-buttons">

			<%
				if ((int) request.getAttribute("loggedin") == -1) {
			%>

			<form action="login_page" method="post" id="nav_login"
				class="invisible"></form>

			<form action="signup_page" method="post" id="nav_signup"
				class="invisible"></form>
			<script>
				$(document).ready(function(){
					$('#nav-sign-up').click(function(){
						$('#nav_signup').submit();
					});
					$('#nav-sign-in').click(function(){
						$('#nav_login').submit();
					});
				});
				
			</script>
			<button id="nav-sign-up" class="btn btn-default">Sign up</button>
			<button id="nav-sign-in" class="btn btn-success">Log in</button>
			<%
				} else {
			%>

			<form action="logout" method="post" id="nav_logout"
				class="invisible"></form>

			<form action="myacc_page" method="post" id="nav_myacc"
				class="invisible"></form>
				
			<script>
				$(document).ready(function(){
					$('#nav-logout').click(function(){
						$('#nav_logout').submit();
					});
					$('#nav-myacc').click(function(){
						$('#nav_myacc').submit();
					});
				});
				
			</script>
			<button id="nav-myacc" class="btn btn-default">My Account</button>
			<button id="nav-logout" class="btn btn-default">Log out</button>
			<%
				}
			%>
		</div>
	</div>
	<!--/.navbar-collapse -->
</div>
</nav>