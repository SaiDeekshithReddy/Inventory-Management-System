<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<title>EMart</title>
	<style>
	.button1 {
	  background-color: #4CAF50;
	  border: none;
	  color: white;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 16px;
	  margin: 20px 10px;
	  cursor: pointer;
	  float: right;
	}
	</style>
</head>
<body>
<%
if(session.getAttribute("name") == null){
	response.sendRedirect("index");
}
%>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-12" style="background-color: yellow;">
      <h1 class="float-left">EMart</h1>
      <h1 class="float-right"><a href="profile">${name}</a></h1><br>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-12" style="background-color: yellow;">
      <form action="warehousepage" method="post"><input type="submit" name="warehouse" class="btn btn-success button1"  value="warehouse"></form>
      <form action="shelf" method="post"><input type="submit" name="shelf" class="btn btn-success button1"  value="shelf"></form>
      <% if( session.getAttribute("role").equals("admin")){ %>
	  <form action="user_left" method="post"><input type="submit" class="btn btn-success button1"  value="user"></form>
	  <% } %>
    </div>
  </div>
</div>
</body>
</html>