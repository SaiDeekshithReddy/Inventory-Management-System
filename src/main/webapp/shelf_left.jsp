<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
	<meta charset="ISO-8859-1">
	<title>EMart</title>
	<link rel="stylesheet" href="sample.css"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
  <%@include file="toppage.jsp" %>
  <div class="col-sm-2" style="background-color: lightgreen; min-height: 50vh;">
	  <h3 style="text-align:center">Shelf</h3>
	  <form action="display_shelf_products" method="post">
		<input type = "submit" value="Products" class="button">
	  </form>
	  <form action="load_shelf" method="post" enctype="multipart/form-data" >
		  <input type="file" id="myFile" name="filename" class="button" onchange="fileValidation()" required /><br>
		  <input type="submit" value = "load" class="button">
	  </form>
	  <div style="color:red;">${shelf_upload_msgerror}</div>
	  <div style="color:green;">${shelf_upload_msg}</div>
	  <form action="billing" method="post">
		<input type = "submit" value="Billing" class="button">
	  </form>
	  <form action="all_bills" method="post">
		<input type = "submit" value="All Bills" class="button">
	  </form>
  </div>
</body>
</html>