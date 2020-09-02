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
	<script>
	function fileValidation(){
	    var fileInput = document.getElementById('myFile');
	    var filePath = fileInput.value;
	    var allowedExtensions = /(\.csv)$/i;
	    if(!allowedExtensions.exec(filePath)){
	        alert('Please upload file having extensions .csv only.');
	        fileInput.value = '';
	        return false;
	    }
	}
	</script>
</head>
<body>
  <%@include file="toppage.jsp" %>
  <div class="col-sm-2" style="background-color: lightgreen; min-height: 40vh;">
	  <h3 style="text-align:center">Warehouse</h3>
	  <form action="display_warehouseproducts" method="post">
	    <input type = "submit" value="Products" class="button">
	  </form>
	  <form action="display_warehouse_expiry" method="post">
	    <input type = "submit" value="Products expiry" class="button">
	  </form>
	  <form action="load_warehouse" method="post" enctype="multipart/form-data">
	    <input type="file"  id="myFile" name="filename" class="button" onchange="fileValidation()" required><br>
	    <input type="submit" value = "upload" class="button">
	  </form>
	  <div style="color:red;">${warehouse_upload_msgerror}</div>
	  <div style="color:green;">${warehouse_upload_msg}</div>
  </div>
</body>
</html>