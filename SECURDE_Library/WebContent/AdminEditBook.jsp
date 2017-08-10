<%@page import="java.util.ArrayList"%>
<%@page import="models.Tags"%>
<%@page import="java.util.List"%>
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
<jsp:include page="components/headers.jsp"></jsp:include>
</head>
<style>
#editbook {
	margin-top: 10px;
	padding: 20px;
	border-radius: 10px;
	background-color: #e0e0e0;
}
</style>
<script>
	$('document').ready(function() {

		$('#submit').click(function() {
			$('form#editbook').submit();
		});

	});
</script>

<body>

	<%
		String tagnames = "";
		List<Tags> tags = new ArrayList<Tags>();
		if (request.getAttribute("show_book_tags") instanceof ArrayList) {
			tags = (ArrayList<Tags>) request.getAttribute("show_book_tags");
		}
		for (int i = 0; i < tags.size(); i++) {
			tagnames += tags.get(i).getTag();
			if (i < tags.size() - 1) {
				tagnames += ",";
			}
		}
	%>

	<jsp:include page="components/navbar.jsp"></jsp:include>
	<div class="col-md-6 col-md-offset-3 clearfix">

		<form action="update_book" method="post" id="editbook">
			<input type="text" class="invisible" value="${show_book.idBooks}" name="idBooks"/>
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
						<option>Book</option>
						<option selected="selected">Magazine</option>
						<option>Thesis</option>
					</select>
				</c:if>

				<c:if test="${show_book.type eq 2 }">
					<select class="selectpicker" id="booktype" name="updated_type">
						<option>Book</option>
						<option>Magazine</option>
						<option selected="selected">Thesis</option>
					</select>
				</c:if>
			</div>
			<div class="form-group">
				<label for="status">Status:</label> <br>
				<c:if test="${show_book.status eq 0 }">
					<select class="selectpicker" id="bookstatus" name="updated_status">
						<option selected="selected">Available</option>
						<option>Reserved</option>
						<option>Out</option>
					</select>
				</c:if>

				<c:if test="${show_book.status eq 1 }">
					<select class="selectpicker" id="bookstatus" name="updated_status">
						<option>Available</option>
						<option selected="selected">Reserved</option>
						<option>Out</option>
					</select>
				</c:if>

				<c:if test="${show_book.status eq 2 }">
					<select class="selectpicker" id="bookstatus" name="updated_status">
						<option>Available</option>
						<option>Reserved</option>
						<option selected="selected">Out</option>
					</select>
				</c:if>
			</div>
			<div class="form-group">
				<label for="name">Book Title</label> <input type="text"
					value="${show_book.title}" class="form-control" id="booktitle"
					name="updated_title" required />
			</div>
			<div class="form-group">
				<label for="email">Author:</label> <input type="text"
					class="form-control" value="${show_book.author}"
					name="updated_author" id="auth">
			</div>
			<div class="form-group">
				<label for="pwd">Publisher:</label> <input type="text"
					class="form-control" value="${show_book.publisher}"
					name="updated_publisher" id="pub">
			</div>
			<div class="form-group">
				<label for="cpwd">Published Year:</label> <input type="text"
					class="form-control" value="${show_book.year}" name="updated_year"
					id="pubyear">
			</div>
			
			<div class="form-group">
				<label for="cpwd">Location:</label> <input type="text"
					class="form-control" value="${show_book.location}" name="updated_location"
					id="pubyear">
			</div>
			<div class="form-group">
				<label for="cpwd">Tags:</label> <input type="text"
					class="form-control" value="<%=tagnames%>" name="updated_tags"
					id="desc">
			</div>
			<button class="btn btn-default col-md-4 col-md-offset-2 btn-space">Cancel</button>
			<button type="submit" class="btn btn-success col-md-4 btn-space">Save</button>
		</form>
		<br>
		<form action="delete_book" method="post">
			<input type="text" class="invisible" value="${show_book.idBooks}" name="idBooks"/>
			<button type="submit" class="btn btn-danger col-md-offset-6 col-md-4">DELETE</button>
		</form>
	</div>


</body>
</html>
