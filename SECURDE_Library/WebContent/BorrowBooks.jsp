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

<jsp:include page="components/headers.jsp"/>
<style>
#borrow-books{
	margin-top:2%;
	padding:10px;
	border-radius: 10px;
	background-color: #e0e0e0;
}

#results-list{
	border-color: #333;
}

</style>
<body>

<jsp:include page="components/navbar.jsp"/>
	<div id="borrow-books" class="col-md-8 col-md-offset-2">
		
		<form class="content row">
			<div class="col-md-9">
			<input type="text" placeholder="Keyword"
					class="form-control" id="keyword">
			</div>
				<button type="submit" class="btn btn-success col-md-2 ">Search</button>		
		</form>
		
	</div>


	<div id="advanced-search" class="col-md-2 col-md-offset-5">
		<a href="AdvancedSearch.html" class="link">Advanced Search</a>
	</div>
	
	<div class="card col-md-9 col-md-offset-2">
  		<div class="card-block card-space" >
    		<div class="container">
				<div class="row">
					<div class="col-md-9">
              			<div class="thumbnail">
                			<div class="caption">
                			<div class="btn-group cart pull-right">
								<button type="button" class="btn btn-success">
									Details
								</button>
							</div>
                  					<h4><a href="#">Book Title</a></h4>
                  					<h5><a href="#">Author</a></h5>
                  					<h5><a href="#">Publisher</a></h5>
                  					<h6><a href="#">Brief Description</a></h6>
                  			</div>
                			<div class="caption">
                  				<p>Status: Available </p>	
                			</div>
              			</div>
            		</div>
  				</div>
  			</div>
  		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
