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
</head>
<style>
#borrow-books {
	margin-top: 2%;
	padding: 10px;
	border-radius: 10px;
	background-color: #e0e0e0;
}
</style>
<body>

	<jsp:include page="components/navbar.jsp" />

	<div class="card col-md-9 col-md-offset-2">
		<div class="card-block card-space">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="thumbnail">
							<div class="caption">
								
								<c:forEach items="${lockedusers}" var="locked_users">
								
									<h5> Name: ${locked_users.firstName} ${locked_users.lastName} </h5>
									<h4> Username: ${locked_users.userName}</h4>
								
									<c:if test="${loggedin != -1}">
								
												<form action="unlock_users" method="post">
													<input type="text" class="invisible" value="${locked_users.idUser}"
														name="idUser" />
													<button type="submit" class="btn btn-success">Unlock Account</button>
								
												</form>
											
								
									</c:if>
									<br><br>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
