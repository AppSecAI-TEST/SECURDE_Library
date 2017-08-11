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

<jsp:include page="components/headers.jsp" />
<style>
#borrow-books {
	margin-top: 2%;
	padding: 10px;
	border-radius: 10px;
	background-color: #e0e0e0;
}

#results-list {
	border-color: #333;
}
</style>
<body>

	<jsp:include page="components/navbar.jsp" />
	<c:forEach items="${reviewlist}" var="review">
		<div class="card col-md-9 col-md-offset-2">
			<div class="card-block card-space">
				<div class="container">
					<div class="row">
						<div class="col-md-9">
							<div class="thumbnail">
								<div class="caption">
									<div class="btn-group cart pull-right">
										<form action="review_detail" method="get">
											<input type="text" name="idBooks" value="${book.idBooks}"
												class="invisible" />
										</form>
									</div>
									<h4>
										${book.title}
									</h4>
									<h5>
										 ${user.username}
									</h5>
									<h5>
										Comment: ${review.review}
									</h5>
									<h6>
										Date: ${review.create_time}
									</h6>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</c:forEach>



</body>
</html>
