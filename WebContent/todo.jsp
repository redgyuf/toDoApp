<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>toDoApp</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/todo.css" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src=js/todo.js ></script>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">toDo App</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</nav>	
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h1>Your toDo list</h1>
			<ul class="nav nav-pills" role="tablist">
		        <li id="FilterAll" class="active" role="presentation"><a href="#" onClick="getTasks('All')">All <span class="badge"></span></a></li>
		        <li id="FilterActive" role="presentation"><a href="#" onClick="getTasks('Active')">Active <span class="badge"></span></a></li>
		        <li id="FilterCompleted" role="presentation"><a href="#" onClick="getTasks('Completed')">Completed <span class="badge"></span></a></li>
     		</ul>
			<div class="row">
				<div class="col-sm-4">
					<ul id="toDoList" class="list-group">
					</ul>
					<ul id="addToDo" class="list-group">
						<li class="list-group-item"><input id="inputTaskName" class="form-control" placeholder="Your next thing to Do?" required><button id="addTaskButton" type="button" class="btn btn-xs btn-success">Add</button></li>
					</ul>
				</div>
			</div>
		</div>
	</div>



</body>
</html>