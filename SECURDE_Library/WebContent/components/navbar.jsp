<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
#nav-buttons{
	margin-top:7px
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
		<a class="navbar-brand" href="index.jsp">DLSU SHS Library</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<div class="navbar-right" id="nav-buttons">
			<button id="nav-sign-up" class="btn btn-default"
				onclick="location.href='SignUp.jsp'">Sign up</button>
			<button id="nav-sign-in" class="btn btn-success"
				onclick="location.href='LogIn.jsp'">Log in</button>
		</div>
	</div>
	<!--/.navbar-collapse -->
</div>
</nav>