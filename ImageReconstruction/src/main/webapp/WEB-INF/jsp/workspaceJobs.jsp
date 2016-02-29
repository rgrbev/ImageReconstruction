<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../../resources/css/dashboard.css" rel="stylesheet">
<link href="../../resources/css/uploadImage.css" rel="stylesheet">
<link href="../../resources/css/dropZone.css" rel="stylesheet">
<link rel="stylesheet" href="../../resources/css/blueimp-gallery.css">
<link rel="stylesheet" href="../../resources/css/bootstrap-select.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/uploadImage.js"></script>
<script src="../../resources/js/dropZone.js"></script>

<script src="../../resources/js/blueimp-helper.js"></script>
<script src="../../resources/js/blueimp-gallery.js"></script>
<script src="../../resources/js/jquery.blueimp-gallery.js"></script>
<script src="../../resources/js/bootstrap-select.js"></script>
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
				</div>
				<div class="row">
					<!-- Create workspaceJob -->
					<div
						class="col-sm-5 col-md-5 col-md-offset-2 col-lg-5 col-lg-offset-3">
						<div class="panel panel-default">
							<div class="panel-heading">
								<strong>Create new Workspace Job</strong>
							</div>
							<div class="panel-body">
								<form action="createWorkspaceJob" method="POST">
									<div class="form-group">
										<label class="col-sm-6 col-md-6 col-lg-6 control-label">Name</label>
										<div class="col-sm-4 col-md-4 col-lg-4 selectContainer">
											<input type="text" name="name" class="form-control" />
																														<!-- name -->
										</div>
									</div>
									<br /> <br />

									<div class="form-group">
										<label class="col-sm-6 col-md-6 col-lg-6 control-label">Detector</label>
										<div class="col-sm-4 col-md-4 col-lg-4 selectContainer">
											<select name="detector" id="detectorSelect"
												class="form-control" onchange="fillAdditionalSelect()">
																														<!-- detector -->
												<option value="notselected"></option>
												<option value="harrislaplace">Harris-Laplace</option>
												<option value="densesampling">Dense sampling</option>
											</select>
										</div>
									</div>
									<br /> <br />

									<div class="form-group">
										<label class="col-sm-6 col-md-6 col-lg-6 control-label">Additional
											options for detector</label>
										<div class="col-sm-6 col-md-6 col-lg-6 selectContainer"
											id="additionalOptionsDiv">
											<div id="denseSampling" style="display: none">

												<div class="row">
													<div class="col-sm-8 col-md-8 col-lg-8">
														<label><input type="checkbox" name="ds_spacing"  				
															value="--ds_spacing" id="ds_spacing" />&nbsp ds_spacing</label><!-- ds_spacing -->
													</div>
													<div class="col-sm-4 col-md-4 col-lg-4">
														<input type="text" name="ds_spacingValue" class="form-control" /><!-- ds_spacingValue -->
													</div>

												</div>
												<br />
												<div class="row">
													<div class="col-sm-8 col-md-8 col-lg-8">
														<label><input type="checkbox" name="ds_scales"
															value="--ds_scales" id="ds_scales" />&nbsp ds_scales
															&nbsp</label>													<!-- ds_scales -->
													</div>
													<div class="col-sm-4 col-md-4 col-lg-4">
														<input type="text" name="ds_scalesValue" class="form-control" />	<!-- ds_scalesValue -->
													</div>
												</div>
												<!-- additionalOptions -->
											</div>
											<div id="harrisLaplace" style="display: none">
												<div class="row">
													<div class="col-sm-8 col-md-8 col-lg-8">
														<label style="display: block" for="harrisThreshold"><input
															type="checkbox" name="harrisThreshold"
															value="--harrisThreshold" id="harrisThreshold" />&nbsp harrisThreshold</label> <!-- harrisThreshold -->
													</div>
													<div class="col-sm-4 col-md-4 col-lg-4">
														<input type="text" name="harrisThresholdValue" class="form-control" />			<!-- harrisThresholdValue -->
													</div>
												</div><br />
												<div class="row">
													<div class="col-sm-8 col-md-8 col-lg-8">
														<label style="display: block" for="harrisK"><input
															type="checkbox" name="harrisK" value="--harrisK"
															id="harrisK" />&nbsp harrisK</label>										<!-- harrisK -->
													</div>
													<div class="col-sm-4 col-md-4 col-lg-4">
														<input type="text" name="harrisKValue" class="form-control" />					<!-- harrisKValue -->
													</div>
												</div><br />
												<div class="row">
													<div class="col-sm-8 col-md-8 col-lg-8">
														<label style="display: block" for="laplaceThreshold"><input
															type="checkbox" name="laplaceThreshold"
															value="--laplaceThreshold" id="laplaceThreshold" />&nbsp laplaceThreshold</label>	<!-- laplaceThreshold -->
													</div>
													<div class="col-sm-4 col-md-4 col-lg-4">
														<input type="text" name="laplaceThresholdValue" class="form-control" />					<!-- laplaceThresholdValue -->
													</div>
												</div>
												<!-- additionalOptions -->
											</div>
										</div>

									</div>
									<br /> <br />

									<div class="form-group">
										<label class="col-sm-6 col-md-6 col-lg-6 control-label">Descriptor</label>
										<div class="col-sm-4 col-md-4 col-lg-4 selectContainer">
											<select id="descriptorSelect" name="descriptor"
												class="form-control">
																																		<!-- descriptor -->
												<option value="rgbhistogram">RGB histogram</option>
												<option value="opponenthistogram">Opponent
													histogram</option>
												<option value="huehistogram">Hue histogram</option>
												<option value="nrghistogram">rg histogram</option>
												<option value="transformedcolorhistogram">Transformed
													Color histogram</option>
												<option value="colormoments">Color moments</option>
												<option value="colormomentinvariants">Color moment
													invariants</option>
												<option value="sift">SIFT</option>
												<option value="huesift">HueSIFT</option>
												<option value="hsvsift">HSV-SIFT</option>
												<option value="opponentsift">OpponentSIFT</option>
												<option value="rgsift">rgSIFT</option>
												<option value="csift">C-SIFT</option>
											</select>
										</div>
									</div>
									<br /> <br />

									<div class="form-group">
										<div
											class="col-sm-offset-5 col-md-offset-5 col-lg-offset-5 col-sm-2 col-md-2 col-lg-2">
											<input type="text" name="idWorkspace"
												value="${workspace.getId()}" style="display: none;">
											<!-- idWorkspace -->
											<input type="Submit" value="Create Job"
												class="btn btn-primary">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- Create workspaceJob -->

				</div>
				<div class="row">
					<div class="col-sm-3 col-md-3 col-lg-3">
						<h2 class="sub-header">Workspace Jobs</h2>
					</div>
				</div>
				<div class="row">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Date created</th>
									<th>Date Finished</th>
									<th>Detector</th>
									<th>Additional detector options</th>
									<th>Descriptor</th>
									<th></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="workspaceJob" items="${workspaceJobs}">
									<tr>
										<td>${workspaceJob.getId()}</td>
										<td>${workspaceJob.getName()}</td>
										<td>${workspaceJob.getDateCreated()}</td>
										<td>${workspaceJob.getDateFinished()}</td>
										<td>${workspaceJob.getDetector()}</td>
										<td>${workspaceJob.getAdditionalDetectorOptions()}</td>
										<td>${workspaceJob.getDescriptor()}</td>
										<td><a
											href="http://localhost:8080/ImageReconstruction/workspace/configureProcessing/startProcessing/${workspaceJob.getId()}?idWorkspace=${workspace.getId()}"><button
													style="margin-right: 10px;" class="btn btn-sm btn-primary">Start
													Processing</button></a> </a> <a
											href="http://localhost:8080/ImageReconstruction/workspace/configureProcessing/deleteWorkspaceJob/${workspaceJob.getId()}?idWorkspace=${workspace.getId()}"><button
													class="btn btn-sm btn-danger">Delete</button></a></td>

									</tr>
								</c:forEach>


							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function fillAdditionalSelect() {

		var select = document.getElementById("detectorSelect");
		var option = select.options[select.selectedIndex].value;
		var additionalOptionsDiv = document
				.getElementById("additionalOptionsDiv");

		if (option == "harrislaplace") {
			var div = document.getElementById("denseSampling");
			div.style.display = "none";
			var div2 = document.getElementById("harrisLaplace");
			div2.style.display = "block";
		} else if (option == "densesampling") {
			var div2 = document.getElementById("harrisLaplace");
			div2.style.display = "none";
			var div = document.getElementById("denseSampling");
			div.style.display = "block";
			/*alert(option);
			additionalOptionsDiv.innerHTML = "";

			optionsArray = [ "ds_spacing", "ds_scales" ];
			for (var i = 0; i < optionsArray.length; i++) {
				/alert(optionsArray[i]);
				var checkBox = document.createElement("input");
				var label = document.createElement("label");
				checkBox.type = "checkbox";
				checkBox.name = optionsArray[i];
				checkBox.innerHTML = optionsArray[i];
				checkBox.value = optionsArray[i];
				checkBox.className = "form-control";
				checkBox.id = optionsArray[i];
				label.htmlFor = optionsArray[i];
				label.innerHTML = "--" + optionsArray[i];
				label.appendChild(checkBox);

				additionalOptionsDiv.appendChild(label);
				
			}
			 */
		}
	}
</script>
</html>