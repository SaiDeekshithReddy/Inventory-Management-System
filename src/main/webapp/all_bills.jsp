<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" 
        import="com.virtusa.demo.bean.Bill" 
        import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="table_styling.css">
	<title>EMart</title>
</head>
<body>
  <div class="container-fluid" >
    <div class="row h-100" >
    <%@include file="shelf_left.jsp" %>
	  <div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;" >
	    <h3 style="text-align:center">Billing</h3>
	    <!-- Displaying all bills in the table -->
        <div class="table-responsive">
		  <table class="table">
  			<tr>
    		  <th>Bill Id </th>
    		  <th>Date and Time</th>
  			</tr>
  			
  			<% ArrayList<Bill> list = (ArrayList<Bill>) request.getAttribute("bills_list");
	 			for(int i=list.size()-1; i>=0 ;i--){
		 			Bill bill = list.get(i);
  			%>
  			<tr>
    		  <td><form action="display_bill" method ="post"><input type = "submit" value=<%= ""+bill.getBillId() %> name="button" ></form></td>
    		  <td><%= bill.getDate() %></td>
  			</tr>
  			<% } %>
  		  </table>
  		</div>
  	  </div>
  	</div>
  </div>
</body>
</html>