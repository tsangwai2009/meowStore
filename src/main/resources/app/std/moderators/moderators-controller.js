'use strict';
(function()
{

	var app = angular.module('std.app');
	app.controller('ModeratorsController', ['$scope', 'moderatorService', ModeratorsController]);

	function ModeratorsController($scope, moderatorService) //Login moderator system
	{
		$scope.notLogined= true;
		$scope.status= null;
		$scope.submit = function()
		{
			if(!$scope.username || !$scope.password) {
		        $scope.message = "Empty username or password";
		        return;
		    }
		    
			moderatorService.findByUser($scope.username,$scope.password).then(function(result)
			{
				if(!result.data){
					$scope.message = "Wrong username or password";
					return;
				}
				else{
					$scope.message = "Welcome " + result.data["username"];
					$scope.refresh();
				}
			});
		};
		
		$scope.check = function(){
			moderatorService.checkLogin().then(function(result)
			{
				if(result.data[0]["authority"]=="ROLE_ADMIN"){
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
