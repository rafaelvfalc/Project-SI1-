var anunciantes = angular.module("AD-Extreme", []);
anunciantes.controller("anunciantesCtrl", ['$scope', '$http', function($scope, $http){

	function getUsers(){
		$http.get('/users')
			.then(function(response){
				return response.data;
			},
			function(response){
				console.log(response.status);
		});
	}

	function usuarioLogado() {
		$http.get('/loggedUser')
			.then(function(response){
				return response.data;
			}
			, function(response){
				console.log(response.status);
			});
	}
	
	var logged = usuarioLogado();
	var users = getUsers();
	$scope.usuarios = function(){
		return users.splice(logged.id, 1);
	};
}])