<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" 
        import="com.virtusa.demo.bean.BillingItem" 
        import="com.virtusa.demo.bean.Bill"
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
  <div class="container-fluid" >
	<div class="row h-100" >
	  <%@include file="shelf_left.jsp" %>
	  <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;" >
		<h3 style="text-align:center">Bill</h3><br>
		<% Bill bill = (Bill)request.getAttribute("bill"); %>
		<h3>Bill Id : <%= bill.getBillId() %></h3>
		<h3>Date and Time : <%= bill.getDate() %></h3>
		  <div class="table-responsive">
			<table class="table">
			  <tr>
			    <th>Product Id</th>
				<th>Product Name</th>
				<th>Cost</th>
				<th>Count</th>
				<th>Total Cost</th>
			  </tr>
			<% ArrayList<BillingItem> billingList = bill.getItems();
			   Long totalAmount = 0l;
			   for(BillingItem billingItem : billingList){
				 long cost = billingItem.getCost();
				 long count = billingItem.getCount();
				 long totalCost = cost * count;
				 totalAmount += totalCost;
			%>
			<tr>
			    <td><%= billingItem.getProductId() %></td>
			    <td><%= billingItem.getProductName() %></td>
			    <td><%= cost %></td>
			    <td><%= count %></td>
			    <td><%= totalCost %></td>
			  </tr>
			  <% } %>
			  <tr>
			    <td colspan="3">Total Amount<td>
			    <td><%= totalAmount %></td>
			  </tr>
			</table>
	    </div>
	  </div>
    </div>
  </div>
</body>
</html>