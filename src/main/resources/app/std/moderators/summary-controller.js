'use strict';
(function()
{ 
	var app = angular.module('std.app');
	app.controller('SummaryController', ['$scope', 'buyService', SummaryController]);
	
	function SummaryController($scope, buyService) //Generate last quarter summary report
	{
		$scope.check = function(){
			buyService.checkLogin().then(function(result)
			{
				if(result.data[0]["authority"]=="ROLE_ADMIN"){
					$scope.notLogined= false;
					buyService.summary().then(function(result)
					{
						$scope.quarter = result.data["quarter"];
						$scope.completedContract = result.data["completedContract"];
						$scope.voidContract = result.data["voidContract"];
						$scope.cash = result.data["cash"]; 
					});
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
