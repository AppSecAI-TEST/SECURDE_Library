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
								<div class="btn-group cart pull-right margin card-space">

									<br>
								</div>

							</div>
							<div class="caption">
									<h2>Room Name: ${room.name}</h2>
									<h3>Floor: ${room.floor}</h3>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="thumbnail">
							<div class="caption">
								
<c:forEach items="${roomslots}" var="roomslot">

	Status:
	<c:choose>
		<c:when test="${roomslot.status==0}">
												Available
											</c:when>
		<c:otherwise>
												Reserved
											</c:otherwise>
	</c:choose>
	<h5>Timeslot: ${roomslot.start_time} - ${roomslot.end_time}</h5>

	<c:if test="${loggedin != -1}">
	
		<c:choose>

			<c:when test="${roomslot.status==0}">
						<form action="room_reserve" method="post">
							<input type="text" class="invisible" value="${roomslot.idRoomSlot}"
								name="idRoomSlot" />
							<button type="submit" class="btn btn-success">Reserve</button>
		
						</form>
			</c:when>
			<c:otherwise>
					<c:choose>
						<c:when test="${override != null }">
							<form action="delete_reserve" method="post">
								<input type="text" class="invisible" value="${roomslot.idRoomSlot}"
									name="idRoomSlot" />
								<button type="submit" class="btn btn-danger">Delete Reservation</button>
			
							</form>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-disabled">Unavailable </button>
						</c:otherwise>
					</c:choose>
					
			</c:otherwise>
		</c:choose>

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
