<%@page import="model.Fuel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.FuelServiceImpl"%>
<%@page import="service.IFuelService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Fuel.css" />
<meta charset="ISO-8859-1">
<title>Fuel Management</title>
</head>
<body>
	<h1 class="h1"> Fuel Station Management</h1>
	 <h2>List of Fuels</h2><br>
	  <div align="left">
		<table  id="t">
		  <tr>
                <th>Date</th>
                <th>Tank ID</th>
                <th>Fuel Name</th>
                <th>Tank Capacity</th>
                <th>Fuel Usage</th>
                <th>Remaining</th>
          </tr>
            <%
            IFuelService iFuelService = new FuelServiceImpl();
			ArrayList<Fuel> arrayList = iFuelService.getFuel();
			
			
			for(Fuel fuel : arrayList){
			%>
			 <tr>
				<td> <%=fuel.getDate() %> </td>
				<td> <%=fuel.getTankID() %></td>
				<td> <%=fuel.getFuelName() %> </td>
				<td> <%=fuel.getTankCapacity() %> </td>
				<td> <%=fuel.getFuelUsage()%></td>
				<td> <%=fuel.getRemaining() %> </td> 
				<td>
				<form method="POST" action="GetFuelDataServlet">
				<input type="hidden" name="tankID" value="<%=fuel.getTankID()%>"/>
				 <input type="submit" value= "Select" class="select-button" /> 
				 </form>
				 </td>	
				</tr>			
			<%	
			   }
            %>     
		</table>
		<br>
		<br>
		<button><a href="index.jsp">Add Fuels</a></button>
		</div>
		
</body>
</html>