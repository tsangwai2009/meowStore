'use strict';
(function()
{
	var app = angular.module('std.app');
	app.controller('EditController', ['$state', '$scope', 'buyService', EditController]);
	
	function EditController($state, $scope, buyService) //Edit contract
	{	
		$scope.statusList=["Not received","Verifying","Declined","Approved"];
		$scope.refresh = function(){
				buyService.findChequeById($state.params['chequeId']).then(function(result){
					$scope.contractId = result.data["contract"]["contractId"];
					$scope.chequeId = result.data["chequeId"];
					$scope.userId = result.data["contract"]["userId"];
					$scope.bookId = result.data["contract"]["bookId"];
					$scope.amount = result.data["amount"];
					$scope.chequeStatus = result.data["status"];
					$scope.contractStatus = result.data["contract"]["status"];
				});
			};
		$scope.refresh();
		
		$scope.edit = function(contractId,chequeId,userId,bookId,amount,chequeStatus){
						if(!(bookId && amount)){
							$scope.editMsg = "All field must be filled ";
							return;
						}else{
							buyService.findBookById(bookId).then(function(result){
								if(!result.data){
									$scope.editMsg = "The book not exist.";
									return;
								}
								$scope.price = result.data["price"];
								if($scope.price>amount){
									$scope.editMsg = "The amount of cheque is less than the target book price.";
									return;
								}
							});
							buyService.edit(contractId,chequeId,userId,bookId,amount,chequeStatus).then(function(result){
								$scope.editMsg =  result.data["msg"];
							});
						}
		
		};
		
	}
	                
})();

