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
	.button {
	  background-color: #4CAF50;
	  border: none;
	  color: white;
	  padding: 10px 10px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 16px;
	  margin: 5px 5px;
	  cursor: pointer;
	  float: left;
	  width: 100%;
	}
	</style>
</head>
<body>
  <%@include file="toppage.jsp" %>
  <div class="col-sm-2" style="background-color: lightgreen; min-height: 40vh;">
	<h3 style="text-align:center">Users</h3>
	<form action="create_user" method="post">
	  <input type = "submit" value="create user" class="button">
	</form>
	<form action="delete_user" method="post">
	  <input type = "submit" value="Delete user" class="button">
	</form>
  </div>
</body>
</html>