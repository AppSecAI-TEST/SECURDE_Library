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
	<div id="borrow-books" class="col-md-8 col-md-offset-2">

		<form class="content row" action="search_book">
			<div class="col-md-9">
				<input type="text" placeholder="Keyword" class="form-control"
					id="keyword" name="keyword">
			</div>
			<button type="submit" class="btn btn-success col-md-2 ">Search</button>
		</form>

	</div>


	<div id="advanced-search" class="col-md-2 col-md-offset-5">
		<!-- 
		<a href="AdvancedSearch.html" class="link">Advanced Search</a>
		 -->
	</div>
	<c:forEach items="${booklist}" var="book">
		<div class="card col-md-9 col-md-offset-2">
			<div class="card-block card-space">
				<div class="container">
					<div class="row">
						<div class="col-md-9">
							<div class="thumbnail">
								<div class="caption">
									<div class="btn-group cart pull-right">
										<form action="book_detail" method="get">
											<input type="text" name="idBooks" value="${book.idBooks}"
												class="invisible" />
											<button type="submit" class="btn btn-success">Details
											</button>
										</form>
										<form action="book_detail" method="get">
											<input type="text" name="idBooks" value="${book.idBooks}"
											    class="invisible"/>
											<button type="submit" class="btn btn-sucess">View Reviews</button>
										</form>
									</div>
									<h4>
										${book.title}
									</h4>
									<h5>
										Author: ${book.author}
									</h5>
									<h5>
										Publisher: ${book.publisher}
									</h5>
									<h6>
										Year Published: ${book.year}
									</h6>
									<h6>
										Type: <c:choose>
												<c:when test="${book.type==0}">
												Book
											</c:when>
												<c:when test="${book.type==1}">
												Magazine
											</c:when>
												<c:when test="${book.type==2}">
												Thesis
											</c:when>
												<c:otherwise>
												Unknown
											</c:otherwise>
											</c:choose>
										
									</h6>
								</div>
								<div class="caption">
									<p>
										Status:
										<c:choose>
											<c:when test="${book.status==0}">
												Available
											</c:when>
											<c:otherwise>
												Reserved
											</c:otherwise>
										</c:choose>
									</p>
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
