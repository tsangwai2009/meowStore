'use strict';
(function()
{	
	var app = angular.module('std.app');
	app.controller('BookListController', ['$scope', 'bookService', BookListController]);
	function BookListController($scope, bookService) //Book List
	{
		$scope.refresh = function()
		{
			bookService.checkLogin().then(function(result)
				{
					if(result.data[0]["authority"]=="ROLE_ADMIN"){
						$scope.notLogined= false;
						bookService.findBookAll().then(function(result)
						{
							$scope.data = result.data;
						});
					}
					else{$scope.notLogined= true;}  //Request login
				});
			
		};
		
		$scope.refresh();
		
	}
	                
})();

