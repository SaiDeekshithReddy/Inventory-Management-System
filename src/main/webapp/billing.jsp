<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script>
i=0;
function display(){
	a=document.getElementById("demo");
	b=document.createElement("input");
	b.setAttribute("type","text");
	b.setAttribute("id","t1"+i);
	b.setAttribute("name","product_id"+i);
	c=document.createElement("input");
	c.setAttribute("type","text");
	c.setAttribute("id","t2"+i);
	c.setAttribute("name","product_count"+i);
	var br = document.createElement("br");
	a.appendChild(b);
	a.appendChild(c);
	a.appendChild(br);
	i=i+1;
}
</script>
  <div class="container-fluid horizontal-scrollable h-100" >
    <div class="row h-100" >
	  <%@include file="shelf_left.jsp" %>
		<div class="col-sm-10" style="background-color: lightblue; min-height: 80vh;" >
		  <h3 style="text-align:center">Billing</h3>
		  <form action="bill" method ="post">
			<input type="text" value="Product ID" readonly/>
			<input type="text" value="Product Count" readonly/>
			<div id="demo"></div><br>
			<input type = "button" value="Add" onClick="display()"/><br>
			<div style="color:red;">${billing_error}</div>
			<input type = "submit" value="Submit">
		  </form>
	  </div>
    </div>
  </div>
</body>
</html>