<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>EMart</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="table_style.css"/>
</head>
<body>
  <div class="container-fluid " >
	<div class="row">
	  <%@include file="user.jsp" %>
	  <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;">
	    <h3 style="text-align:center">Delete User</h3>
		<form action = "remove_user" method ="post">
			User Id : <input type="text" name="user-id" required/><br><br>
			<div style="color:red;">${user_deleted_msg_error}</div>
			<div style="color:green;">${user_deleted_msg}</div>
			<input type="submit" value="submit" />
		</form>
	  </div>
    </div>
  </div>
</body>
</html>