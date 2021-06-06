'use strict';
(function()
{

	var app = angular.module('std.app');
	app.controller('UserController', ['$scope', 'userService', UserController]);
	function UserController($scope, userService)
	{
		$scope.notLogined= true;
		
		$scope.submit = function()
		{
			if(!$scope.username || !$scope.password) {
		        $scope.message = "Empty username or password";
		        return;
		    }
		    
			userService.findByUser($scope.username,$scope.password).then(function(result)
			{
				if(!result.data){
					$scope.message = "Wrong username or password";
					return;
				}
				else{
					if(result.data["status"]=="approved"){
						$scope.message = "Welcome " + result.data["name"];
						$scope.refresh();
					}
					else($scope.message = "Your account has not approved / block. Please contact or wait moderator process")
				}
			});
		};
		
		$scope.check = function(){
			userService.checkLogin().then(function(result)
			{
				if(result.data[0]["authority"]=="ROLE_USER"){
					$scope.notLogined= false;
				}
				else{$scope.notLogined= true;}
			});
		};
		
		$scope.refresh = function()
		{
			$scope.check();
		};
		
		$scope.refresh();
		
		
		
	}                                     
})();

