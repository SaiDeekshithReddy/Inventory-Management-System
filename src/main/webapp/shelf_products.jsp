<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" 
        import="com.virtusa.demo.bean.Shelf" 
        import="java.text.SimpleDateFormat"
        import="java.util.Date" %>
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
	<div class="row h-100" >
	  <%@include file="shelf_left.jsp" %>
	  <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;" >
		<h3 style="text-align:center">Shelf Products</h3>
		  <div class="table-responsive">
		    <table class="table">
			  <tr>
				<th>Id</th>
				<th>Product Name</th>
				<th>Product Category</th>
				<th>Product Description</th>
				<th>Count</th>
				<th>Cost</th>
				<th>Currency</th>
				<th>Expire Date</th>
			  </tr>
			  <% ArrayList<Shelf> list = (ArrayList<Shelf>)request.getAttribute("shelf_list"); 
				 Date today = new Date();
				 for(int i=0; i<list.size(); i++){ 
				 	Shelf shelf = list.get(i);
				 	SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		        	String strDate = formatter.format(shelf.getExpireDate());
		        	String todDate = formatter.format(today);
		        	Date todayadate  = formatter.parse(todDate);
		        	Date expdate  = formatter.parse(strDate);
		        	long difference = expdate.getTime() - todayadate.getTime();
			    	float daysBetween = (difference / (1000*60*60*24));%>
			  <% if( daysBetween < 3 || shelf.getCount() < 30){ %>
			  <tr class="table-danger">
			    <td><%= shelf.getId() %></td>
			    <td><%= shelf.getProductName() %></td>
			    <td><%= shelf.getProductCategory() %></td>
			    <td><%= shelf.getProductDescription() %></td>
			    <td><%= shelf.getCount() %></td>
			    <td><%= shelf.getCost() %></td>
			    <td><%= shelf.getCurrency() %></td>
			    <td><%= strDate %></td>
			  </tr>
			  <%} else{ %>
			  <tr>
			    <td><%= shelf.getId() %></td>
			    <td><%= shelf.getProductName() %></td>
			    <td><%= shelf.getProductCategory() %></td>
			    <td><%= shelf.getProductDescription() %></td>
			    <td><%= shelf.getCount() %></td>
			    <td><%= shelf.getCost() %></td>
			    <td><%= shelf.getCurrency() %></td>
			    <td><%= strDate %></td>
			  </tr>
				<% }} %>
		  </table>
    	</div>
	  </div>
	</div>
  </div>
</body>
</html>