'use strict';
(function()
{	
	var app = angular.module('std.app');
	app.controller('HomeController', ['$scope', 'bookService', HomeController]);
	function HomeController($scope, bookService)
	{
		$scope.notLogined= true;
		$scope.refresh = function()
		{	
			bookService.findBookAll().then(function(result)
			{
				$scope.data = result.data;
				bookService.checkLogin().then(function(result)
				{
					if(result.data[0]["authority"]=="ROLE_USER"){
					$scope.notLogined= false;
					if($scope.data) $scope.data.forEach(d=>{
								bookService.findShelfAll().then(function(result){
									if(result.data) result.data.forEach(o=>{if (o.bookId == d.bookId){d.owned="yes";}
									});
								  })
						});
					}
				});;
			});
		};
		
		
		
		$scope.refresh();
		
	
	}
	                
})();

//subject,description,isbn,content,contentType,price,author,publisher,category
