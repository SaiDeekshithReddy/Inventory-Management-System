<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" 
        import="com.virtusa.demo.bean.WareHouseExpiry" 
        import="java.text.SimpleDateFormat"
        import="java.util.Date"%>
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
	  <%@include file="warehouseleft.jsp" %>
	  <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;" >
	    <h3 style="text-align:center">WareHouse Expiry </h3>
	    <div class="table-responsive">
		  <table class="table">
			  <tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Expire Date</th>
				<th>Count</th>
			  </tr>
			  <% ArrayList<WareHouseExpiry> list = (ArrayList<WareHouseExpiry>)request.getAttribute("warehouse_expiry"); 
				 Date today = new Date();
			  	 for(int i=0; i<list.size(); i++){ 
				 	WareHouseExpiry wh = list.get(i);
				 	SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
			        String strDate = formatter.format(wh.getExpireDate());
			        String todDate = formatter.format(today);
			        Date todayadate  = formatter.parse(todDate);
			        Date expdate  = formatter.parse(strDate);
			        long difference = expdate.getTime() - todayadate.getTime();
				    float daysBetween = (difference / (1000*60*60*24));
				%>
				<% if( daysBetween < 3 ){%>
			  <tr class="table-danger">
			    <td><%= wh.getProductId() %></td>
			    <td><%= wh.getProductName() %></td>
			    <td><%= strDate %></td>
			    <td><%= wh.getCount() %></td>
			  </tr>
			  <%} else{%>
			  <tr>
			    <td><%= wh.getProductId() %></td>
			    <td><%= wh.getProductName() %></td>
			    <td><%= strDate %></td>
			    <td><%= wh.getCount() %></td>
			  </tr>
			  <% } %>
			  <% } %>
		  </table>
		</div>
	  </div>
	</div>
  </div>
</body>
</html>