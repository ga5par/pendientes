 var app = angular.module('pendientes', []);

 app.controller('pendientesController', function($scope, $http, $timeout) {
     $scope.pendientes = []
     $scope.pendienteForm = {
         id: "",
         nombre: "",
         fecha: new Date()
     };
     $scope.boton = "Agregar";
     $scope.temp = "";

     obtenerListaPendientes();

     function obtenerListaPendientes() {
         $http({
             method: 'GET',
             url: 'lista'
         }).then(function successCallback(response) {
             $scope.pendientes = response.data;
             mostarInformacion();
         }, function errorCallback(response) {
             console.log(response.statusText);
         });
     }

     $scope.nuevoPendiente = function() {
         if ($scope.pendienteForm.nombre.trim().length < 1) {
             mensaje("El pendiente no puede estar vacio o lleno de espacios.");
             return false;
         }

         if ($scope.pendienteForm.nombre.trim().length > 250) {
             mensaje("El pendiente no puede ser mayor a 250 caracteres.");
             return false;
         }

         if (yaExiste()) {
             mensaje("El pendiente ya existe.");
             return false;
         }
         $http({
             method: 'POST',
             url: 'nuevo',
             data: angular.toJson($scope.pendienteForm),
             headers: {
                 'Content-Type': 'application/json'
             }
         }).then(function successCallback(response) {
             obtenerListaPendientes();
             limpiarForm();
         }, function errorCallback(response) {
             console.log(response.statusText);
         });
     }

     $scope.actualizarPendiente = function(p) {
         $http({
             method: 'POST',
             url: 'actualizar',
             data: angular.toJson(p),
             headers: {
                 'Content-Type': 'application/json'
             }
         }).then(function successCallback(response) {
             mostarInformacion();
         }, function errorCallback(response) {
             console.log(response.statusText);
         });
     }

     $scope.borrarPendiente = function(p) {
         $http({
             method: 'DELETE',
             url: 'borrar',
             data: angular.toJson(p),
             headers: {
                 'Content-Type': 'application/json'
             }
         }).then(function successCallback(response) {
             obtenerListaPendientes();
         }, function errorCallback(response) {
             console.log(response.statusText);
         });
     }

     $scope.hacerPendiente = function(p) {
         p.completado = true;
         $scope.actualizarPendiente(p);
         limpiarForm();
     }

     $scope.deshacerPendiente = function(p) {
         p.completado = false;
         $scope.actualizarPendiente(p);
         limpiarForm();
     }

     $scope.editarPendiente = function(p) {
         $scope.boton = "Editar";
         $scope.pendienteForm.nombre = p.nombre;
         $scope.temp = p.nombre;
         $scope.pendienteForm.id = p.id;
     }


     function limpiarForm() {
         $scope.pendienteForm.nombre = "";
         $scope.pendienteForm.id = "";
         $scope.boton = "Agregar";
     };


     function yaExiste() {
         if ($scope.pendienteForm.nombre.toLowerCase() == $scope.temp.toLowerCase()) {
             return false;
         }
         for (var i = 0; i < $scope.pendientes.length; i++) {
             if ($scope.pendienteForm.nombre.toLowerCase() == $scope.pendientes[i].nombre.toLowerCase()) {
                 return true;
             }
         }
         return false;
     };

     function mostarInformacion() {
         var total = $scope.pendientes.length;
         var completos = pendientesCompletados();
         var restantes = total - completos;
         $scope.informacion = (restantes == 1 ? "Queda " : "Quedan ") + restantes + (restantes == 1 ? " pendiente " : " pendientes ") + "de " + total + " por realizar."

     }

     function pendientesCompletados() {
         var contador = 0;
         for (var i = 0; i < $scope.pendientes.length; i++) {
             if ($scope.pendientes[i].completado == true) {
                 contador++;
             }
         }
         return contador;
     }

     function mensaje(msj) {
         $("#errorMsg").show();
         $scope.showError = false;
         $scope.doFade = false;
         $scope.showError = true;
         $scope.errorMessage = msj;
         $timeout(function() {
             $scope.doFade = true;
             $("#errorMsg").hide();
         }, 2500);
     };

 });