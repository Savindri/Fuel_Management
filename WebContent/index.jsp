<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html SYSTEM "about:legacy-compat">
<head>

  <link rel = "stylesheet"
   type = "text/css"
   href = "Fuel.css" />
   
   
<meta charset="ISO-8859-1">
<title>Fuel Station</title>
</head>
<body class="body">

	<jsp:include page="/WEB-INF/view/header.jsp"></jsp:include>
	
	<h1 class="h1"> Fuel Station Management</h1>
	<h2 class="h2">Add Fuel Data </h2>

	<br>
	<br>

	<form method="POST" action="AddFuelDataServlet">
		<table>
			<tr>
				<td>Date</td>
				<td><input type="date" name="date"  /></td>
			</tr>
			<tr>
				<td>Fuel Name</td>
				<td>
					<select name="name">
    					<option value="Petrol 92 Octane" selected>Petrol 92 Octane</option>
    					<option value="Petrol 95 Octane Euro 4">Petrol 95 Octane Euro 4</option>
    					<option value="Lanka Auto Diesel">Lanka Auto Diesel</option>
    					<option value="Lanka Super Diesel 4 Star Euro">Lanka Super Diesel 4 Star Euro</option>
    					<option value="Ethanol E10">Ethanol E10</option>
    					<option value="Ethanol E85">Ethanol E85</option>
    					<option value="Ethanol E100">Ethanol E100</option>
    					<option value="Biodiesel B20">Biodiesel B20</option>
    					<option value="Biodiesel B99">Biodiesel B99</option>
    					<option value="Propane">Propane</option>
    					<option value="CNG(Compressed Natural Gas)">CNG(Compressed Natural Gas)e</option>
    					<option value="Kerosine[Regular]">Kerosine[Regular]</option>
    					<option value="Lanka Industrial Kerosine">Lanka Industrial Kerosine</option>
 					 </select>
 				</td>
			</tr>
			<tr>
				<td>Tank Capacity(in litres)</td>
				<td><input type="text" name="tankCapacity" value="10000" readonly/></td>
			</tr>
			<tr>
				<td>Fuel Usage(in litres)</td>
				<td><input type="text" name="fuelUsage" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Fuel Data" class="add-button" /> </td>
			</tr>
			<tr>	
				<td colspan="2"><input type="reset" value="Reset" class="reset-button" /></td>
			</tr>
		</table>
	</form>
		
	<form method="POST" action="ListFuelDataServlet">
		<table>
			<tr>
				<td colspan="2"><input type="submit" value="List All Fuels" class="list-button" /></td>
			</tr>
		</table>
	</form>
			
	<jsp:include page="/WEB-INF/view/footer.jsp"></jsp:include>

</body>
</html>