<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anything near boun</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="margin: 15px auto;">
	<div class="jumbotron" style="margin-top: 15px; text-align: center;">
		You can search any place within a circle around Bogazici University, or you can move off as much as you want and search around that point (Negative values are valid). Default is 1 km radius around South Campus of the university.
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			WikiData query 
		</div>
		<div class="panel-body">
			<form action="" class="form-inline">
				<div class="row">
					<div class="col-sm-3">
						<div class="input-group">
							<input class="form-control" type="number" name="radius" placeholder="Radius">
							<span class="input-group-addon">km</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input class="form-control" type="number" name="x" placeholder="Distance on x axis">
							<span class="input-group-addon">km</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input class="form-control" type="number" name="y" placeholder="Distance on y axis">
							<span class="input-group-addon">km</span>
						</div>
					</div>
					<div class="col-sm-3">
						<input class="btn btn-default" type="submit" value="Get data">
					</div>
				</div>
			</form>
		</div>
	</div>
	<% JSONArray items = (JSONArray) request.getAttribute("items"); %>
	<div class="panel panel-default">
		<div class="panel-heading">
			Data
		</div>
		<form method="POST" action="">
			<table class="table">
				<tr>
					<th>Save</th>
					<th>Value</th>
					<th>Description</th>
				</tr>
				<% for (int i = 0; i < items.size() ; i++) { %>
					<tr>
						<% JSONObject item = (JSONObject) items.get(i); %>
						<% JSONObject placeLabel = (JSONObject) item.get("placeLabel"); %>
						<% JSONObject placeDescription = (JSONObject) item.get("placeDescription"); %>
						<% String label = (String) placeLabel.get("value"); %>
						<% String description = placeDescription != null ? (String) placeDescription.get("value") : ""; %>
						<% if (request.getMethod().equalsIgnoreCase("GET")) { %>
							<td><input type="checkbox" name="data<%= i %>save"></td>
							<input hidden name="data<%= i %>" value="true">	
							<input hidden type="text" name="data<%= i %>label" value="<%= label %>">
							<input hidden type="text" name="data<%= i %>description" value="<%= description %>">
						<% } else { %>
							<td></td>
						<% } %>
						<td><%= label %></td>
						<td><%= description %></td>
					</tr>
				<% } %>
			</table>
		</form>
		<% if (request.getMethod().equalsIgnoreCase("GET")) { %>
			<div class="panel-body">
				<form method="POST" action="" style="display: inline-block;">
					<input class="btn btn-default btn-lg" type="submit" value="Get data from database">
				</form>
				<input type="submit" value="Save to database" class="btn btn-default btn-lg">
			</div>
		<% } %>
	</div>
</div>
</body>
</html>