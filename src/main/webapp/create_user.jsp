<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>EMart</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<style>
	td {
	  text-align: left;
	  padding: 10px;
	}
	</style>
</head>
<body>
  <div class="container-fluid " >
	<div class="row">
	  <%@include file="user.jsp" %>
	  <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh; border-spacing:25px;" >
	    <h3 style="text-align:center">Create User</h3>
		  <form action = "add_user" method ="post">
			<table style="border-spacing:25px; justify-content: space-around;" >
				<tr><td>User Id : </td><td><input type="text" name="user-id" required/></td></tr>
				<tr><td>Name : </td><td><input type="text" name="user-name" required/></td></tr>
				<tr><td>Password : </td><td><input type="text" name="user-password" required/></td></tr>
				<tr><td>Role : </td><td><input type="text" name="user-role" required/></td></tr>
			</table>
			<div style="color:red;">${user_created_msg_error}</div>
			<div style="color:green;">${user_created_msg}</div>
			<br><input type="submit" value="submit" />
		  </form>
	  </div>
	</div>
  </div>
</body>
</html>