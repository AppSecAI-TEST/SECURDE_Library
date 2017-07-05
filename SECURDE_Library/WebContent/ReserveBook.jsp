<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="components/headers.jsp"/>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

	<div class="container">
    	<h1 class="well">Reservation Form</h1>
		<div class="col-lg-12 well">
		<div class="row">
			<form>
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label><h4>Book Title Here</h4></label>
							</div>
							<div class="col-sm-6 form-group">
								<label>Author Here</label><br>
								<label>Publisher Here</label><br>
								<label>Published Year</label>
							</div>
						</div>					
						<div class="form-group">
							<label><h6>Book Description Here Hi hello</h6></label>
						</div>	
						<div class="row">
							<div class="col-sm-4 form-group">
								<label><h6>Reserved Date:</h6></label>
							</div>	
							<div class="col-sm-4 form-group" style="color: red">
								<label><h5>Return by "date here"</h5></label>
							</div>			
						</div>
						
						<button type="submit" class="btn btn-success pull-right">
						Reserve</button>
						<button type="submit" class="btn btn-danger pull-right" style="margin-right:15px;"> 
						Cancel </button>
						</div>
						
				</form> 
				</div>
	</div>
	</div>
</body>

</html>