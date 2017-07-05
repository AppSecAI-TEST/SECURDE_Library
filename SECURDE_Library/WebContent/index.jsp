<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

  <body>

	<jsp:include page="components/navbar.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Hello, Lasallians!</h1>
        <p>Welcome to the new library system that will make it easier for library patrons like you to reserve books and meeting rooms. Sign up now!</p>
        <p><a class="btn btn-primary btn-lg" href="SignUp.html" role="button">Sign Up &raquo;</a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-6">
          <h2>Borrow Books</h2>
          <p>The main library, called The Learning Commons, is home to 434,720 volumes of book accessions, 31,309 volumes of thesis & dissertations, and more than 29,000 volumes of other printed materials. </p>
          <p><a class="btn btn-default" href="search_book" role="button">Borrow &raquo;</a></p>
       </div>
        <div class="col-md-6">
          <h2>Reserve Rooms</h2>
          <p>Currently, The Learning Commons has 40 discussion rooms that are available and located on the 7th to the 12th floors. This serve as a venue for researchers to discuss and work together.</p>
          <p><a class="btn btn-default" href="search_room" role="button">Reserve &raquo;</a></p>
        </div>
      </div>

      <hr>

      <footer>
        <p>&copy; 2016 LDR Production</p>
      </footer>
    </div> <!-- /container -->


  </body>
</html>
