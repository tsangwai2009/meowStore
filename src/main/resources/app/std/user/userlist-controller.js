'use strict';
(function()
{
	var app = angular.module('std.app');
	                
	app.controller('PageBController', ['$scope', 'userService', PageBController]);
	
	function PageBController($scope, userService)
	{
		$scope.refresh = function()
		{
			userService.findUserAll().then(function(result)
			{
				result.data.forEach(user => {
					if(user.lastLoginDate) user.lastLoginDate = $scope.convertTime(new Date(user.lastLoginDate));	
					if(user.createdDate) user.createdDate = $scope.convertTime(new Date(user.createdDate));	
					if(user.updatedDate) user.updatedDate = $scope.convertTime(new Date(user.updatedDate));
					if(user.approvedDate) user.approvedDate = $scope.convertTime(new Date(user.approvedDate));
				});
				$scope.data = result.data;
			});
		};
		
		$scope.convertTime = function(date){
			return date.getDate()+
          "/"+(date.getMonth()+1)+
          "/"+date.getFullYear()+
          " "+date.getHours()+
          ":"+date.getMinutes()+
          ":"+date.getSeconds();
		};
		
		$scope.approve = function (username){
			userService.approve(username).then(function(result)
			{
				$scope.refresh();
			});
		};
		
		$scope.reset = function (username){
			userService.reset(username).then(function(result)
			{
				$scope.refresh();
			});
		};
		
		$scope.refresh();
	}
	                
	                
	                                      
})();
