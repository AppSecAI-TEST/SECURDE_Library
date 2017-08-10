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
					<div class="commenterName">
					   <p class="">anon1234</p>
					</div>
					<div class="commentText">
						<p class="">Hello this is a test bad review.</p>
					</div>
				</li>
			</ul>
			<form action="addreview" method="post" id="add-review-form" >
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