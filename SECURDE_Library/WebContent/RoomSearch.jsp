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
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">

<!-- SELECT -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

<!-- SLECT -->


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

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">DLSU SHS Library</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" placeholder="Email" class="form-control">
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control">
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	<div class="col-md-10 col-md-offset-1">
		<div id="searchRooms" class="col-md-3">

			<form id="searchForm" method="post" class="form-horizontal">

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
				<div class="form-group col-md-12">
					<label>Start Time (0000-2359)</label>
					<div>
						<input type="text" class="form-control" name="name" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label>End Time (0000-2359)</label>
					<div>
						<input type="text" class="form-control" name="name" />
					</div>
				</div>
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

				<button type="submit"
					class="btn btn-success col-md-4 col-md-offset-4">Search</button>
			</form>
		</div>

		<div id="results" class="col-md-8 clearfix">
			<div>
				<h2>Room Reservation > Results</h2>
				<div class="list-group">
					<a href="#" class="list-group-item">Meeting Room 1</a>
					<a href="#" class="list-group-item">Meeting Room 2</a>
					<a href="#"	class="list-group-item">Thesis Room 1</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
