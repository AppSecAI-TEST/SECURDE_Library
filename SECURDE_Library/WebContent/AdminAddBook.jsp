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

<jsp:include page="components/headers.jsp"/>
</head>
<style>
#editbook{
	margin-top:10px;
	padding:20px;
	border-radius: 10px;
	background-color: #e0e0e0;
}

</style>

<script>
	$(document).ready(function() {
		$('#submit').click(function() {
			$('#add-book-form').submit();
		});

	});
</script>
<body>

<jsp:include page="components/navbar.jsp"/>

	<div id="editbook" class="col-md-6 col-md-offset-3">
		
		<form action="addbook" method="post" id="add-book-form" >
		
			<div class="form-group">
				<label for="type">Type:</label>
				<div>
					<select class="selectpicker" id="booktype" name="type">
						<option>Book</option>
						<option>Magazine</option>
						<option>Thesis</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="title">Book Title</label> <input type="text"
					class="form-control" name="book_title" id="booktitle">
			</div>
			<div class="form-group">
				<label for="author">Author:</label> <input type="text"
					class="form-control" name="author" id="auth">
			</div>
			<div class="form-group">
				<label for="publisher">Publisher:</label> <input type="text"
					class="form-control" name="publisher" id="pub">
			</div>
			<div class="form-group">
				<label for="year">Published Year:</label> <input type="number"
					class="form-control" name="year" id="pubyear">
			</div>
			<div class="form-group">
				<label for="location">Location:</label> <input type="number"
					class="form-control" placeholder="Enter in a Dewey decimal format. (e.g. 123.4)" name="location" id="loc">
			</div>
			
			<div class="form-group">
				<label for="tags">Tags:</label> <input type="text"
					class="form-control" placeholder="Separate tags with comma (,)" name="tags" id="tag">
			</div>
			<button class="btn btn-default col-md-4 col-md-offset-2 btn-space">Cancel</button>
			<button type="submit" class="btn btn-success col-md-4 btn-space">Add</button>
		</form>
	</div>


</body>
</html>
