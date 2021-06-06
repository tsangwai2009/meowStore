'use strict';
(function()
{	
	var app = angular.module('std.app');
	app.controller('BookController', ['$scope', 'bookService', BookController]);
	function BookController($scope, bookService) //For add book
	{
		$scope.typeList=["Text","PDF"];
		$scope.refresh = function()
		{
			bookService.findBookAll().then(function(result)
			{
				$scope.data = result.data;
			});
			bookService.findAuthorAll().then(function(result)
			{
				$scope.authorData = result.data;
			});
			bookService.findPublisherAll().then(function(result)
			{
				$scope.publisherData = result.data;
			});
			bookService.findCategoryAll().then(function(result)
			{
				$scope.categoryData = result.data;
			});
		};
		
		$scope.refresh();
		
		$scope.add = function()
		{
			bookService.checkLogin().then(function(result)
			{
				if(!($scope.subject && $scope.description && $scope.isbn && $scope.content
					&&$scope.contentType&&$scope.price&&$scope.author&&$scope.publisher&&$scope.category)){
					$scope.addMsg = "All field must be filled.";
					return;
				} else {
					bookService.add($scope.subject,$scope.description,$scope.isbn,$scope.content,
						$scope.contentType,$scope.price,$scope.author.authorId,
						$scope.publisher.publisherId,$scope.category.categoryId).then(function(result){
						$scope.addMsg = result.data["msg"];
						return;
					});
				}
			});
		};
		
	}
	                
})();


