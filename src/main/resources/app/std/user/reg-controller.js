'use strict';
(function()
{
	var app = angular.module('std.app');
	app.controller('RegController', ['$scope', 'userService', RegController]);
	function RegController($scope, userService)
	{
		
		$scope.reg = function()
		{	
			if(!($scope.username && $scope.password && $scope.name && $scope.email
				&& $scope.telephone && $scope.mobile && $scope.address)) {
		        $scope.regMsg = "All field must be filled";
		        return;
		    }else{
		    	userService.reg($scope.username, $scope.password, $scope.name, $scope.email,
					$scope.telephone, $scope.mobile, $scope.address).then(function(result){
				if(!result.data){
					$scope.regMsg = "The username has been used.";
					return;
				}
				else{
					$scope.regMsg = "Success! Please wait for moderator approve.";
					return;
					}
				});
		    }
		};

	}                                     
})();

