<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/dashboard.css" rel="stylesheet">
<link href="../resources/css/uploadImage.css" rel="stylesheet">
<link href="../resources/css/dropZone.css" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/blueimp-gallery.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/uploadImage.js"></script>
<script src="../resources/js/dropZone.js"></script>

<script src="../resources/js/blueimp-helper.js"></script>
<script src="../resources/js/blueimp-gallery.js"></script>
<script src="../resources/js/jquery.blueimp-gallery.js"></script>
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
			<a class="navbar-brand"
				href="http://localhost:8080/ImageReconstruction/first">Image
				Reconstruction</a>
		</div>

	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<!-- Navigacija levo -->
			<div class="col-sm-3 col-md-2 col-lg-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="workspacesClass"><a
						href="http://localhost:8080/ImageReconstruction/newWorkspace">Create
							new Workspace</a></li>
				</ul>
				<hr style="color: red;" />
				<ul class="nav nav-sidebar">
					<c:forEach var="workspace" items="${workspaces}">
						<li class="workspacesClass" id="${workspace.getId()}"
							value="${workspace.getId()}"><a
							href="http://localhost:8080/ImageReconstruction/workspace/${workspace.getId()}">${workspace.getName()}</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- Navigacija levo  Kraj-->
			<div
				class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 col-lg-10 col-lg-offset-2 main">
				<div class="row">
					<!-- Naslov, opis i kopcinjata -->
					<div class="col-sm-8 col-md-9 col-lg-9">
						<h1 style="float: left;" class="page-header">${workspace.getName()}
							<small><h5>${workspace.getDescription()}</h5></small>
						</h1>

					</div>
					<div
						class="col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-1 col-lg-2 col-lg-offset-1 ">
						<a style="float: right;"
							href="http://localhost:8080/ImageReconstruction/workspace/deleteWorkspace/${workspace.getId()}"><button
								class="btn btn-md btn-danger">
								<span class="glyphicon glyphicon-remove" aria-hidden="true">&nbsp</span>Delete
							</button></a></br> </br> </br>

						<!-- Modal Form -->
						<button type="button" class="btn btn-warning" data-toggle="modal"
							style="float: right;" data-target="#exampleModal"
							data-whatever="@mdo">
							<span class="glyphicon glyphicon-edit" aria-hidden="true">&nbsp</span>Edit
						</button>
						</br> </br> </br>

						<div class="modal fade" id="exampleModal" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="exampleModalLabel">Edit
											${workspace.getName()}</h4>
									</div>
									<div class="modal-body">
										<form action="editWorkspace" method="POST"
											enctype="multipart/form-data">
											<div class="form-group">
												<input type="text" style="display: none;" name="idWorkspace"
													value="${workspace.getId()}"> <label
													for="recipient-name" class="control-label">Workspace
													name:</label> <input type="text" class="form-control" id="name"
													name="name">
											</div>
											<div class="form-group">
												<label for="message-text" class="control-label">Workspace
													description</label> <input class="form-control" id="description"
													name="description"></input>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
												<button type="submit" value="submit" class="btn btn-primary">Save</button>
											</div>
										</form>
									</div>

								</div>
							</div>
						</div>
						<!-- Modal Form -->
					</div>

				</div>
				<div class="row">
					<!-- File upload -->
					<div class="col-md-4 col-md-offset-3 col-lg-4 col-lg-offset-3">
						<div class="panel panel-default">
							<div class="panel-heading">
								<strong>Upload Images</strong>
							</div>
							<div class="panel-body">

								<!-- Standar Form -->
								<h4>Select files from your computer</h4>
								<form id="data" enctype="multipart/form-data"
									action="uploadImage" method="POST">
									<div class="form-inline">
										<div class="form-group">
											<input type="text" name="idWorkspace"
												value="${workspace.getId()}" style="display: none;">
											<input type="file" name="files[]" id="files"
												accept="image/png, image/jpeg, image/gif" multiple>
										</div>
										<button type="submit" class="btn btn-sm btn-primary"
											id="js-upload-submit" action>Upload files</button>
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-1 col-md-1 col-lg-1">
						<h2 class="sub-header">Images</h2>
					</div>
					<div class="col-sm-11 col-md-11 col-lg-11">

						<a
							href="http://localhost:8080/ImageReconstruction/workspace/configureProcessing/${workspace.getId()}">
							<button class="btn btn-info"
								style="float: right; margin-right: 10px">Configure
								Processing</button>
						</a>

					</div>
				</div>
				<div class="row">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Width</th>
									<th>Height</th>
									<th>Size</th>
									<th>Processing</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<div id="links" class="links">
									<c:forEach var="image" items="${images}">

										<tr>

											<td>${image.getId()}</td>
											<td><a data-gallery="gallery"
												href="${image.getNamePath()}" title="${image.getName()}">${image.getName()}</a></td>
											<td>${image.getWidth()}</td>
											<td>${image.getHeight()}</td>
											<td>${image.getSize()}</td>
											<td><a
												href="http://localhost:8080/ImageReconstruction/workspace/deleteImage/${image.getId()}?idWorkspace=${workspace.getId()}"><button
														class="btn btn-sm btn-danger">Delete</button></a></td>
											</a>
										</tr>
									</c:forEach>
								</div>
								<div id="blueimp-gallery" class="blueimp-gallery">
									<div class="slides"></div>
									<h3 class="title"></h3>
									<a class="prev"><span
										class="glyphicon glyphicon-chevron-left" aria-hidden="true">&nbsp</span></a>
									<a class="next"><span
										class="glyphicon glyphicon-chevron-right" aria-hidden="true"
										style="margin-left: 60%;">&nbsp</span></a> <a class="close">x</a>
									<a class="play-pause"></a>
									<ol class="indicator"></ol>
								</div>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function createParameters(imagePath) {

		var imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1,
				imagePath.length);
		return imageName;

	}
	$(document).ready(function() {

		$('[data-toggle="tooltip"]').tooltip({
			placement : 'top'
		});

		$("workspacesClass active").addClass("workspacesClass");
		var idLi = "${workspace.getId()}";
		$("#" + idLi).addClass("workspacesClass active");
	});
</script>
</html>