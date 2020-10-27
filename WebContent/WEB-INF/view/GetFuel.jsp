<%@page import="model.Fuel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Fuel.css" />
<meta charset="UTF-8">
<title>Fuel Management</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/header.jsp"></jsp:include>

	<h1 class="h1"> Fuel Station Management</h1>
	<h2 class="h2">Get Fuel Data</h2>

	<br>
	<br>
	<%
		Fuel fuel = (Fuel) request.getAttribute("fuel");
	%>

	<form method="POST" action="UpdateFuelDataServlet">
		<table>
			<tr>
				<td>Date</td>
				<td><input type="date" name="date"
					value="<%=fuel.getDate()%>" /></td>
			</tr>
			<tr>
				<td>Tank ID</td>
				<td><input type="text" name="tankID"
					value="<%=fuel.getTankID()%>" /></td>
			</tr>
			<tr>
				<td>Fuel Name</td>
				<td><input type="text" name="name"
					value="<%=fuel.getFuelName()%>" /></td>
			</tr>
			<tr>
				<td>Tank Capacity</td>
				<td><input type="text" name="tankCapacity"
					value="<%=fuel.getTankCapacity()%>" /></td>
			</tr>
			<tr>
				<td>Fuel Usage</td>
				<td><input type="text" name="fuelUsage"
					value="<%=fuel.getFuelUsage()%>" /></td>
			</tr>
			<tr>
				<td>Remaining</td>
				<td><input type="text" name="remaining"
					value="<%=fuel.getRemaining()%>" /></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<input type="hidden" name="tankID"
					value="<%=fuel.getTankID()%>" />
					<input type="submit"
					value="Update Fuel" class="update-button"/>
				</td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<td colspan="2">
				<form method="POST" action="DeleteFuelDataServlet">
					<input type="hidden" name="tankID"
						value="<%=fuel.getTankID()%>" /> 
					<input type="submit"
						value="Delete Fuel" class="delete-button"/>
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<form method="POST" action="ListFuelDataServlet">
					<input type="submit" value="Back" class="back"/>
				</form>
			</td>		
		</tr>
	</table>

	<jsp:include page="/WEB-INF/view/footer.jsp"></jsp:include>

</body>
</html>