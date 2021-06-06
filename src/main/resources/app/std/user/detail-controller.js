//refer to book data

'use strict';
(function()
{ 
	var app = angular.module('std.app');
	app.controller('DetailController', ['$state','$scope', 'bookService', DetailController]);
	
	function DetailController($state, $scope, bookService) // Read the book
	{
	
		$scope.check = function(){
			bookService.checkLogin().then(function(result)
			{
				if(result.data[0]["authority"]=="ROLE_USER"){
					$scope.notLogined= false;
					$scope.own();
				}
				else{$scope.notLogined= true;}
			});
		};
		
		$scope.own = function(){
			bookService.findShelfAll().then(function(result)
			{
				$scope.notOwn= true;
				result.data.forEach(o=>{if (o.bookId == $state.params['bookId']){
					$scope.notOwn= false;
					$scope.view();
				}});
				
			});
		};
		
		$scope.view = function(){
			bookService.findBookByIdwithLogin($state.params['bookId']).then(function(result)
			{
				$scope.subject = result.data["subject"];
				$scope.description = result.data["description"];
				$scope.isbn = result.data["isbn"];
				$scope.content = result.data["content"];
				$scope.contentType = result.data["contentType"];
				if($scope.contentType=="PDF"){$scope.isLink=true;}else{$scope.isLink=false;}
				$scope.price = result.data["price"];
				$scope.author = result.data["author"];
				$scope.publisher = result.data["publisher"];
				$scope.category = result.data["category"];
			});	
			bookService.updateAccess($state.params['bookId']).then(function(result){}); //Update access time
		};
		
		$scope.refresh = function()
		{
			$scope.check();
		};
		
		$scope.refresh();
		

	}
	                
})();
