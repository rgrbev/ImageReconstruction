<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/dashboard.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://localhost:8080/ImageReconstruction/first">Image Reconstruction</a>
		</div>

	</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a
						href="http://localhost:8080/ImageReconstruction/newWorkspace">Create
							new Workspace</a></li>
				</ul>
				<hr style="color: red;" />
				<ul class="nav nav-sidebar">
					<c:forEach var="workspace" items="${workspaces}">
						<li><a href="http://localhost:8080/ImageReconstruction/workspace/${workspace.getId()}">${workspace.getName()}</a></li>
					</c:forEach>
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="jumbotron">
					<form action="createWorkspace" method="POST">
						<div class="form-group">
							<label for="name">Workspace name</label> <input type="text"
								class="form-control" id="name" name="name"
								placeholder="Workspace name">
						</div>
						<div class="form-group">
							<label for="description">Description</label> <input type="text"
								class="form-control" id="description" name="description"
								placeholder="Description">
						</div>
						<button type="submit" class="btn btn-success">Create</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>