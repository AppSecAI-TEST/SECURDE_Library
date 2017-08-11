<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="components/headers.jsp" />
</head>
<script>
	$(document).ready(function() {
		$('#submit').click(function() {
			$('#add-review-form').submit();
		});

	});
</script>
<body>

	
	<jsp:include page="components/navbar.jsp" />

	
	<div class="detailBox col-md-9">
		<div class="titleBox">
			<label>Book Reviews</label>
			<button type="button" class="close" aria-hidden="true">&times;</button>
		</div>
		<div class="commentBox">
			<p class="taskDescription">Lorem Ipsum is simply dummy text of
				the printing and typesetting industry.</p>
		</div>
		<div class="actionBox">
			<ul class="commentList">
				<li>
					<div class="commenterImage">
						<img src="http://placekitten.com/50/50" />
					</div>
					<div class="commentText">
						<p class="">Hello this is a test bad review.</p>
						<span class="date sub-text">on March 5th, 2014</span>

					</div>
				</li>
			</ul>
			<form  action="addreview" method="post" id="add-review-form"  class="form-inline" role="form" >
				<div class="form-group">
					<input type="text" class="form-control" id="review" name="review" maxlength="450">
					<input type="text" class="invisible" name="idBooks" id="idBooks" value="${idBooks}"/>
				</div>
					<button type="submit" class="btn btn-default">Add</button>
			</form>
		</div>
	</div>
</body>
</html>