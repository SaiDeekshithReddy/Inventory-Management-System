<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap/js/bootstrap.min.js">
	<link rel="stylesheet" href="homestyle.css"/>
	<title>EMart</title>
</head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h1>Emart Login Page</h1>
    <!-- Login Form -->
    <form action="verify" method="post">
      <input type="text" id="login" class="fadeIn second" name="id" placeholder="User Id">
      <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
      <div style="color:red">${login_errormsg}</div>
      <input type="submit" class="fadeIn fourth" value="Log In">
    </form>
  </div>
</div>
</body>
</html>




