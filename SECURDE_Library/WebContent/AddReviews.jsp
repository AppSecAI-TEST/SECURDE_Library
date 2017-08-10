<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="components/headers.jsp" />
</head>
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
			<form class="form-inline" role="form">
				<div class="form-group">
					<input class="form-control" type="text" placeholder="Your comments" />
				</div>
				<div class="form-group">
					<button class="btn btn-default">Add</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>