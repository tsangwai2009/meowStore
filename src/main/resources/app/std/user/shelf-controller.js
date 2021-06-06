//refer to book data

'use strict';
(function()
{ 
	var app = angular.module('std.app');
	app.controller('ShelfController', ['$scope', 'bookService', ShelfController]);
	
	function ShelfController($scope, bookService)
	{
	
		$scope.check = function(){
			bookService.checkLogin().then(function(result)
			{
				if(result.data[0]["authority"]=="ROLE_USER"){
					$scope.notLogined= false;
					bookService.findShelfAll().then(function(result)
					{
						$scope.data = result.data;
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
