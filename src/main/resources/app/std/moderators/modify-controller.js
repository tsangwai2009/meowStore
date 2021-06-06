'use strict';
(function()
{
	var app = angular.module('std.app');
	app.controller('ModifyController', ['$state', '$scope', 'bookService', ModifyController]);
	
	function ModifyController($state, $scope, bookService) //Book modify
	{
		$scope.typeList=["Text","PDF"];
		$scope.refresh = function()
		{
			//Select menu
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
			
			bookService.findBookByIdwithAdmin($state.params['bookId']).then(function(result)
			{
				$scope.subject = result.data["subject"];
				$scope.description = result.data["description"];
				$scope.isbn = result.data["isbn"];
				$scope.content = result.data["content"];
				$scope.contentType = result.data["contentType"];
				$scope.price = result.data["price"];
				$scope.authorData.forEach(a=>{if (a.authorId==result.data["author"]["authorId"]){$scope.author = a}})
				$scope.publisherData.forEach(p=>{if (p.publisherId==result.data["publisher"]["publisherId"]){$scope.publisher = p}})
				$scope.categoryData.forEach(c=>{if (c.categoryId==result.data["category"]["categoryId"]){$scope.category = c}})
			});
		};
		
		$scope.refresh();

		$scope.modify = function()
		{
			if(!($scope.subject && $scope.description && $scope.isbn && $scope.content
				&&$scope.contentType&&$scope.price&&$scope.author&&$scope.publisher&&$scope.category)){
				$scope.modifyMsg = "All field must be filled ";
				return;
			}else{
				bookService.modify($state.params['bookId'],$scope.subject,$scope.description,$scope.isbn,$scope.content,
				$scope.contentType,$scope.price,$scope.author.authorId,
				$scope.publisher.publisherId,$scope.category.categoryId).then(function(result){
					$scope.modifyMsg = result.data["msg"];
					return;
				});
			}
		};
	}
	                
})();
