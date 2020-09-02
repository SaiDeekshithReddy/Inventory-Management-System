<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" 
        import="com.virtusa.demo.bean.BillingItem" 
        import="java.text.SimpleDateFormat"
        import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>EMart</title>
	<link rel="stylesheet" href="table_styling.css"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid" >
  <div class="row h-100" >
  <%@include file="shelf_left.jsp" %>
    <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;" >
	  <h3 style="text-align:center">Billing</h3>
		<div class="table-responsive">
		<!-- Displaying the bill -->
		  <table class="table">
		    <tr>
			  <th>Id</th>
			  <th>Product Name</th>
			  <th>Cost</th>
			  <th>Count</th>
			  <th>Total Cost</th>
			</tr>
		    <% ArrayList<BillingItem> billingList = (ArrayList<BillingItem>) request.getAttribute("billing_list"); 
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