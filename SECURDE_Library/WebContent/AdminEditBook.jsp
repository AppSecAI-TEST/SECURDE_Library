<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style>
#editbook{
	margin-top:10%;
	padding:20px;
	border-radius: 10px;
	background-color: #e0e0e0;
}

</style>
<script>
	$('document').ready(function(){
			
			$('#submit').click(function(){
				$('form#editbook').submit();
			});
			
		});
</script>
		
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">DLSU SHS Library</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" placeholder="Email" class="form-control">
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control">
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<div action="EditBookServlet" method="post" id="editbook" class="col-md-6 col-md-offset-3">
		
		<form>
		
		<div class="form-group">
				<label for="type">Type:</label> <br>
				<c:if test="${show_book.type eq 0 }">
					<select class="selectpicker" id="booktype" name="updated_type">
						<option selected="selected">Book</option>
						<option>Magazine</option>
						<option>Thesis</option>
					</select>
				</c:if>
				
				<c:if test="${show_book.type eq 1 }">
					<select class="selectpicker" id="booktype" name="updated_type">
						<option >Book</option>
						<option selected="selected">Magazine</option>
						<option>Thesis</option>
					</select>
				</c:if>
				
				<c:if test="${show_book.type eq 2 }">
					<select class="selectpicker" id="booktype" name="updated_type">
						<option >Book</option>
						<option>Magazine</option>
						<option selected="selected">Thesis</option>
					</select>
				</c:if>
			</div>
				<div class="form-group">
				<label for="name">Book Title</label> <input type="text" value="${show_book.title}" class="form-control" id="booktitle" name="updated_title" required/>
			</div>
			<div class="form-group">
				<label for="email">Author:</label> <input type="text"
					class="form-control" value="${show_book.author}" name="updated_author" id="auth">
			</div>
			<div class="form-group">
				<label for="pwd">Publisher:</label> <input type="text"
					class="form-control" value="${show_book.publisher}" name="updated_publisher" id="pub">
			</div>
			<div class="form-group">
				<label for="cpwd">Published Year:</label> <input type="text"
					class="form-control" value="${show_book.year}" name="updated_year" id="pubyear">
			</div>
			<div class="form-group">
				<label for="cpwd">Tags:</label> 
				String tagnames;
							<c:forEach var="t" items="${show_book_tags}">
								   tagnames+= "${t.tag}" + ",";
							</c:forEach>
					<input type="text" class="form-control" value=tagnames name="updated_tags" id="desc">
			</div>
			<button class="btn btn-default col-md-4 col-md-offset-2 btn-space">Cancel</button>
			<button type="submit" class="btn btn-success col-md-4 btn-space">Save</button>
		</form>
	</div>




	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
