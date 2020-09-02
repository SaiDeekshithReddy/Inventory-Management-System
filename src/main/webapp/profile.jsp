<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.virtusa.demo.bean.Users" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>EMart</title>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<style>
	td {
	  text-align: left;
	  padding: 10px;
	}
	</style>
</head>
<body >
  <%@include file="toppage.jsp" %>
  <form action="change-password" method="post">
	<table style="float:center">
	  <% Users user = (Users)session.getAttribute("user"); %>
	  <tr>
	    <td>Id : </td>
	    <td><input type="text" value=<%= user.getId() %> readonly/></td>
	  </tr>
	  <tr>
	    <td>Name : </td>
	    <td><input type="text" value=<%= user.getName() %> readonly/></td>
	  </tr>
	  <tr>
	    <td>Role : </td>
	    <td><input type="text" value=<%= user.getRole() %> readonly/></td>
	  </tr>
	  <tr>
	    <td>Old Password : </td>
	    <td><input type="text" name="old-password"/></td>
	  </tr>
	  <tr>
	    <td>New Password : </td>
	    <td><input type="text" name="new-password" /></td>
	  </tr>
	</table>
	<div style="color:green">${change_password_msg}</div>
	<div style="color:red">${change_password_msgerror}</div>
	<input type="submit" value="change password" />
  </form>
  <form action="logout" method="post">
    <input type="submit" value="logout" />
  </form>
</body>
</html>