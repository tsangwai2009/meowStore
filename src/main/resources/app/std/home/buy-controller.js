'use strict';
(function()
{	
	var app = angular.module('std.app');
	app.controller('BuyController', ['$state','$scope', 'buyService', BuyController]);
	function BuyController($state,$scope, buyService) //Buy method
	{
		$scope.notOwn=true;
		$scope.check = function(){
			buyService.checkLogin().then(function(result)
			{
				if(result.data[0]["authority"]=="ROLE_USER"){
					$scope.notLogined= false;
					buyService.findBookById($state.params['bookId']).then(function(result)
					{
						$scope.subject = result.data["subject"];
						$scope.description = result.data["description"];
						$scope.isbn = result.data["isbn"];
						$scope.contentType = result.data["contentType"];
						$scope.price = result.data["price"];
					});
					$scope.checkBalance();
				}
				else{$scope.notLogined= true;}
			});
		};
		
		$scope.checkBalance = function(){
			buyService.checkBalance().then(function(result){
						$scope.balance = result.data;
						if(result.data==""){$scope.balance = 0;}
					});
		}
		
		$scope.cashbuy = function(){
			buyService.cashBuy($state.params['bookId']).then(function(result){
				$scope.buyMsg = result.data["msg"];
				$scope.notOwn=result.data["notOwn"];
				$scope.checkBalance();
			});
		
		}
		
		$scope.chequebuy = function(amount){
			buyService.chequeBuy($state.params['bookId'],amount).then(function(result){
				$scope.buyMsg = result.data["msg"];
			});
		}
		
		
		$scope.refresh = function()
		{
			$scope.check();
		};

		
		$scope.refresh();
	
	}
	                
})();

