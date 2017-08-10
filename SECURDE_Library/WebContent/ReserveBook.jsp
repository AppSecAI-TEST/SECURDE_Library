<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="components/headers.jsp" />
</head>
<body>

	<jsp:include page="components/navbar.jsp" />

	<div class="container">
		<div class="card col-md-9 col-md-offset-2">
			<div class="card-block card-space">
				<div class="container">
					<div class="row">
						<div class="col-md-9">
							<div class="thumbnail">
								<div class="caption">
									<div class="btn-group cart pull-right"></div>
									<h4>${book.title}</h4>
									<h5>Author: ${book.author}</h5>
									<h5>Publisher: ${book.publisher}</h5>
									<h6>Year Published: ${book.year}</h6>
									<h6>
										Type:
										<c:choose>
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
									<p>
										Deadline:
										<fmt:formatDate value="${reservation.deadline.time}"
											type="date" dateStyle="short" />
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>