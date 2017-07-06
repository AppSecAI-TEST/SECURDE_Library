<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<title>DLSU-LIB : Room Reservations</title>

<jsp:include page="components/headers.jsp" />


</head>
<style>
#searchRooms {
	margin: 10px;
	margin-top: 20px;
	margin-right: 10px;
	padding: 20px;
	padding-right: 0px;
	border-radius: 10px;
	background-color: #e0e0e0;
}

#results {
	margin-left: 20px;
	margin-right: 10px;
	padding: 10px;
}
</style>
<script type="text/javascript" src="js/datepicker.js"></script>

<body>

	<jsp:include page="components/navbar.jsp" />

	<div class="col-md-10 col-md-offset-1">
		<div id="searchRooms" class="col-md-3">

			<form id="searchForm" method="post" class="form-horizontal">
				<!-- 
				<div class="form-group col-md-12">
					<label>Date</label>
					<div>
						<div class="input-group input-append date" id="datePicker">
							<input type="text" class="form-control" name="date" /> <span
								class="input-group-addon add-on"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
				 -->
				<div class="form-group col-md-12">
					<label>Start Time (0600-2100)</label>
					<div>
						<input type="number" class="form-control" name="start" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label>End Time (0700-2200)</label>
					<div>
						<input type="number" class="form-control" name="end" />
					</div>
				</div>
				<!-- 
				<div class="form-group col-md-12">
					<label>Floor</label>
					<div>
						<select class="selectpicker col-xs-12">
							<option>All</option>
							<option>7th Floor</option>
							<option>8th Floor</option>
							<option>9th Floor</option>
							<option>10th Floor</option>
							<option>11th Floor</option>
							<option>12th Floor</option>
						</select>
					</div>
				</div>
 -->
				<button type="submit"
					class="btn btn-success col-md-4 col-md-offset-4">Search</button>
			</form>
		</div>

		<div id="results" class="col-md-8 clearfix">
			<div>
				<h2>Room Reservation > Results</h2>
				<div class="list-group">

					<c:forEach items="${rooms}" var="room">
						<form action="getroom" method="get">
							<input type="text" class="invisible" value="${room.idRooms}"
								name="idRooms" />
							<button type="submit">${room.name}</button>
						</form>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
