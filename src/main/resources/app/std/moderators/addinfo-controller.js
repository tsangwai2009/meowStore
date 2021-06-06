'use strict';
(function()
{	
	var app = angular.module('std.app');
	app.controller('AddInfoController', ['$scope', 'bookService', AddInfoController]);
	function AddInfoController($scope, bookService) //For add book
	{
		
		$scope.addAuthor = function(Aname,Aemail,Acountry)
		{
			if(!(Aname && Aemail && Acountry)){
				$scope.addMsg = "All field must be filled.";
				return;
			} else {
				bookService.addAuthor(Aname,Aemail,Acountry).then(function(result){
					$scope.addMsg = result.data["msg"];
					return;
				});
			}
		};
		
		
		
	}
	                
})();


