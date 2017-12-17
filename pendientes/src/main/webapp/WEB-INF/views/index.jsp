<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pendientes</title>

<link href="${css}/bootstrap.min.css" rel="stylesheet">
<link href="${css}/style.css" rel="stylesheet">
</head>
<body ng-app="pendientes">
	<div class="container" ng-controller="pendientesController">
		<div
			class="col-xs-12">
			<h2 class="text-center">Lista de Pendientes</h2>
			<p class="text-center">{{informacion}}</p>
			<div id="errorMsg" data-ng-show="showError" ng-class="{fade:doFade}"
				class="alert alert-danger">
				<strong>Error:</strong> {{errorMessage}}
			</div>
			<div class="form">
				<div class="input-group">
					<input type="text" class="form-control" placeholder ="Tengo que hacer ..."
						ng-model="pendienteForm.nombre"> <span
						class="input-group-btn">
						<button class="btn btn-default" type="button"
							ng-click="nuevoPendiente()">
							<span class="glyphicon glyphicon-plus"></span> {{boton}}
						</button>
					</span>
				</div>
			</div>
			<hr/>

			<ul class="list-group" ng-show="pendientes.length > 0">
				<li class="list-group-item clearfix task"
					ng-repeat="p in pendientes"
					ng-class="{disabled: p.completado==true}">
					<p class="lead">{{p.nombre}}</p>
					<div>
						<span class="pull-right">
											<p>{{p.fecha | date: "dd-MM-yyyy"}}</p>
							<button class="btn btn-default btn-xs" ng-disabled="p.completado==true">
								<span class="glyphicon glyphicon-pencil"
									ng-click="editarPendiente(p)"></span>
							</button>
							<button class="btn btn-primary btn-xs"
								ng-show="p.completado==false">
								<span class="glyphicon glyphicon-ok"
									ng-click="hacerPendiente(p)"></span>
							</button>
							<button class="btn btn-primary btn-xs"
								ng-show="p.completado==true">
								<span class="glyphicon glyphicon-repeat"
									ng-click="deshacerPendiente(p)"></span>
							</button>
							<button class="btn btn-danger btn-xs">
								<span class="glyphicon glyphicon-remove"
									ng-click="borrarPendiente(p)"></span>
							</button>
						</span>
					</div>
				</li>
			</ul>


		</div>
	</div>
	<br />
	<script src="${js}/angular.min.js"></script>
	<script src="${js}/jquery.js"></script>
	<script src="${js}/bootstrap.min.js"></script>
	<script src="${js}/app.js"></script>
</body>
</html>