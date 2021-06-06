'use strict';
(function()
{ 
	var app = angular.module('std.app');
	app.controller('ContractController', ['$scope', 'buyService', ContractController]);
	
	function ContractController($scope, buyService) //Contract Management
	{
		$scope.check = function(){
			buyService.checkLogin().then(function(result)
			{
				if(result.data[0]["authority"]=="ROLE_ADMIN"){
					$scope.notLogined= false;
					buyService.findUserChequeAll().then(function(result)
					{
						$scope.chequeData = result.data;
						if(result.data) $scope.chequeData.forEach(record => {
							if(record.contract.contractId) record.contract.contractId = $scope.pad(record.contract.contractId);
							if(record.chequeId) {
								record.chequeIdTemp = record.chequeId;
								record.chequeId = $scope.pad(record.chequeId);
							}
							if(record.transactionId) record.transactionId = $scope.pad(record.transactionId);
							if(record.createdDate) record.createdDate = $scope.convertTime(new Date(record.createdDate));	
							if(record.contract.updatedDate) record.contract.updatedDate = $scope.convertTime(new Date(record.contract.updatedDate));
							if(record.contract.voidedDate) record.contract.voidedDate = $scope.convertTime(new Date(record.contract.voidedDate));
						});
					});
					
				}
				else{$scope.notLogined= true;} //Request login
			});
		};
		
		$scope.terminate = function(chequeId){
			buyService.terminate(chequeId).then(function(result){
					$scope.refresh();
				});
		}
		
		$scope.approve = function(chequeId){
			buyService.approve(chequeId).then(function(result){
					$scope.refresh();
				});
		}
		
		$scope.pad = function zeroPad(num) {
		  var zero = 9 - num.toString().length + 1;
		  return Array(+(zero > 0 && zero)).join("0") + num;
		}
		
		$scope.convertTime = function(date){
			return date.getDate()+
          "/"+(date.getMonth()+1)+
          "/"+date.getFullYear()+
          " "+date.getHours()+
          ":"+date.getMinutes()+
          ":"+date.getSeconds();
		};
		
		$scope.refresh = function()
		{
			$scope.check();

		};
		
		$scope.refresh();

	}
	                
})();
