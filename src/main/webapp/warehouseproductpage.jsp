<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" 
        import="com.virtusa.demo.bean.WareHouse" 
        import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="table_styling.css"/>
	<title>EMart</title>
</head>
<body>
  <div class="container-fluid horizontal-scrollable h-100" >
	<div class="row h-100">
	  <%@include file="warehouseleft.jsp" %>
	  <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;">
		<h3 style="text-align:center">WareHouse Products</h3>
		<div class="table-responsive">
		  <table class="table">
		  <tr>
			<th>Id</th>
			<th>Product Name</th>
			<th>Vendor Code</th>
			<th>Product Category</th>
			<th>Product Description</th>
			<th>Count</th>
			<th>Cost</th>
			<th>Currency</th>
		  </tr>
		  <% ArrayList<WareHouse> list = (ArrayList<WareHouse>)request.getAttribute("warehouseproductlist"); 
			 for(int i=0; i<list.size(); i++){ 
			 	WareHouse wareHouse = list.get(i);
			 	 %>
		  <% if( wareHouse.getCount() < 100){ %>
		  <tr class="table-danger">
		    <td><%= wareHouse.getId() %></td>
		    <td><%= wareHouse.getProductName() %></td>
		    <td><%= wareHouse.getVendorCode() %></td>
		    <td><%= wareHouse.getProductCategory() %></td>
		    <td><%= wareHouse.getProductDescription() %></td>
		    <td><%= wareHouse.getCount() %></td>
		    <td><%= wareHouse.getCost() %></td>
		    <td><%= wareHouse.getCurrency() %></td>
		  </tr>
		  <%} else{ %>
		  <tr>
		    <td><%= wareHouse.getId() %></td>
		    <td><%= wareHouse.getProductName() %></td>
		    <td><%= wareHouse.getVendorCode() %></td>
		    <td><%= wareHouse.getProductCategory() %></td>
		    <td><%= wareHouse.getProductDescription() %></td>
		    <td><%= wareHouse.getCount() %></td>
		    <td><%= wareHouse.getCost() %></td>
		    <td><%= wareHouse.getCurrency() %></td>
		  </tr>
		  <%} %>
			<% } %>
		</table>
		</div>
      </div>
    </div>
  </div>
</body>
</html>