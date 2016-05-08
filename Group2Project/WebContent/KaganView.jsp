<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="">
	<input type="number" name="radius" placeholder="Radius (km)">
	<label for="x">Go to up or down</label>
	<input type="number" name="y" placeholder="Difference on x axis">
	<label for="y">Go to right or left</label>
	<input type="number" name="x" placeholder="Radius (km)">
	<input type="submit" value="Get data">
</form>
<% JSONArray items = (JSONArray) ((JSONObject) (((JSONObject) request.getAttribute("items")).get("results"))).get("bindings"); %>
<table>
	<tr>
    	<th>value</th>
    	<th>description</th>
	</tr>
	<% for (int i = 0; i < items.size() ; i++) { %>
		<tr>
			<% JSONObject item = (JSONObject) items.get(i); %>
			<% JSONObject placeLabel = (JSONObject) item.get("placeLabel"); %>
			<% JSONObject placeDescription = (JSONObject) item.get("placeDescription"); %>
			<% String type = (String) placeLabel.get("type"); %>
			<% String label = (String) placeLabel.get("value"); %>
			<% String description = placeDescription != null ? (String) placeDescription.get("value") : ""; %>
			<td><%= label %></td>
			<td><%= description %></td>
		</tr>
	<% } %>
</table>

</body>
</html>